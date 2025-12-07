package br.com.infnet.PetFriends.transporte.infra.service;


import br.com.infnet.PetFriends.transporte.domain.Endereco;
import br.com.infnet.PetFriends.transporte.domain.EnvioPedido;
import br.com.infnet.PetFriends.transporte.infra.EnvioPedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvioPedidoService {

    private final EnvioPedidoRepository repository;
    @Autowired
    private transient StreamBridge streamBridge;


    @Transactional
    public void iniciarEnvio(Long pedidoId, Long customerId) {
        Endereco enderecoEntrega = buscarEnderecoDeEntrega(customerId);
        EnvioPedido envioPedido = new EnvioPedido(pedidoId, customerId, enderecoEntrega);

        this.repository.save(envioPedido);
    }

    private Endereco buscarEnderecoDeEntrega(Long customerId) {
        // Busca o endereço de entrega do cliente em PetFriends_Clientes.

        return new Endereco("Rua de Teste", "Centro", "Cidade de Teste", "MG", 1, null, "01000-000");
    }

}
