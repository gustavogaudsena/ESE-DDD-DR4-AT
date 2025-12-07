package br.com.infnet.PetFriends.transporte.domain;

import java.util.Objects;

public class Endereco {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String complemento;
    private String cep;

    public Endereco(String rua,
                    String bairro,
                    String cidade,
                    String estado,
                    Integer numero,
                    String complemento,
                    String cep) {

        if (rua == null || rua.isBlank()) throw new IllegalArgumentException("Rua obrigatória");
        if (bairro == null || bairro.isBlank()) throw new IllegalArgumentException("Bairro obrigatório");
        if (cidade == null || cidade.isBlank()) throw new IllegalArgumentException("Cidade obrigatória");
        if (estado == null || estado.isBlank()) throw new IllegalArgumentException("Estado obrigatória");
        if (numero == null || numero <= 0)  throw new IllegalArgumentException("Número inválido");
        if (cep.matches("\\d{5}-\\d{3}")) throw new IllegalArgumentException("CEP inválido (use formato 00000-000)");

        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado.toUpperCase();
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Endereco outro = (Endereco) obj;

        return rua.equals(outro.rua)
                && bairro.equals(outro.bairro)
                && cidade.equals(outro.cidade)
                && estado.equals(outro.estado)
                && numero.equals(outro.numero)
                && Objects.equals(complemento, outro.complemento)
                && cep.equals(outro.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua, bairro, cidade, estado, numero, complemento, cep);
    }
}
