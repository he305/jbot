package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.application.dtos.TokenDto;
import com.github.he305.jbot.anime.configuration.MalApiConfig;
import com.github.he305.jbot.anime.exceptions.NetworkException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class MalAuthenticationServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private MalApiConfig malApiConfig;
    @Mock
    private CodeChallengeProvider codeChallengeProvider;

    @InjectMocks
    private MalAuthenticationServiceImpl underTest;

    @Test
    void getToken_error() {
        String clientId = "clientId";
        String clientSecret = "clientSecret";
        String challengeCode = "challengeCode";

        Mockito.when(malApiConfig.getClientId()).thenReturn(clientId);
        Mockito.when(malApiConfig.getClientSecret()).thenReturn(clientSecret);
        Mockito.when(codeChallengeProvider.getCodeChallenge()).thenReturn(challengeCode);

        Mockito.when(restTemplate.exchange(eq(MalAuthenticationServiceImpl.BASE_URL), eq(HttpMethod.POST), Mockito.any(), eq(TokenDto.class))).thenThrow(HttpClientErrorException.class);
        assertThrows(NetworkException.class, () -> underTest.getToken("123"));
    }

    @Test
    void getToken_ok() {
        String clientId = "clientId";
        String clientSecret = "clientSecret";
        String challengeCode = "challengeCode";

        Mockito.when(malApiConfig.getClientId()).thenReturn(clientId);
        Mockito.when(malApiConfig.getClientSecret()).thenReturn(clientSecret);
        Mockito.when(codeChallengeProvider.getCodeChallenge()).thenReturn(challengeCode);

        TokenDto expected = new TokenDto();
        ResponseEntity<TokenDto> response = ResponseEntity.ok(expected);
        Mockito.when(restTemplate.exchange(eq(MalAuthenticationServiceImpl.BASE_URL), eq(HttpMethod.POST), Mockito.any(), eq(TokenDto.class))).thenReturn(response);

        TokenDto actual = underTest.getToken("123");
        assertEquals(expected, actual);
    }

    @Test
    void refreshToken_error() {
        String clientId = "clientId";
        String clientSecret = "clientSecret";

        Mockito.when(malApiConfig.getClientId()).thenReturn(clientId);
        Mockito.when(malApiConfig.getClientSecret()).thenReturn(clientSecret);

        Mockito.when(restTemplate.exchange(eq(MalAuthenticationServiceImpl.BASE_URL), eq(HttpMethod.POST), Mockito.any(), eq(TokenDto.class))).thenThrow(HttpClientErrorException.class);
        assertThrows(NetworkException.class, () -> underTest.refreshToken("123"));
    }

    @Test
    void refreshToken_ok() {
        String clientId = "clientId";
        String clientSecret = "clientSecret";

        Mockito.when(malApiConfig.getClientId()).thenReturn(clientId);
        Mockito.when(malApiConfig.getClientSecret()).thenReturn(clientSecret);

        TokenDto expected = new TokenDto();
        ResponseEntity<TokenDto> response = ResponseEntity.ok(expected);
        Mockito.when(restTemplate.exchange(eq(MalAuthenticationServiceImpl.BASE_URL), eq(HttpMethod.POST), Mockito.any(), eq(TokenDto.class))).thenReturn(response);

        TokenDto actual = underTest.refreshToken("123");
        assertEquals(expected, actual);
    }
}