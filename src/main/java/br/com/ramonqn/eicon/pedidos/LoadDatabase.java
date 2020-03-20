package br.com.ramonqn.eicon.pedidos;

import br.com.ramonqn.eicon.pedidos.dto.PedidoDTO;
import br.com.ramonqn.eicon.pedidos.dto.PedidosDTO;
import br.com.ramonqn.eicon.pedidos.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(PedidoService pedidoService) {
        PedidoDTO pedido1 = new PedidoDTO()
                .comNumeroControle(1L)
                .comNome("Produto 1")
                .comValor(BigDecimal.valueOf(2.50))
                .comQuantidade(2)
                .comCodigoCliente(1L);

        Instant dataDeOntemInstant = LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant();
        PedidoDTO pedido2 = new PedidoDTO()
                .comNumeroControle(2L)
                .comNome("Produto 2")
                .comDataCadastro(Date.from(dataDeOntemInstant))
                .comValor(BigDecimal.valueOf(100))
                .comQuantidade(10)
                .comCodigoCliente(2L);

        PedidosDTO pedidosDTO = new PedidosDTO();
        pedidosDTO.setPedidos(Arrays.asList(pedido1, pedido2));

        return args -> {
            log.info("Salvando " + pedidoService.novoPedido(pedidosDTO));
        };
    }
}
