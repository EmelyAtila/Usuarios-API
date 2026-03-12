package br.com.emelyatila.backend.service;

import br.com.emelyatila.backend.dto.UsuarioDTO;
import br.com.emelyatila.backend.specifications.SpecificationsTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDTO> findById(Long id);

    UsuarioDTO save(UsuarioDTO dto);

    UsuarioDTO update(Long id, UsuarioDTO dto);

    UsuarioDTO updateImagem(Long id, UsuarioDTO dto);

//    Page<UsuarioDTO> findAll(Pageable pageable);

    Page<UsuarioDTO> findAll(SpecificationsTemplate.UsuarioSpec spec, Pageable pageable);

    void updateSenha(Long id, UsuarioDTO dto);

    void delete(Long id);

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

}
