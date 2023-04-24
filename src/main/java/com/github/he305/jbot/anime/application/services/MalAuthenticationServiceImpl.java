package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.application.dtos.TokenDto;
import com.github.he305.jbot.anime.configuration.MalApiConfig;
import com.github.he305.jbot.anime.exceptions.NetworkException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MalAuthenticationServiceImpl implements MalAuthenticationService {
    public static final String BASE_URL = "https://myanimelist.net/v1/oauth2/token";
    private final RestTemplate restTemplate;
    private final MalApiConfig malApiConfig;
    private final CodeChallengeProvider codeChallengeProvider;

    @Override
    public TokenDto getToken(String authorizationCode) {
        String clientId = malApiConfig.getClientId();
        String clientSecret = malApiConfig.getClientSecret();
        String challengeCode = codeChallengeProvider.getCodeChallenge();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("code_verifier", challengeCode);
        map.add("code", authorizationCode);
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        TokenDto token;
        try {
            ResponseEntity<TokenDto> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, TokenDto.class);
            token = response.getBody();
        } catch (ResourceAccessException | HttpServerErrorException | HttpClientErrorException ex) {
            throw new NetworkException(ex.getMessage());
        }

        return token;
    }

    @Override
    public TokenDto refreshToken(String refreshToken) {
        String clientId = malApiConfig.getClientId();
        String clientSecret = malApiConfig.getClientSecret();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        TokenDto token;
        try {
            ResponseEntity<TokenDto> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, TokenDto.class);
            token = response.getBody();
        } catch (ResourceAccessException | HttpServerErrorException | HttpClientErrorException ex) {
            throw new NetworkException(ex.getMessage());
        }

        return token;
    }
}
