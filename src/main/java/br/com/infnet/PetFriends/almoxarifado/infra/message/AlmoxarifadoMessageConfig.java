package br.com.infnet.PetFriends.almoxarifado.infra.message;


import br.com.infnet.PetFriends.almoxarifado.infra.service.AlmoxarifadoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class AlmoxarifadoMessageConfig {

    private final AlmoxarifadoService service;

    public AlmoxarifadoMessageConfig(AlmoxarifadoService service) {
        this.service = service;
    }

    @Bean
    public Consumer<PedidoEnviado> pedidoEnviado() {
        return e -> {
            this.service.prepararPedido(e.pedidoId, e.customerId, e.itensPedidos);
        };
    }

    public record PedidoEnviado(
            Long pedidoId,
            Long customerId,
            Map<Long, Integer> itensPedidos,
            Instant ts
    ) {}

}
