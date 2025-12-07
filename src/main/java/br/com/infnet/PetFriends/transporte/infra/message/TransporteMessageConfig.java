package br.com.infnet.PetFriends.transporte.infra.message;


import br.com.infnet.PetFriends.transporte.infra.service.EnvioPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.function.Consumer;

@Configuration
public class TransporteMessageConfig {

    private final EnvioPedidoService service;

    public TransporteMessageConfig(EnvioPedidoService service) {
        this.service = service;
    }

    @Bean
    public Consumer<PedidoDespachado> pedidoDespachado() {
        return e -> {
            this.service.iniciarEnvio(e.pedidoId, e.customerId);
        };
    }

    public record PedidoDespachado(
            Long pedidoId,
            Long customerId,
            Instant ts
    ) {}
}