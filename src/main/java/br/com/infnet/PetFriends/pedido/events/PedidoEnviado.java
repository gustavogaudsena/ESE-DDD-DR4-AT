package br.com.infnet.PetFriends.pedido.events;

import java.time.Instant;
import java.util.Map;

public record PedidoEnviado(
        Long pedidoId,
        Long customerId,
        Map<Long, Integer> itensPedidos,
        Instant ts
) {}
