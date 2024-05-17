package com.example.member.domain.authCode;

import com.example.common.config.KakaoOauthConfig;
import com.example.member.domain.type.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

	private final KakaoOauthConfig kakaoOauthConfig;

	@Override
	public OauthServerType supportServer() {
		return OauthServerType.KAKAO;
	}

	@Override
	public String provide() {
		return UriComponentsBuilder
			.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
			.queryParam("response_type","code")
			.queryParam("client_id",kakaoOauthConfig.clientId())
			.queryParam("redirect_uri",kakaoOauthConfig.redirectUri())
			.queryParam("scope",String.join(",",kakaoOauthConfig.scope()))
			.toUriString();
	}
}
