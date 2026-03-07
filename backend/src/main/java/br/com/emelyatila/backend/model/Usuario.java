package br.com.emelyatila.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @Email(message = "E-mail inválido")
    private String email;

    //testar pra segurança da senha
    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "nomeCompleto", length = 150)
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_usuario", nullable = false)
    private StatusUsuario statusUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape>String, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;


}
