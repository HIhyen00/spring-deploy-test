package com.example.demo.kakao_authentication.service;

import com.example.demo.kakao_authentication.service.response.KakaoUserInfoResponse;

import java.util.Map;

public interface KakaoAuthenticationService {
    KakaoUserInfoResponse handleLogin(String code);

}
