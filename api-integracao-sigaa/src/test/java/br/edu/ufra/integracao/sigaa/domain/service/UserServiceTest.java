package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.integracao.sigaa.MySQLTestContainer;
import br.edu.ufra.integracao.sigaa.domain.model.User;
import br.edu.ufra.integracao.sigaa.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceTest extends MySQLTestContainer {

    private final UserRepository repository;
    private final UserService underTest;

    @Autowired
    UserServiceTest(UserRepository repository) {
        this.repository = repository;
        this.underTest = new UserService(repository);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findByUsername() {
        //given
        String username1 = "pv_frota";
        String username2 = "mario789";
        User u1 = new User(1, username1, "2019027960", "SI", "GRADUAÇÃO", 8.0, 8.0);
        User u2 = new User(2, username2, "2019026060", "SI", "GRADUAÇÃO", 8.0, 8.0);
        repository.save(u1);
        repository.save(u2);
        //when
        User expected = underTest.findByUsername(username1);
        //then
        assertThat(expected.getId()).isEqualTo(u1.getId());
    }
}