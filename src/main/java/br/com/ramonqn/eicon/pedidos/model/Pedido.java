package br.com.ramonqn.eicon.pedidos.model;

import br.com.ramonqn.eicon.pedidos.dto.PedidoDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "PEDIDO",
    uniqueConstraints = @UniqueConstraint(columnNames = {"NUMERO_CONTROLE"}, name = "NUMERO_CONTROLE_IDX"))
public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "NUMERO_CONTROLE")
    private Long numeroControle;

    @NotNull
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;

    @NotNull
    @Column(name = "QUANTIDADE")
    private Integer quantidade = 1;

    @NotNull
    @Column(name = "CODIGO_CLIENTE")
    private Long codigoCliente;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    public Pedido comNumeroControle(Long numeroControle) {
        this.numeroControle = numeroControle;
        return this;
    }

    public Pedido comDataCadastro(Date dataCadastro) {
        if (dataCadastro != null) {
            this.dataCadastro = dataCadastro;
        }
        return this;
    }

    public Pedido comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Pedido comValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Pedido comQuantidade(Integer quantidade) {
        if (quantidade != null) {
            this.quantidade = quantidade;
        }
        return this;
    }

    public Pedido comCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }

    public Pedido comValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoDTO converterParaDTO() {
        return new PedidoDTO()
                .comNumeroControle(getNumeroControle())
                .comDataCadastro(getDataCadastro())
                .comNome(getNome())
                .comValor(getValor())
                .comQuantidade(getQuantidade())
                .comCodigoCliente(getCodigoCliente())
                .comValorTotal(getValorTotal());
    }
}
