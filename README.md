# [Deploy](https://edhet.github.io/ChocolateStore-Frontend/)

> [!WARNING]  
> EN 🇺🇸: Due to lack of funds to pay the hosting for the application, the Back-end has a cold start when not used for 30 minutes, taking around 3 minutes to turn on.
> 
> PT 🇧🇷: Devido à falta de verba para o pagamento do hosting da aplicação, o Back-end está com um cold start caso não seja utilizado por 30 minutos, levando cerca de 3 minutos para ligar.

> [!IMPORTANT]  
> The project is available in portuguese only.

# Project Overview

## EN 🇺🇸

Fictional Responsive Chocolate Store App developed in collaboration with a designer. Having a landing page, user registering, buying and cart system. Made as SPA using Angular and REST APIs in Spring Boot, with PostgreSQL Relational Database and Deploy made with Docker and GH Actions.

## PT 🇧🇷

App de Loja de Chocolates Responsiva fictícia desenvolvida em colaboração com designer. Possuíndo uma landing Page, cadastro de usuários, sistema de compras e carrinho. Feita como SPA usando Angular e APIs REST no Spring Boot, com Banco de dados relacional PostgreSQL e Deploy feito usando Docker e GH Actions.

# Tech Overview
## [Back-end](https://github.com/Edhet/ChocolateStore-Backend/)

### Overview

The Back-end is a Spring Boot application that consists in multiple Spring MVC RESTfull APIs endpoints for authenticating users through JWTs and manipulating the 5 JPA Entities that map a PostgreSQL Relational Database.

### Deploy

The deploy was made using a hook for GitHub repositories, cloning the repository when a commit is made to the master branch and running the Dockerfile in the project root, creating a docker image that runs with the Render PostgreSQL database instance environment variables to connect locally.

### Run Locally

To run the Back-end locally there are many options, but i recommend using Docker compose, that compiles, creates a docker image and runs with a database instance with a persistent volume. Using the docker compose, docker will set a IPV4 address for the application, you get it's value by doing a `docker inspect` in the container and set that value as the URL on the Front-end, the port is set to be 8080 by default.

> By doing it in other ways you'll have to set environment variables manually.

## [Front-end](https://github.com/Edhet/ChocolateStore-Frontend/)

### Overview

The front-end consists in a SPA application made in Angular with services that consume the different API endpoints to populate the interface and perform the cart and auth operations, with JWT Tokens being stored as cookies.

### Deploy

The deploy was made using GH Actions to automatically create a build and serve it on GH Pages.
> Currently using: `AhsanAyaz/angular-deploy-gh-pages-actions`

### Run Locally

To run locally, first you have to change the API URL in the `src/app/environment/environment.ts` to the URL and port that corresponds to the Spring Boot web server running in your machine. (by default Spring Boot uses http://localhost:8080/)

Then its necessary to have a JavaScript package manager like _npm_ (or _yarn_ and similars), run `npm install` to install the dependencies, and then `npm start` to start the web-server, or, if you have _angular-cli_, use `ng serve` to start the application.

If the Back-end is running and the `environment.ts` file has it's correct URL, the Front-end web server should connect to it and run as intended.
