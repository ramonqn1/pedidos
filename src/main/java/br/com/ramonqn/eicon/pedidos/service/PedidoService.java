package br.com.ramonqn.eicon.pedidos.service;

import br.com.ramonqn.eicon.pedidos.dto.PedidoDTO;
import br.com.ramonqn.eicon.pedidos.dto.PedidoQueryDTO;
import br.com.ramonqn.eicon.pedidos.dto.PedidosDTO;
import br.com.ramonqn.eicon.pedidos.exception.BusinessException;
import br.com.ramonqn.eicon.pedidos.model.Pedido;
import br.com.ramonqn.eicon.pedidos.repository.PedidoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<PedidoDTO> novoPedido(PedidosDTO pedidos) {
        validarNovoPedido(pedidos);

        List<Pedido> pedidosModel = pedidos.getPedidos()
                .stream()
                .map(PedidoDTO::converterParaModel)
                .collect(Collectors.toList());

        return repository.saveAll(pedidosModel)
                .stream()
                .map(Pedido::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<PedidoDTO> buscarPedidos(PedidoQueryDTO pedidoQuery) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("quantidade")
                .withIgnoreNullValues();
        Example<Pedido> example = Example.of(pedidoQuery.converterParaModel(), matcher);

        return repository.findAll(example)
                .stream()
                .map(Pedido::converterParaDTO)
                .collect(Collectors.toList());
    }

    private void validarNovoPedido(PedidosDTO pedidos) {
        if (pedidos.getPedidos().size() > 10) {
            throw new BusinessException("A quantidade de pedidos não pode ser maior que 10!");
        }

        for (PedidoDTO pedido : pedidos.getPedidos()) {
            if (repository.existsByNumeroControle(pedido.getNumeroControle())) {
                throw new BusinessException("O número de controle " + pedido.getNumeroControle() + " já existe no sistema!");
            }
        }
    }
}
