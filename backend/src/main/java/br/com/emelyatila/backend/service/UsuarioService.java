package br.com.emelyatila.backend.service;

import br.com.emelyatila.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> findAll();

    Optional<UsuarioDTO> findById(Long id);

    UsuarioDTO save(UsuarioDTO dto);

    UsuarioDTO update(Long id, UsuarioDTO dto);

    UsuarioDTO updateImagem(Long id, UsuarioDTO dto);

    void updateSenha(Long id, UsuarioDTO dto);

    void delete(Long id);

    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);

}
