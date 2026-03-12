package br.com.emelyatila.backend.service.impl;

import br.com.emelyatila.backend.dto.UsuarioDTO;
import br.com.emelyatila.backend.exceptions.NotFoundException;
import br.com.emelyatila.backend.model.StatusUsuario;
import br.com.emelyatila.backend.model.Usuario;
import br.com.emelyatila.backend.repository.UsuarioRepository;
import br.com.emelyatila.backend.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

//    @Override
//    public List<UsuarioDTO> findAll() {
//        return usuarioRepository.findAll()
//                .stream()
//                .map(UsuarioDTO::new)
//                .toList();
//    }

    @Override
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioDTO::new);
    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::new);
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        var usuario = new Usuario();

        //BeanUtils.copyProperties(dto,usuario);

        usuario.setUsuario(dto.getUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuario.setImagemUrl(dto.getImagemUrl());

        usuario.setStatusUsuario(StatusUsuario.ATIVO); // todo usuário nasce ATIVO

        //          usuarioRepository.save(usuario)
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuario.setStatusUsuario(dto.getStatusUsuario());

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO updateImagem(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));

        usuario.setImagemUrl(dto.getImagemUrl());
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void updateSenha(Long id, UsuarioDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // verificar senha antiga;
        if (!usuario.getSenha().equals((dto.getSenhaAntiga()))){
            throw new NotFoundException("Senha antiga incorreta");
        }

        usuario.setSenha(dto.getSenha());
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return usuarioRepository.existsByUsuario(usuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);

    }

}



