# Testez une application full stack, OpenClassrooms - projet n°5

## Table of Contents

* [Summary](#summary)
* [Ressources](#ressources)
    * [SQL script](#sql-script)
    * [Postman collection](#postman-collection)
* [Backend](#backend)
    * [Backend technologies](#backend-technologies)
    * [Setup the database](#setup-the-database)
    * [Setup the backend API](#setup-the-backend-api)
* [Frontend](#frontend)
    * [Frontend technologies](#frontend-technologies)
    * [Setup the frontend server](#setup-the-frontend-server)

# Summary

This project contains both the frontend and backend code for an app called MDD, which allows users to register and login
to follow topics about IT, and comment on related articles.

# Ressources

### SQL script

An SQL script to populate the database's tables is available at `back/docs/script.sql`

Among other entries, it'll generate domr users accounts, like :

- login: ocruser1@aze.com
- password: ocrMdp1*

### Postman collection

For Postman import the collection

> back/docs/mddapi.postman_collection.json

by following the documentation:

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

# Backend

## Backend technologies

This project was generated with [Java](https://www.java.com/en/) 17 and [Spring Boot](https://spring.io/) 3.4.4

## Setup the database

You can use e.g. [MySQL](https://www.mysql.com/fr/) to create a database (name it `ocrp6`, but you can modify that in
the application.properties situated in `back/src/main/ressources`, as well as the credentials that are at the moment
login `userocrp6` and password `mpocrp6`), and admin it with [phpMyAdmin](https://www.phpmyadmin.net/), both of which
are available in the [XAMPP](https://www.apachefriends.org/fr/index.html) bundle.
Once the database created, running the API will create the tables if you set `spring.jpa.hibernate.ddl-auto` to `create`
in the application.properties file. After it's done you can populate these tables using the sql script found in
`back/docs`.

## Setup the backend API

Clone the project :

> git clone https://github.com/adThomDev/Developpez-une-application-full-stack-complete.git

Go inside the back folder :

> cd back

Install dependencies :

> mvn clean install

Run the API :

> mvn spring-boot:run

# Frontend

## Frontend technologies

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) 18.2.15.,
uses [TypeScript](https://www.typescriptlang.org/) 5.4.5 and the UI library [Angular Material](https://material.angular.io/) 18.2.0.

## Setup the frontend server

Go inside the front folder :

> cd front

Install dependencies :

> npm install

Launch the frontend :

> ng serve

The app should then be available at `http://localhost:4200/`