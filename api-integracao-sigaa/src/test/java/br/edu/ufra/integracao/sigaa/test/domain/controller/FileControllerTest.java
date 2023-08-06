package br.edu.ufra.integracao.sigaa.test.domain.controller;

import br.edu.ufra.integracao.sigaa.test.AuthenticatedRestTemplate;
import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.integracao.sigaa.domain.model.File;
import br.edu.ufra.integracao.sigaa.domain.repository.FileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerTest extends AuthenticatedRestTemplate {

    private final FileRepository repository;

    @Autowired
    public FileControllerTest(TestRestTemplate request, FileRepository repository) {
        super(request);
        this.repository = repository;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldGetFile() {
        //given
        String selectedUser = getCredentials().getUsername();
        FileTypeEnum selectedFile = FileTypeEnum.HISTORICO;
        Date now = new Date();
        File vinculo = new File(1, selectedUser, FileTypeEnum.VINCULO, now, new byte[]{});
        File historico = new File(2, selectedUser, selectedFile, now, new byte[]{});
        repository.save(vinculo);
        repository.save(historico);
        //when
        String url = "/api/aluno/arquivo?usuario="+selectedUser+"&tipoArquivo=HISTORICO";
        ResponseEntity<File> expected = getRestTemplate().exchange(url, HttpMethod.GET, null, File.class);
        //then
        Assertions.assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}