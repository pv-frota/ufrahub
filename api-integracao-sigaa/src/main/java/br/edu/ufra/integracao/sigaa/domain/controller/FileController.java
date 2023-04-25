package br.edu.ufra.integracao.sigaa.domain.controller;

import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.integracao.sigaa.domain.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Arquivos", description = "APIs REST para obter arquivos de usuários")
@RequestMapping("/api/aluno/arquivo")
public class FileController {

    private final FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @ApiOperation(value = "Obter um arquivo do usuário de acordo com o seu identificador e o tipo do arquivo", response = byte.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Arquivo obtido com sucesso"),
            @ApiResponse(code = 401, message = "Não Autorizado"),
    })
    @GetMapping
    public ResponseEntity<byte[]> getFile(@RequestParam("usuario") String usuario, @RequestParam("tipoArquivo") FileTypeEnum tipoArquivo) {
        byte[] contents = service.getFile(usuario, tipoArquivo);
        String filename = tipoArquivo.getNome() + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

}