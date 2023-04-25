package br.edu.ufra.integracao.sigaa.domain.controller;

import br.edu.ufra.autenticationufrapp.domain.service.AuthenticationService;
import br.edu.ufra.autenticationufrapp.infrastructure.dto.request.LoginRequestDTO;
import br.edu.ufra.autenticationufrapp.infrastructure.dto.response.JwtResponseDTO;
import br.edu.ufra.integracao.sigaa.domain.model.User;
import br.edu.ufra.integracao.sigaa.domain.service.UserService;
import br.edu.ufra.integracao.sigaa.infrastructure.security.dto.response.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Autenticação", description = "APIs REST relacionadas a autenticação do usuário")
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @ApiOperation(value = "Autenticar as informações de login informadas", response = byte.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autenticação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não Autorizado - Login ou senha incorretos"),
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Validated @RequestBody LoginRequestDTO loginRequest) {
        JwtResponseDTO jwt = authenticationService.authenticate(loginRequest);
        User user = userService.findByUsername(loginRequest.getUsername());

        return ResponseEntity.ok(new UserResponseDTO(user, jwt));
    }

}