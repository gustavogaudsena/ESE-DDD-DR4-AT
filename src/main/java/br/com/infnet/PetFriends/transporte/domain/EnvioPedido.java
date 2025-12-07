package br.com.infnet.PetFriends.transporte.domain;

import br.com.infnet.PetFriends.transporte.infra.EnderecoConverter;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class EnvioPedido {

    private Long id;
    private Long pedidoId;
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private EnvioPedidoStatus status;

    private Date criadoEm;
    private Date atualizadoEm;

    @Convert(converter = EnderecoConverter.class)
    private Endereco enderecoEntrega;

    public EnvioPedido() {
    }

    public EnvioPedido(Long pedidoId, Endereco enderecoEntrega) {
        this.pedidoId = pedidoId;
        this.status = EnvioPedidoStatus.EM_TRANSITO;
        this.criadoEm = new Date();
        this.enderecoEntrega = enderecoEntrega;
    }

    public void entregarPedido() {
        if (status != EnvioPedidoStatus.EM_TRANSITO) {
            throw new IllegalStateException("Não é possível entregar um pedido que não está em transito.");
        }

        this.status = EnvioPedidoStatus.ENTREGUE;
        this.atualizadoEm = new Date();
    }

    public void devolverPedido() {
        if (status == EnvioPedidoStatus.EXTRAVIADO) {
            throw new IllegalStateException("Não é possível solicitar devolução de um pedido que foi extraviado");
        }

        this.status = EnvioPedidoStatus.DEVOLVIDO;
        this.atualizadoEm = new Date();

    }

    public void marcarExtraviado() {
        if (status != EnvioPedidoStatus.EM_TRANSITO) {
            throw new IllegalStateException("Não é possível marcar um pedido como extravido que não esteja em transito");
        }

        this.status = EnvioPedidoStatus.EXTRAVIADO;
        this.atualizadoEm = new Date();
    }

}
