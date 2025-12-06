package br.com.infnet.PetFriends.almoxarifado.domain;

public class EstoqueInsuficiente extends IllegalStateException {

    public EstoqueInsuficiente(String message) {
        super(message);
    }
}
