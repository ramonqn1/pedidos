package br.com.ramonqn.eicon.pedidos.dto;

import br.com.ramonqn.eicon.pedidos.model.Pedido;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Data
public class PedidoDTO {

    @NotNull(message = "O número de controle não pode ser nulo!")
    private Long numeroControle;

    private Date dataCadastro;

    @NotBlank(message = "O nome do produto não pode estar vazio!")
    private String nome;

    @NotNull(message = "O valor do produto não pode ser nulo!")
    private BigDecimal valor;

    private Integer quantidade;

    @NotNull(message = "O código do cliente não pode ser nulo!")
    private Long codigoCliente;

    @Setter(AccessLevel.NONE)
    private BigDecimal valorTotal;

    public BigDecimal calcularValorTotal() {
        if (getQuantidade() > 5) {
            return calcularValorTotalComDesconto(5d);

        } else if (getQuantidade() >= 10) {
            return calcularValorTotalComDesconto(10d);
        }

        return calcularValorTotalSemDesconto();
    }

    private BigDecimal calcularValorTotalSemDesconto() {
        return valor.multiply(new BigDecimal(getQuantidade())).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularValorTotalComDesconto(Double porcentagem) {
        BigDecimal porcentagemDesconto = BigDecimal.valueOf(porcentagem / 100).setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorDesconto = calcularValorTotalSemDesconto().multiply(porcentagemDesconto);

        return calcularValorTotalSemDesconto().subtract(valorDesconto);
    }

    public Pedido converterParaModel() {
        return new Pedido()
                .comNumeroControle(getNumeroControle())
                .comDataCadastro(getDataCadastro())
                .comNome(getNome())
                .comValor(getValor())
                .comQuantidade(getQuantidade())
                .comCodigoCliente(getCodigoCliente())
                .comValorTotal(calcularValorTotal());
    }

    public PedidoDTO comNumeroControle(Long numeroControle) {
        this.numeroControle = numeroControle;
        return this;
    }

    public PedidoDTO comDataCadastro(Date dataCadastro) {
        if (dataCadastro != null) {
            this.dataCadastro = dataCadastro;
        }
        return this;
    }

    public PedidoDTO comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PedidoDTO comValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public PedidoDTO comQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public PedidoDTO comCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }

    public PedidoDTO comValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);
        return this;
    }
}
