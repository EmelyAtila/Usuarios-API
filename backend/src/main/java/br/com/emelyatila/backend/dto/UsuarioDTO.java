package br.com.emelyatila.backend.dto;

import br.com.emelyatila.backend.model.StatusUsuario;
import br.com.emelyatila.backend.model.TipoUsuario;
import br.com.emelyatila.backend.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private Long id;
    private String usuario;
    private String email;
    private String nomeCompleto;
    private StatusUsuario statusUsuario;
    private TipoUsuario tipoUsuario;
    private String imagemUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;


    public UsuarioDTO(){}

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
