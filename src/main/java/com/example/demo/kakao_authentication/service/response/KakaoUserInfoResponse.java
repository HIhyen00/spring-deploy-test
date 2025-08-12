package com.example.demo.kakao_authentication.service.response;

import com.example.demo.kakao_authentication.controller.response_form.KakaoUserInfoResponseForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoUserInfoResponse {
    final private String email;
    final private String nickname;
    final private String accessToken;
    final private Boolean isNewUser;

    public static KakaoUserInfoResponse from(String email, String nickname, String accessToken, Boolean isNewUser) {
        return new KakaoUserInfoResponse(
                email,
                nickname,
                accessToken,
                isNewUser
        );

    }
}
