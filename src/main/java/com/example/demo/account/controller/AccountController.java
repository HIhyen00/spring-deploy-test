package com.example.demo.account.controller;

import com.example.demo.account.controller.request_form.RegisterAccountRequestForm;
import com.example.demo.account.controller.response_form.RegisterAccountResponseForm;
import com.example.demo.account.service.AccountService;
import com.example.demo.account.service.request.RegisterAccountRequest;
import com.example.demo.account.service.response.RegisterAccountResponse;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    final private AccountService accountService;
    final private RedisCacheService redisCacheService;
    // userToken > 사용자 Id > accessToken (KEY, VALUE 관계)
    @PostMapping("/register")
    public RegisterAccountResponseForm register(@RequestHeader("Authorization")String authorizedHeader, @RequestBody RegisterAccountRequestForm requestForm){
        log.info("register() -> requestForm: {}", requestForm);

        // 지금 가입 요청한 사용자가 정상적인 임시 토큰을 가지고 있는가? 회원가입 필요, 카카오 인증 완료 ... 약관동의
        String temporaryUserToken = authorizedHeader.replace("Bearer ", "").trim();
        // 임시토큰 > access token ... String
        String accessToken = redisCacheService.getValueByKey(temporaryUserToken, String.class);
        if (accessToken == null) {
            throw new IllegalArgumentException("만료되거나 잘못된 토큰 요청입니다.");
        }

        // 회원가입 진행
        RegisterAccountRequest request = requestForm.toRegisterAccountRequest();
        RegisterAccountResponse response = accountService.register(request);
        Long accountId = response.getAccountId();

        // 진짜 토큰 발급
        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(userToken, accountId);
        redisCacheService.setKeyAndValue(accountId, accessToken);

        // 임시 토큰 삭제
        redisCacheService.deleteByKey(temporaryUserToken);

        return RegisterAccountResponseForm.from(response, userToken);
    }

//    @PostMapping("/login")
//    public LoginAccountResponseForm login(@RequestBody LoginAccountRequestForm requestForm) {
//        log.info("login() -> requestForm: {}", requestForm);
//
//        LoginAccountRequest request = requestForm.toLoginAccountRequest();
//        LoginAccountResponse response = accountService.login(request);
//
//        return LoginAccountResponseForm.from(response);
//    }

}
