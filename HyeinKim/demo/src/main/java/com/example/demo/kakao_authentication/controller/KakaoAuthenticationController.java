package com.example.demo.kakao_authentication.controller;

import com.example.demo.account.entity.Account;
import com.example.demo.account.service.AccountService;
import com.example.demo.kakao_authentication.controller.response_form.KakaoLoginResponseForm;
import com.example.demo.kakao_authentication.service.response.KakaoUserInfoResponse;
import com.example.demo.kakao_authentication.controller.response_form.KakaoUserInfoResponseForm;
import com.example.demo.kakao_authentication.service.KakaoAuthenticationService;
import com.example.demo.redis_cache.service.RedisCacheService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-authentication")
public class KakaoAuthenticationController {

    private final KakaoAuthenticationService kakaoAuthenticationService;
    private final RedisCacheService redisCacheService;
    private final AccountService accountService;

    @GetMapping("/login") // redirect-uri??
    public KakaoLoginResponseForm requestLogin(@RequestParam("code") String code) throws IOException {
        // 사용자 정보, accessToken을 가져야함
        log.info("requestLogin(): code {}", code);

        KakaoUserInfoResponse response = kakaoAuthenticationService.handleLogin(code);
//        String email = response.getEmail();
//        Optional<Account> optionalAccount = accountService.find(email);
//        boolean isNewUser = false;
//
//        if(optionalAccount.isEmpty()) {
//            isNewUser = true;
//            //Account newAccount = optionalAccount.orElse(new Account());
//        }

        String accessToken = response.getAccessToken();
        String temporaryUserToken = createTemporaryUserToken(accessToken);

        return KakaoLoginResponseForm.from(response, temporaryUserToken);
    }

    private String createTemporaryUserToken(String accessToken) {
        try {
            String temporaryUserToken = UUID.randomUUID().toString();
            redisCacheService.setKeyAndValue(temporaryUserToken, accessToken, Duration.ofMinutes(5));
            return temporaryUserToken;
        } catch (Exception e) {
            throw new RuntimeException("Redis Token 생성 중 에러 발생: "+ e.getMessage());
        }
    }

}
