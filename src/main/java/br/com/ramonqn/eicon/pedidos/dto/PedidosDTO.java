package br.com.ramonqn.eicon.pedidos.dto;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class PedidosDTO {

    @Valid
    private List<PedidoDTO> pedidos;
}
