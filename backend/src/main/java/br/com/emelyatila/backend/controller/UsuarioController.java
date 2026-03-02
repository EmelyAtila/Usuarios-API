package br.com.emelyatila.backend.controller;

import br.com.emelyatila.backend.model.Usuario;
import br.com.emelyatila.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> xxxx() {
        return usuarioService.listarUsuarios();
    }
}
