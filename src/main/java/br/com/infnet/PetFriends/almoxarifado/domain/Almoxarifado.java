package br.com.infnet.PetFriends.almoxarifado.domain;

import br.com.infnet.PetFriends.almoxarifado.infra.LocalizacaoConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "almoxarifado")
@Getter
public class Almoxarifado {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    @ElementCollection
    @CollectionTable(name = "estoque")
    @MapKeyColumn(name = "produto_id")
    @Column(name = "quantidade")
    private Map<Long, Integer> estoque = new HashMap<>();

    @Convert(converter = LocalizacaoConverter.class)
    private Localizacao localizacao;

    public Almoxarifado() {
    }

    public Almoxarifado(String nome, Localizacao localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void prepararItens(Map<Long, Integer> itensPedido) {
        itensPedido.forEach(this::baixarEstoque);
    }

    private void baixarEstoque(Long produtoId, Integer quantidade) {
        int quantidadeEmEstoque = estoque.getOrDefault(produtoId, 0);

        if (quantidade > quantidadeEmEstoque)
            throw new EstoqueInsuficiente("Estoque insuficiente. ProdutoId: " + produtoId + " Quantidade: " + quantidade);

        estoque.put(produtoId, quantidadeEmEstoque - quantidade);
    }

}
