package br.com.infnet.PetFriends.pedido.events;

import java.time.Instant;

public record PedidoDespachado(
        Long pedidoId,
        Long customerId,
        Instant ts
) {}
