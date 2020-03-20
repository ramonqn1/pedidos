package br.com.ramonqn.eicon.pedidos.controller;

import br.com.ramonqn.eicon.pedidos.dto.PedidoDTO;
import br.com.ramonqn.eicon.pedidos.dto.PedidoQueryDTO;
import br.com.ramonqn.eicon.pedidos.dto.PedidosDTO;
import br.com.ramonqn.eicon.pedidos.service.PedidoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping(value = "/pedidos")
    public List<PedidoDTO> todos(@RequestParam(required = false) Long numeroControle,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataCadastro,
                              @RequestParam(required = false) Long codigoCliente) {

        PedidoQueryDTO pedidoQuery = new PedidoQueryDTO()
                .comNumeroControle(numeroControle)
                .comDataCadastro(dataCadastro)
                .comCodigoCliente(codigoCliente);

        return service.buscarPedidos(pedidoQuery);
    }

    @PostMapping(value = "/pedidos")
    public List<PedidoDTO> novoPedido(@Valid @RequestBody PedidosDTO pedidos) {
        return service.novoPedido(pedidos);
    }
}
