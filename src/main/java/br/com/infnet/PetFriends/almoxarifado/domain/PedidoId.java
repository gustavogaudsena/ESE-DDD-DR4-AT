package br.com.infnet.PetFriends.almoxarifado.domain;

public record PedidoId(Long value) {
    @Override
    public String toString() {
        return value.toString();
    }
}