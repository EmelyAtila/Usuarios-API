package br.com.emelyatila.backend.repository;
import br.com.emelyatila.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);

}
