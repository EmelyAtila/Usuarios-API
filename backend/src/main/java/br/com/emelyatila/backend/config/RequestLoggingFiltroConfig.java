package br.com.emelyatila.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFiltroConfig {

    @Bean
    public CommonsRequestLoggingFilter loggingFilter(){
        var filter = new CommonsRequestLoggingFilter();

        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setAfterMessagePrefix("SOLICITAÇÃO DE DADOS: ");
        filter.setHeaderPredicate(header -> !header.equalsIgnoreCase("authorization"));

        return filter;
    }
}
