# pedidos-eicon
Aplicação em Spring Boot que expõe um serviço REST de pedidos que podem ser enviados ou visualizados no formato JSON ou XML.<br>
Para inicializar a aplicação rodar o comando `./mvnw spring-boot:run`
### Buscar pedidos cadastrados
**Todos pedidos**<br>
http://localhost:8080/pedidos

**Filtro por número de controle**<br>
http://localhost:8080/pedidos?numeroControle={id do cliente}<br>
Exemplo: http://localhost:8080/pedidos?numeroControle=1

**Filtro por data de cadastro**<br>
http://localhost:8080/pedidos?dataCadastro={data com hora no formato ISO}<br>
Exemplo: http://localhost:8080/pedidos?dataCadastro=2020-03-20T14:27:08.564-03:00<br>

**Filtro por cliente**<br>
http://localhost:8080/pedidos?cliente={código do cliente}<br>
Exemplo: http://localhost:8080/pedidos?cliente=1

**Todos filtros**<br>
http://localhost:8080/pedidos?numeroControle=1&dataCadastro=2020-03-20T14:27:08.564-03:00&cliente=1

**XML**<br>
Para receber os dados no formato XML colocar o seguinte header no request: `Accept:application/xml`

### Enviar pedidos
**JSON**<br>
Enviar request POST para o endpoint http://localhost:8080/pedidos com o seguinte corpo na requisição:
```
{
  "pedidos": [
    {
      "numeroControle": 3,
      "nome": "Novo produto 3",
      "valor": 50.50,
      "quantidade": 5,
      "codigoCliente": 25
    },{
      "numeroControle": 4,
      "nome": "Novo produto 4",
      "valor": 10.20,
      "quantidade": 10,
      "codigoCliente": 34
    }
  ]
}
```
**XML**<br>
Colocar o seguinte header no request: `Accept:application/xml`<br>
Enviar request POST para o endpoint http://localhost:8080/pedidos com o seguinte corpo na requisição:
```
<PedidosDTO>
    <pedidos>
        <pedidos>
            <numeroControle>3</numeroControle>
            <dataCadastro>2020-03-20T11:52:44.916-0300</dataCadastro>
            <nome>Produto 3</nome>
            <valor>2.50</valor>
            <quantidade>5</quantidade>
            <codigoCliente>55</codigoCliente>
        </pedidos>
        <pedidos>
            <numeroControle>220</numeroControle>
            <dataCadastro>2020-03-19T11:52:44.452-0300</dataCadastro>
            <nome>Produto 4</nome>
            <valor>100.00</valor>
            <quantidade>10</quantidade>
            <codigoCliente>25</codigoCliente>
        </pedidos>
    </pedidos>
</PedidosDTO>
```
