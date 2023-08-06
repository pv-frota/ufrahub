package br.edu.ufra.integracao.sigaa.domain.controller;

import br.edu.ufra.ufrahub.integracao.sigaa.domain.model.Message;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Mensagens", description = "APIs REST relacionadas às mensagens de usuários para as coordenações de cursos")
@RequestMapping("/api/aluno/mensagem")
public class MessageController {

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @ApiOperation(value = "Enviar uma mensagem para o curso do usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mensagem enviada com sucesso"),
            @ApiResponse(code = 401, message = "Não Autorizado"),
    })
    @PostMapping
    public void sendMessage(@RequestBody Message mensagem) {
        service.save(mensagem);
    }

}