package br.edu.ufra.integracao.sigaa.test.domain.controller;

import br.edu.ufra.integracao.sigaa.test.AuthenticatedRestTemplate;
import br.edu.ufra.integracao.sigaa.domain.model.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest extends AuthenticatedRestTemplate {

    @Autowired
    public MessageControllerTest(TestRestTemplate request) {
        super(request);
    }

    @Test
    void shouldSendMessage() {
        //given
        String selectedUser = getCredentials().getUsername();
        Message dto = new Message(selectedUser, "title", "question");
        //when
        String url = "/api/aluno/mensagem";
        ResponseEntity<Void> expected = getRestTemplate().postForEntity(url, dto, Void.class);
        //then
        Assertions.assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}