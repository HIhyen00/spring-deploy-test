package com.example.demo.kakao_authentication.service;


import com.example.demo.account.entity.Account;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.kakao_authentication.repository.KakaoAuthenticationRepository;
import com.example.demo.kakao_authentication.service.response.KakaoUserInfoResponse;
import com.example.demo.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;
    final private AccountRepository accountRepository;

    @Override
    public KakaoUserInfoResponse handleLogin(String code) {
        Map<String, Object> tokenResponse = kakaoAuthenticationRepository.getAccessToken(code);
        // Object로 String인지 모름
        String accessToken = tokenResponse.get("access_token").toString();

        Map<String, Object> userInfoResponse = kakaoAuthenticationRepository.getUserInfo(accessToken);
        Map<?, ?> userInfoProperties = (Map<?, ?>) userInfoResponse.get("properties");
        String nickname = (String) userInfoProperties.get("nickname");

        Map<?, ?> userInfoKakaoAccount = (Map<?, ?>) userInfoResponse.get("kakao_account");
        String email = (String) userInfoKakaoAccount.get("email");

        Optional<Account> maybeAccount = accountRepository.findByEmail(email);
        boolean isNewUser = false;

        if (maybeAccount.isEmpty()) {
            isNewUser = true;
        }

        return KakaoUserInfoResponse.from(email, nickname, accessToken, isNewUser);
    }

}
