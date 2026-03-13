package br.com.emelyatila.backend.controller;

import br.com.emelyatila.backend.dto.UsuarioDTO;
import br.com.emelyatila.backend.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoUsuarioController {

    Logger log = LogManager.getLogger(AutenticacaoUsuarioController.class);

    final UsuarioService usuarioService;

    public AutenticacaoUsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> registroUsuario(@RequestBody
                                                  @Validated(UsuarioDTO.UsuarioView.RegistroUsuarioPost.class)
                                                  @JsonView(UsuarioDTO.UsuarioView.RegistroUsuarioPost.class)
                                                  UsuarioDTO dto){
        log.info("POST registro - registrando novo usuário: {}", dto.getUsuario());

        if(usuarioService.existsByUsuario(dto.getUsuario())){
            log.warn("POST registro - usuário já existe: {}", dto.getUsuario());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO, USUARIO JÁ EXISTENTE!");
        }

        if (usuarioService.existsByEmail(dto.getEmail())){
            log.warn("POST registro - email já existe: {}", dto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO, EMAIL JÁ CADASTRADO!");
        }

        log.info("POST registro - usuário registrado com sucesso: {}", dto.getUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));

    }
}
