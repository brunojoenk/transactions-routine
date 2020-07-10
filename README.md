## Rotina de transações
- Link da aplicação: https://transactions-routine.herokuapp.com/

#### Para fazer para criar a aplicação em sua conta do Heroku, basta clicar
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

#### Para informações da API
https://transactions-routine.herokuapp.com/swagger-ui.html

#### Tecnologias
- [Java 11](https://docs.oracle.com/en/java/javase/11/);
- [Sping-Boot 2.3.0](https://spring.io/projects/spring-boot);
- [Apache Maven 3.6](https://maven.apache.org/ref/3.6.0/);
- [Banco H2](https://www.h2database.com/html/main.html);
- [JUnit 5](https://junit.org/junit5/);
- [Lombok](https://projectlombok.org/);

#### Caso queira montar a imagem docker da aplicação
```sh
mvn clean install
docker build -t transactions-routine .
```

#### Rodar imagem docker
```sh
docker run -it -p 8080:8080 --name transactions-routine-container transactions-routine
```

#### Acessar app após rodar a imagem docker
- http://localhost:8080
- http://localhost:8080/h2-console - Acesso a base
