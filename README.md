# Car Rental

This minimal bootstrap project contains a React UI and a backend powered by Spring, connected to a PostgreSQL database.
Before you start the project make sure you have Maven, Java 17, Docker ( for database ), Node and NPM installed. 

The backend serves a "Hello word" API that the frontend communicates with. 

You will be able to see the text "Hello Word" in the UI if you successfully manage to start the backend, frontend and the database.

## How to start the bootstrap project

### Database
You may start a database with the following docker command:

`docker run --name postgresql -p 5432:5432 -e POSTGRESQL_USERNAME=my_user -e POSTGRESQL_PASSWORD=password123 -e POSTGRESQL_DATABASE=rental bitnami/postgresql:latest`


### Backend 
The backend was bootstrapped with [Spring initializr](https://start.spring.io/) and is configured to run against a postgres database.

Start the backend by running  `com.example.rental.RentalApplication#main` or with Maven.


### Frontend

The frontend was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

See `package.json` for npm commands. The `package.json` has a proxy for the backend hosted at `http://localhost:8080`.

Start the frontend by doing `npm install` followed by `npm start` in the `frontend` folder

