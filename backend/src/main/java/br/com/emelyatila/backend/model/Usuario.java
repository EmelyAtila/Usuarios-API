package br.com.emelyatila.backend.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Usuario {


    //Perguntar sobre UUID
    private Long usuarioID;

    private String usuario;

    private String email;

    private String senha;

    private String nomeCompleto;

    private StatusUsuario statusUsuario;

    private TipoUsuario tipoUsuario;

    private String imagemUrl;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;



}
