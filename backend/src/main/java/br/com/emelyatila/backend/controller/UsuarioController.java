package br.com.emelyatila.backend.controller;

import br.com.emelyatila.backend.dto.UsuarioDTO;
import br.com.emelyatila.backend.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    //@Autowired
    final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        //ResponseEntity.status(HttpStatus.OK).body(usuarios)
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(("Usuário deletado com sucesso"));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id,dto));
    }

    @PutMapping("/{id}/imagem")
    public ResponseEntity<Object> updateImagem(@PathVariable Long id, @RequestBody UsuarioDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateImagem(id,dto));
    }



}
