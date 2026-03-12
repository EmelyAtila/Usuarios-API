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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(SpecificationsTemplate.UsuarioSpec spec, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.findAll(spec,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
        return usuarioService.findById(id)
                .<ResponseEntity<Object>>map(dto ->
                        ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(("Usuário deletado com sucesso"));
    }

    @PutMapping("/{id}/usuario")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id,
                                                @RequestBody
                                                @Validated(UsuarioDTO.UsuarioView.UsuarioPut.class)
                                                @JsonView(UsuarioDTO.UsuarioView.UsuarioPut.class)
                                                UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id, dto));
    }

    @PutMapping("/{id}/imagem")
    public ResponseEntity<Object> updateImagem(@PathVariable Long id,
                                               @RequestBody
                                               @Validated(UsuarioDTO.UsuarioView.ImagemPut.class)
                                               @JsonView(UsuarioDTO.UsuarioView.ImagemPut.class)
                                               UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateImagem(id, dto));
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Object> updateSenha(@PathVariable Long id,
                                              @RequestBody
                                              @Validated(UsuarioDTO.UsuarioView.SenhaPut.class)
                                              @JsonView(UsuarioDTO.UsuarioView.SenhaPut.class)
                                              UsuarioDTO dto) {
        usuarioService.updateSenha(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso");
    }


}
