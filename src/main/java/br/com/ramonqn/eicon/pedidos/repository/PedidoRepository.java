package br.com.ramonqn.eicon.pedidos.repository;

import br.com.ramonqn.eicon.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    boolean existsByNumeroControle(Long numeroControle);
}
