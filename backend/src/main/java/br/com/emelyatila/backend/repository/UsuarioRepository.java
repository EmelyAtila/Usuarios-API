package br.com.emelyatila.backend.repository;

import br.com.emelyatila.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

}
