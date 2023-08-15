package br.edu.ufra.ufrahub.api.test.domain.service;

import br.edu.ufra.ufrahub.api.test.MySQLTestContainer;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.model.Message;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.repository.MessageRepository;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.service.MessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MessageServiceTest extends MySQLTestContainer {

    private final MessageRepository repository;
    private final MessageService underTest;

    @Autowired
    MessageServiceTest(MessageRepository repository) {
        this.repository = repository;
        this.underTest = new MessageService(repository);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
    
    @Test
    void shouldSave() {
        //given
        String selectedUser = "pv_frota";
        Message message = new Message(selectedUser, "title", "question");
        //when
        underTest.save(message);
        //then
        assertDoesNotThrow(() -> repository.findById(1).get());
    }
}