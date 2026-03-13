package br.com.emelyatila.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDoc {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Vendas Online — Área do Usuário")
                        .description("API RESTful para gerenciamento de usuários do sistema de vendas online.")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("emelyatila@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://sistema_vendas.com/api/licenca")));
    }
}