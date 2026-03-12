package br.com.emelyatila.backend.dto;

import br.com.emelyatila.backend.model.StatusUsuario;
import br.com.emelyatila.backend.model.TipoUsuario;
import br.com.emelyatila.backend.model.Usuario;
import br.com.emelyatila.backend.validates.SenhaConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    public interface UsuarioView {
        interface RegistroUsuarioPost {
        }

        interface UsuarioPut {
        }

        interface ImagemPut {
        }

        interface SenhaPut {
        }
    }

    @JsonView({UsuarioView.RegistroUsuarioPost.class,
            UsuarioView.UsuarioPut.class,
            UsuarioView.ImagemPut.class})
    private Long id;

    @NotBlank(groups = UsuarioView.RegistroUsuarioPost.class,
            message = "O usuário é obrigatório")
    @Size(groups = UsuarioView.RegistroUsuarioPost.class, min = 3, max = 50,
            message = "O usuário deve ter entre 3 e 50 caracteres")
    @JsonView(UsuarioView.RegistroUsuarioPost.class)
    private String usuario;

    @NotBlank(groups = UsuarioView.RegistroUsuarioPost.class,
            message = "O e-mail é obrigatório")
    @Email(groups = UsuarioView.RegistroUsuarioPost.class,
            message = "E-mail inválido")
    @JsonView(UsuarioView.RegistroUsuarioPost.class)
    private String email;

    @SenhaConstraint(groups = {UsuarioView.RegistroUsuarioPost.class,
            UsuarioView.SenhaPut.class})
    @JsonView({UsuarioView.RegistroUsuarioPost.class, UsuarioView.SenhaPut.class})
    private String senha;

    @SenhaConstraint(groups = UsuarioView.SenhaPut.class)
    @JsonView(UsuarioView.SenhaPut.class)
    private String senhaAntiga;

    @NotBlank(groups = {UsuarioView.RegistroUsuarioPost.class,
            UsuarioView.UsuarioPut.class}, message = "O nome completo é obrigatório")
    @JsonView({UsuarioView.RegistroUsuarioPost.class, UsuarioView.UsuarioPut.class})
    private String nomeCompleto;

    @JsonView({UsuarioView.RegistroUsuarioPost.class, UsuarioView.UsuarioPut.class})
    private StatusUsuario statusUsuario;

    @JsonView({UsuarioView.RegistroUsuarioPost.class, UsuarioView.UsuarioPut.class})
    private TipoUsuario tipoUsuario;

    @NotBlank(groups = UsuarioView.ImagemPut.class, message = "Informe a URL da imagem")
    @JsonView(UsuarioView.ImagemPut.class)
    private String imagemUrl;

    @JsonView({UsuarioView.RegistroUsuarioPost.class,
            UsuarioView.UsuarioPut.class,
            UsuarioView.ImagemPut.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonView({UsuarioView.RegistroUsuarioPost.class,
            UsuarioView.UsuarioPut.class,
            UsuarioView.ImagemPut.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;


    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.usuario = entity.getUsuario();
        this.email = entity.getEmail();
        this.nomeCompleto = entity.getNomeCompleto();
        this.statusUsuario = entity.getStatusUsuario();
        this.tipoUsuario = entity.getTipoUsuario();
        this.imagemUrl = entity.getImagemUrl();
        this.dataCriacao = entity.getDataCriacao();
        this.dataAtualizacao = entity.getDataAtualizacao();
    }
}
