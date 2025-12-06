package br.com.infnet.PetFriends.almoxarifado.domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "almoxarifado")
public class Almoxarifado {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ElementCollection
    @CollectionTable(name = "estoque")
    @MapKeyColumn(name = "produto_id")
    @Column(name = "quantidade")
    private Map<ProdutoId, Integer> estoque = new HashMap<>();

    @Embedded
    private Localizacao localizacao;

    protected Almoxarifado() {
    }

    public Almoxarifado(String nome, Localizacao localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void prepararItens(Map<ProdutoId, Integer> itensPedido) {
        itensPedido.forEach(this::baixarEstoque);
    }

    private void baixarEstoque(ProdutoId produtoId, Integer quantidade) {
        int quantidadeEmEstoque = estoque.getOrDefault(produtoId, 0);

        if (quantidade > quantidadeEmEstoque)
            throw new EstoqueInsuficiente("Estoque insuficiente. ProdutoId: " + produtoId.getValue() + " Quantidade: " + quantidade);

        estoque.put(produtoId, quantidadeEmEstoque - quantidade);
    }

}
