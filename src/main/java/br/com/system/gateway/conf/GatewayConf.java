package br.com.system.gateway.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConf {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("clienteBusca", r -> r
                    .path("/clientes-v1/buscaCliente")
                        .uri("http://localhost:8083/"))
                .route("clienteSalvar",r -> r
                        .path("/clientes-v1/salvarCliente")
                            .uri("http://localhost:8083/"))
                .route("staffBusca", r -> r
                        .path("/staff-v1/buscaStaff")
                            .uri("http://localhost:8082/"))
                .route("staffSalvar", r -> r
                        .path("/staff-v1/salvarStaff")
                            .uri("http://localhost:8082/"))
                .route("Cep - viacep", r -> r
                        .path("/cep/v1/**")
                        .filters(f -> f.rewritePath("/cep/v1/(?<cep>.*)", "/ws/${cep}/json/"))
                            .uri("https://viacep.com.br"))
                .build();
    }
}
