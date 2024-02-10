# Projeto Boardcamp API (Back-end) em Java

Projeto de API de um sistema para locação de jogos de tabuleiro
Link do Deploy: https://boardcamp-java-d83a.onrender.com

## Sobre

Projeto do back-end de API de um site para gestão de uma locadora de jogos de tabuleiro

### Rotas

- POST /games (Cria o registro de um novo jogo)
- GET /games (Retornar a lista de todos os jogos registrados)
- POST /customers (Cria o registro de um cliente)
- GET /customers/:id (Busca no sistema um cliente pelo código do id)
- POST /rentals (Registra uma nova locação)
- GET /rentals (Mostra todas as locações já feitas)
- PUT /rentals/:id/return (Registra a devolução de um jogo no sistema)

## Como rodar o projeto 

1. Clone esse repositório
2. Crie um banco de dados com o Postgresql
4. Na raíz do projeto, crie um arquivo ".env" com as variáveis de ambiente "DB_URL" (link do banco de dados), "DB_USERNAME" (nome do usuário) e "DB_PASSWORD" (senha), conforme o arquivo ".env.example"
5. Agora é só rodar o programa