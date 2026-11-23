# Sistema de Delivery — Publish–Subscribe

Projeto desenvolvido em Java 21 com Maven para demonstrar o padrão arquitetural Publish–Subscribe e o processamento concorrente de pedidos.

## Objetivo

Simular o fluxo de pedidos de um sistema de delivery. Um Publisher publica eventos sobre as mudanças de situação dos pedidos, e diferentes Subscribers recebem apenas as notificações relevantes para suas responsabilidades.

## Funcionalidades demonstradas

- Criação de 20 pedidos com clientes, endereços, produtos e valores variados.
- Distribuição dos pedidos entre 3 restaurantes.
- Cadastro de 5 entregadores e atribuição aos pedidos que ficam prontos.
- Publicação de eventos com `SubmissionPublisher`.
- Subscribers independentes para clientes, restaurantes e entregadores.
- Filtragem das notificações por tipo de evento e responsável.
- Processamento concorrente dos pedidos com `ExecutorService`.
- Validação das mudanças de situação e das regras de cancelamento.
- Relatório final por situação, restaurante e entregador.

## Organização do projeto

- `model`: entidades, situações e eventos do domínio.
- `publisher`: componente responsável por publicar eventos.
- `subscriber`: consumidores que recebem e filtram os eventos.
- `service`: criação dos pedidos, regras de negócio, conversão de situações em eventos e coordenação da simulação.
- `Main`: ponto de entrada da aplicação.

## Como executar no NetBeans

1. Extraia o arquivo ZIP.
2. Abra no NetBeans a pasta que contém o `pom.xml`.
3. Confirme que o projeto está usando JDK 21.
4. Execute **Clean and Build**.
5. Execute a classe `delivery.Main`.

Também é possível executar pelo terminal, na pasta do projeto:

```bash
mvn clean package
mvn exec:java
```

## Fluxo resumido

1. `Main` inicia `DeliverySimulation`.
2. `DeliverySimulation` cria os pedidos, o Publisher e registra os Subscribers.
3. `SimulacaoPedidos` processa os pedidos em uma pool de quatro threads.
4. `PedidoService` valida e altera a situação de cada pedido.
5. `PedidoEventoService` cria o evento correspondente.
6. `PedidoPublisher` publica o evento.
7. Os Subscribers recebem os eventos e filtram aqueles que lhes interessam.
8. Ao final, o Publisher é encerrado e é exibido o relatório da simulação.
