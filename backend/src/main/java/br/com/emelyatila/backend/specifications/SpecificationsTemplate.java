package br.com.emelyatila.backend.specifications;

import br.com.emelyatila.backend.model.Usuario;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationsTemplate {

    @And({
            @Spec(path = "tipoUsuario", spec = Equal.class),
            @Spec(path = "statusUsuario", spec = Equal.class),
            @Spec(path = "email", spec = Like.class),
            @Spec(path = "usuario", spec = Like.class),
            @Spec(path = "nomeCompleto", spec = LikeIgnoreCase.class)
    })

    public interface UsuarioSpec extends Specification<Usuario>{}
}
