package br.com.infnet.PetFriends.almoxarifado.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoId {

    @Column(name = "produto_id")
    private Long value;

    protected ProdutoId() {
    }

    public ProdutoId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProdutoId outro = (ProdutoId) obj;
        return value != null ? value.equals(outro.value) : outro.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}