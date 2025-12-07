package br.com.infnet.PetFriends.almoxarifado.infra;

import br.com.infnet.PetFriends.almoxarifado.domain.Almoxarifado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmoxarifadoRepository extends JpaRepository<Almoxarifado, Long> {

}
