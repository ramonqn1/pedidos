package br.com.ramonqn.eicon.pedidos.dto;

import br.com.ramonqn.eicon.pedidos.model.Pedido;
import lombok.Getter;

import java.util.Date;

@Getter
public class PedidoQueryDTO {

    private Long numeroControle;
    private Date dataCadastro;
    private Long codigoCliente;

    public Pedido converterParaModel() {
        Pedido pedido = new Pedido()
                .comNumeroControle(getNumeroControle())
                .comDataCadastro(getDataCadastro())
                .comCodigoCliente(getCodigoCliente());

        if (getDataCadastro() == null) {
            pedido.setDataCadastro(null);
        }

        pedido.setQuantidade(null);

        return pedido;
    }

    public PedidoQueryDTO comNumeroControle(Long numeroControle) {
        this.numeroControle = numeroControle;
        return this;
    }

    public PedidoQueryDTO comDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public PedidoQueryDTO comCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }
}
