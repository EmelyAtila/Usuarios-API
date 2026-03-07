package br.com.emelyatila.backend.repository;

import br.com.emelyatila.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findAll();
}
