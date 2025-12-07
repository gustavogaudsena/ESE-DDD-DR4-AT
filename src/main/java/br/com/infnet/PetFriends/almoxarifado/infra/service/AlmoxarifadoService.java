package br.com.infnet.PetFriends.almoxarifado.infra.service;

import br.com.infnet.PetFriends.almoxarifado.domain.Almoxarifado;
import br.com.infnet.PetFriends.almoxarifado.domain.EstoqueInsuficiente;
import br.com.infnet.PetFriends.almoxarifado.events.CancelarPedido;
import br.com.infnet.PetFriends.almoxarifado.events.DespacharPedido;
import br.com.infnet.PetFriends.almoxarifado.infra.AlmoxarifadoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final AlmoxarifadoRepository repository;
    @Autowired
    private transient StreamBridge streamBridge;


    public void prepararPedido(Long pedidoId, Long customerId, Map<Long, Integer> itensPedido) {

        try {
            Almoxarifado almoxarifado = this.localizarAlmoxarifadoMaisProximo(customerId);
            almoxarifado.prepararItens(itensPedido);

            streamBridge.send("depacharPedido-out-0", new DespacharPedido(pedidoId, almoxarifado.getId()));

        } catch (EstoqueInsuficiente e) {
            // Poderia buscar um outro almoxarifado para atender o pedido.
            // Na implementação atual, o pedido é cancelado via evento.

            streamBridge.send("cancelarpedido-out-0", new CancelarPedido(pedidoId, e.getMessage()));
        }

    }

    private Almoxarifado localizarAlmoxarifadoMaisProximo(Long customerId) {
        // Localiza o almoxarifado mais próximo com base no endereço do cliente.

        return new Almoxarifado();
    }
}
