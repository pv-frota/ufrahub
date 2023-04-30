package br.edu.ufra.integracao.sigaa.domain.controller;

import br.edu.ufra.autenticationufrapp.infrastructure.dto.request.LoginRequestDTO;
import br.edu.ufra.integracao.sigaa.MySQLTestContainer;
import br.edu.ufra.integracao.sigaa.domain.model.User;
import br.edu.ufra.integracao.sigaa.domain.repository.UserRepository;
import br.edu.ufra.integracao.sigaa.infrastructure.security.dto.response.UserResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest extends MySQLTestContainer {

    private final TestRestTemplate request;
    private final UserRepository repository;

    @Autowired
    public AuthenticationControllerTest(TestRestTemplate request, UserRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldAuthenticate() {
        //given
        User u1 = new User(1, "pv_frota", "2019027960", "SI", "GRADUAÇÃO", 8.0, 8.0);
        repository.save(u1);
        //when
        LoginRequestDTO dto = new LoginRequestDTO("pv_frota", "pedro123");
        String url = "/auth/login";
        ResponseEntity<UserResponseDTO> expected = request.exchange(url, HttpMethod.POST, new HttpEntity<>(dto), UserResponseDTO.class);
        //then
        Assertions.assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotAuthenticate() {
        //given
        User u1 = new User(1, "pv_frota", "2019027960", "SI", "GRADUAÇÃO", 8.0, 8.0);
        repository.save(u1);
        //when
        LoginRequestDTO dto = new LoginRequestDTO("pv_frota", "pedro1234");
        //then
        Assertions.assertThatThrownBy(() -> request.exchange("/auth/login", HttpMethod.POST, new HttpEntity<>(dto), UserResponseDTO.class))
                .isInstanceOf(ResourceAccessException.class);
    }
}