
## Projeto compras

### Backend

No backend foi utilizado Quarkus, MongoDB, RabbitMQ, Java 11.
Para rodar o projeto, assumo que o maven já está configurado, então a opção mais facil é acessar o local do projeto 'backend' e executar o seguinte comando:
```
mvn quarkus:dev
```
Acesse 'http://localhost:8080' já existe o frontend compilado dentro do projeto do backend assim é possível executar o projeto.
É necessário uma instalação do MongoDB na configuração de porta padrão.
É necessário uma instalação do RabbitMQ na configuração de porta padrão.

### Frontend
Foi utilizado Angular 8 no frontend junto com Bootstrap 4 e ngx-bootstrap.

Para executar o projeto entre na pasta do frontend e execute o seguinte comando (necessário ter o NodeJS instalado):
```
npm install
```
Depois execute o comando:
```
ng serve -o
```
Necessário executar o frontend na porta padrão 4200.