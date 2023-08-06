package br.edu.ufra.integracao.sigaa.test;

import br.edu.ufra.autenticationufrapp.infrastructure.dto.request.LoginRequestDTO;
import br.edu.ufra.integracao.sigaa.infrastructure.security.dto.response.UserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class AuthenticatedRestTemplate extends MySQLTestContainer {
    private final LoginRequestDTO credentials = new LoginRequestDTO("pv_frota", "pedro123");
    private final TestRestTemplate restTemplate;

    public AuthenticatedRestTemplate(TestRestTemplate request) {
        this.restTemplate = request;
    }

    @BeforeEach
    void beforeEach() {
        ResponseEntity<UserResponseDTO> auth = restTemplate.exchange("/auth/login", HttpMethod.POST, new HttpEntity<>(credentials), UserResponseDTO.class);
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("Authorization", "Bearer " + auth.getBody().getAccessToken());
                    return execution.execute(request, body);
                }));
    }

    public LoginRequestDTO getCredentials() {
        return credentials;
    }

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }
}
