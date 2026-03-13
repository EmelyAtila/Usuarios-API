package br.com.emelyatila.backend.controller;

import br.com.emelyatila.backend.dto.UsuarioDTO;
import br.com.emelyatila.backend.exceptions.NotFoundException;
import br.com.emelyatila.backend.service.UsuarioService;
import br.com.emelyatila.backend.specifications.SpecificationsTemplate;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    Logger log = LogManager.getLogger(UsuarioController.class);

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(SpecificationsTemplate.UsuarioSpec spec, Pageable pageable) {
        log.info("GET findAll - listando usuários");
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.findAll(spec,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
        log.info("GET getUsuario - buscando usuário id: {}",id);
        return usuarioService.findById(id)
                .<ResponseEntity<Object>>map(dto ->
                        ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        log.info("DELETE  deleteUsuario - deletando usuário id: {}", id);
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(("Usuário deletado com sucesso"));
    }

    @PutMapping("/{id}/usuario")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id,
                                                @RequestBody
                                                @Validated(UsuarioDTO.UsuarioView.UsuarioPut.class)
                                                @JsonView(UsuarioDTO.UsuarioView.UsuarioPut.class)
                                                UsuarioDTO dto) {
        log.info("PUT updateUsuario - atualizando usuário id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id, dto));
    }

    @PutMapping("/{id}/imagem")
    public ResponseEntity<Object> updateImagem(@PathVariable Long id,
                                               @RequestBody
                                               @Validated(UsuarioDTO.UsuarioView.ImagemPut.class)
                                               @JsonView(UsuarioDTO.UsuarioView.ImagemPut.class)
                                               UsuarioDTO dto) {
        log.info("PUT updateImagem - atualizando imagem usuário id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateImagem(id, dto));
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Object> updateSenha(@PathVariable Long id,
                                              @RequestBody
                                              @Validated(UsuarioDTO.UsuarioView.SenhaPut.class)
                                              @JsonView(UsuarioDTO.UsuarioView.SenhaPut.class)
                                              UsuarioDTO dto) {
        log.info("PUT updateSenha - atualizando senha usuário id: {}", id);
        usuarioService.updateSenha(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso");
    }


}
