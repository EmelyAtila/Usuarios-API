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

//    @Autowired
//    UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        Usuario u1 = new Usuario();
        u1.setUsuarioID(1L);
        u1.setUsuario("emely");
        u1.setEmail("emely@email.com");
        u1.setSenha("123456");
        u1.setNomeCompleto("Emely Atila");
        u1.setStatusUsuario(StatusUsuario.ATIVO);
        u1.setTipoUsuario(TipoUsuario.VENDEDOR);
        u1.setImagemUrl("https://meusite.com/imagens/emely.png");
        u1.setDataCriacao(LocalDateTime.now());
        u1.setDataAtualizacao(LocalDateTime.now());

        Usuario u2 = new Usuario();
        u2.setUsuarioID(2L);
        u2.setUsuario("joao");
        u2.setEmail("joao@email.com");
        u2.setSenha("abcdef");
        u2.setNomeCompleto("João Pereira");
        u2.setStatusUsuario(StatusUsuario.ATIVO);
        u2.setTipoUsuario(TipoUsuario.ESTOQUISTA);
        u2.setImagemUrl("https://meusite.com/imagens/joao.png");
        u2.setDataCriacao(LocalDateTime.now());
        u2.setDataAtualizacao(LocalDateTime.now());

        return Arrays.asList(u1, u2);
    }

}
