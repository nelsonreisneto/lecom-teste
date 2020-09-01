# Projeto de cadastro de clientes e serviços.
Esse projeto é utilizado para cadastro de clientes (Empresas) e tarefas (Serviços) para se calcular valores de cada tarefa para cada tipo de cliente.

#Tecnologias
 - Java 8
 - Spring 2.3.3.RELEASE
 - Lombok
 - MySql
 
 #Endpoints
  - Cliente
    - Inserir: http://localhost:8080/cliente/insere POST
    - Alterar: http://localhost:8080/cliente/altera PUT
    - Apagar: http://localhost:8080/cliente/apaga DELETE
    - Buscar 1: http://localhost:8080/cliente/busca/{id} GET
    - Buscar todos: http://localhost:8080/cliente/busca-todos GET
    
   - Serviços
     - Inserir: http://localhost:8080/tarefa/insere POST
     - Alterar: http://localhost:8080/tarefa/altera PUT
     - Apagar: http://localhost:8080/tarefa/apaga DELETE
     - Buscar 1: http://localhost:8080/tarefa/busca/{id} GET
     - Buscar todos: http://localhost:8080/tarefa/busca-todos GET
   
   - Historico e calculo
     - Criar ordem de serviço:http://localhost:8080/historico/criaOrdem POST
     - Exibir historico: http://localhost:8080/historico/exibit-historico/{nomeCliente} GET
     
#Instruções
Para executar deve abrir o terminal na parta do projeto e executar o comando mvn clean install. Após isso deve ir até a pasta /target e executar java -jar cadastro-tarefas-0.0.1-SNAPSHOT.jar.
Dentro do Postman pode-se utilizar das requisições para ter acesso aos end-points.
Os valores de JSON são:

 - Cliente:
  {
      "nome":"Nelson Reis Neto",
      "documento":"00000000000",
      "tipoDocumentos":"CPF",
      "clienteOuro":true,
      "clientePrata":false
  }
  
 - Tarefa:
   {
       "nome":"Nelson",
       "valor":120.00
   }
  
  - Ordem de serviço:
   {
       "nomeCliente":"Nelson Reis Neto",
       "nomeTarefa":"Reis",
       "dtInicio":"2020-08-31T10:15:30",
       "dtFim":"2020-09-30T10:15:30"
   }