## Rotina de transações
- Link da aplicação: https://transactions-routine.herokuapp.com/

#### Para fazer deploy de uma nova versão, basta clicar
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy?template=https://github.com/brunojoenk/transactions-routine/tree/master)

#### Para informações da API
https://transactions-routine.herokuapp.com/swagger-ui.html

#### Tecnologias
- Java 11;
- Spring-Boot 2.3.0
- Maven 3.6;
- Banco H2;
- JUnit 5;
- Lombok;

#### Rodar imagem docker
```sh
docker run -it -p 8080:8080 --name transactions-routine-container transactions-routine
```
