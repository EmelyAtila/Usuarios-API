package br.com.emelyatila.backend.service;

import br.com.emelyatila.backend.model.StatusUsuario;
import br.com.emelyatila.backend.model.TipoUsuario;
import br.com.emelyatila.backend.model.Usuario;
import br.com.emelyatila.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

}
