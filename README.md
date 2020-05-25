## Rotina de transações
- Link da aplicação: https://transactions-routine.herokuapp.com/

#### Para fazer para criar a aplicação em sua conta do Heroku, basta clicar
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

#### Para informações da API
https://transactions-routine.herokuapp.com/swagger-ui.html

#### Tecnologias
- Java 11;
- Spring-Boot 2.3.0
- Maven 3.6;
- Banco H2;
- JUnit 5;
- Lombok;

#### Caso queira montar a imagem docker da aplicação
```sh
docker build -t transactions-routine .
```

#### Rodar imagem docker
```sh
docker run -it -p 8080:8080 --name transactions-routine-container transactions-routine
```
