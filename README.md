# Delivery Pub/Sub



MVP de um sistema de delivery desenvolvido em Java utilizando a arquitetura Publish–Subscribe por meio da API java.util.concurrent.Flow.



##### Tecnologias utilizadas



Java 17 ou superior;



Maven;



Java Flow API;



SubmissionPublisher.



##### Pré-requisitos



Antes de executar o projeto, é necessário instalar:



JDK 17 ou superior;



Maven;



Git.



##### Configuração e execução



1\. Clonar o repositório: git clone https://github.com/Eduemery/delivery-pub-sub.git



2\. Acessar o diretório: cd delivery-pub-sub



3\. Compilar o projeto: mvn clean compile



4\. Executar a aplicação: mvn exec:java



##### Funcionamento



A aplicação simula o fluxo de um pedido de delivery utilizando a arquitetura Publish–Subscribe.



Os eventos publicados pelo sistema são recebidos pelos respectivos Subscribers:



ClienteSubscriber



RestauranteSubscriber



EntregadorSubscriber



Cada componente recebe e processa os eventos de acordo com sua responsabilidade

