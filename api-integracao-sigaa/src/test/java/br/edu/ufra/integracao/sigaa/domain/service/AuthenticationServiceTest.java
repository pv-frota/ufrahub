package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.autenticationufrapp.domain.service.AuthenticationService;
import br.edu.ufra.autenticationufrapp.infrastructure.dto.request.LoginRequestDTO;
import br.edu.ufra.autenticationufrapp.infrastructure.dto.response.JwtResponseDTO;
import br.edu.ufra.integracao.sigaa.MySQLTestContainer;
import br.edu.ufra.integracao.sigaa.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthenticationServiceTest extends MySQLTestContainer {

    private final AuthenticationService underTest;

    @Autowired
    public AuthenticationServiceTest(AuthenticationService underTest) {
        this.underTest = underTest;
    }

    @Test
    void shouldAuthenticate() {
        //given
        LoginRequestDTO dto = new LoginRequestDTO("pv_frota", "pedro123");
        //when
        JwtResponseDTO expected = underTest.authenticate(dto);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldNotAuthenticate() {
        //given
        User u1 = new User(1, "pv_frota", "2019027960", "SI", "GRADUAÇÃO", 8.0, 8.0);
        //when
        LoginRequestDTO dto = new LoginRequestDTO("pv_frota", "pedro1234");
        //then
        Assertions.assertThatThrownBy(() -> underTest.authenticate(dto))
                .isInstanceOf(BadCredentialsException.class);
    }
}