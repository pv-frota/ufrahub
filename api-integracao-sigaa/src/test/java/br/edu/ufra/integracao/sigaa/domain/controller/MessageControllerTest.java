package br.edu.ufra.integracao.sigaa.domain.controller;

import br.edu.ufra.integracao.sigaa.AuthenticatedRestTemplate;
import br.edu.ufra.integracao.sigaa.MySQLTestContainer;
import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.integracao.sigaa.domain.model.File;
import br.edu.ufra.integracao.sigaa.domain.model.Message;
import br.edu.ufra.integracao.sigaa.domain.repository.FileRepository;
import br.edu.ufra.integracao.sigaa.domain.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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