package br.com.infnet.PetFriends.transporte.infra;

import br.com.infnet.PetFriends.transporte.domain.EnvioPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioPedidoRepository extends JpaRepository<EnvioPedido, Long> {
}
