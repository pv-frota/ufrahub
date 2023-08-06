package br.edu.ufra.integracao.sigaa.test.domain.repository;

import br.edu.ufra.integracao.sigaa.domain.repository.UserRepository;
import br.edu.ufra.integracao.sigaa.test.MySQLTestContainer;
import br.edu.ufra.integracao.sigaa.domain.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest extends MySQLTestContainer {

    private final UserRepository underTest;

    @Autowired
    public UserRepositoryTest(UserRepository underTest) {
        this.underTest = underTest;
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByUsername() {
        //given
        String username1 = "pv_frota";
        String username2 = "mario789";
        User u1 = new User(1, username1, "2019027960", "SI", "GRADUAÇÃO", 8.0, 8.0);
        User u2 = new User(2, username2, "2019026060", "SI", "GRADUAÇÃO", 8.0, 8.0);
        underTest.save(u1);
        underTest.save(u2);
        //when
        User expected = underTest.findByUsername(username1);
        //then
        assertThat(expected.getId()).isEqualTo(u1.getId());
    }
}