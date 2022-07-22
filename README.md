# ComtradeAssignment

---
A simple Java WEB-based application that provides the
various translations of ‘Hello World’ string.

## Table of contents
* [Tasks](#tasks)
* [Introduction](#introduction)
* [Releases](#releases)
* [Building the project](#building-the-project)
* [Running the Application on Docker](#running-the-application-on-docker)
* [Environmental variables](#environmental-variables)
* [Endpoints](#endpoints)
* [External API](#external-api)
* [Spring Security](#spring-security)
* [Reverse Proxy](#reverse-proxy)
## Tasks

- [X] Empty working Spring Boot App built with Maven
- [X] Has /hello-rest REST endpoint which returns ‘Hello World’ string
- [X] Has /hello endpoint which returns a HTML page with ‘Hello World’ string displayed
- [X] Has in-memory H2 database started with initial set of 10 different strings per language (‘Hello
   World’ in 10 different languages) and /hello endpoints return the string determined by language
   parameter passed inside the query
- [X] Has /secure/hello endpoint that requires user to log in with username and password
- [X] Has a secured ‘Admin’ page that allows the user to add new Language-Message pairs into the
   database
- [X] Has an aspect (AOP) that performs logging for different endpoints with useful information
   included into logs
- [X] Uses a standalone DB instead of in-memory H2 DB
- [X] Has ability to retrieve ‘Hello World’ translations from an external API (eg. Systran Translation API)
- [X] Has ability to switch between DB and external API retrieval by using spring profiles
- [X] Use standalone DB instead of in-memory H2
- [X] Dockerize the application
- [X] Run 2 instances of application (one that uses DB and another that uses external API) and put an
    reverse proxy in front of them, so when user access /api/db/hello or /api/external/hello it get
    routed to proper instance of the application. [Issue](#issue)

*All the tasks were issued and organized using a JIRA dashboard*
    
## Introduction

The application is implemented in Spring, and it uses:
- PostgreSQL Alpine 12.4 Database

Initial data:

| CODE |     MESSAGE      |
|:----:|:----------------:|
|  en  |   Hello World    |
|  sr  |   Здраво Свету   |
|  zh  |    你好世界          |
|  sl  | Pozdravljen svet |
|  it  |    Ciao mondo    |
|  es  |   ¡Hola Mundo    |
|  cs  |    Ahoj světe    |
|  de  |    Hallo Welt    |
|  ja  |     こんにちは世界      |
|  ar  |  مرحبا بالعالم   |

## Releases

- [v1.0: Tasks 1-7](https://github.com/isidoraDjokovic/ComtradeAssignment/releases/tag/v1.0)
- [v2.0: Tasks 1-13](https://github.com/isidoraDjokovic/ComtradeAssignment/releases/tag/v2.0)

## Building the project
To run build this project you will need:
- Java JDK 
- Maven
- Git
````shell
.\mvnw clean install
````
## Running the Application on Docker
First package the project into a .jar file using Maven:
````shell
.\mvnw clean package
````
Next run the following the command to create the containers:
````shell
docker compose up --build
````
###### The following containers will be created:

- **comtrade-assignment-external-app:**  Application running on port 8989 with *internal* profile
- **comtrade-assignment-db-app:** Application running on port 8888 with *external* profile
- **postgres:**   Postgres running on port 5432

## Environmental variables

| Variable               | Default   | Description         |
|------------------------|-----------|---------------------|
| SERVER_PORT            | 8080      | Application port    |
| DB_HOST                | localhost | Database host       |
| APP_PROFILE            | internal  | Application profile |

Application profiles are used for switching between external API and database translation retrieval and are defined as: 

- external
- internal

## Endpoints
Parameter languageCode must follow the **ISO 639-1 standard**.
##### Rest Endpoint:
```
/hello-rest?language_code=[languageCode]
```
##### Endpoint that returns a HTML page:
```
/hello?language_code=[languageCode]
```
##### LogIn:
```
/secure/hello
```

## External API

 ### Google Translate ###
[Documentation](https://rapidapi.com/googlecloud/api/google-translate1/)
> **WARNING**: Quota Limit 500 objects/month

## Spring Security
Users are defined in the [WebSecurityConfig](https://github.com/isidoraDjokovic/ComtradeAssignment/blob/main/src/main/java/com/dora/assignment/WebSecurityConfig.java) file:

| USER        | PASSWORD | ROLE  |
|-------------|----------|-------|
| user        | password | USER  |
| admin       | admin    | ADMIN |

If you try to access the ````/admin ```` endpoint you will get redirected to the  ````/secure/hello ```` that requires users to enter their credentials and based on them redirect them to the appropriate page.
## Reverse Proxy
In order to enable reverse proxy with nginx uncomment the following lines of code from the [docker-compose.yml]("https://github.com/isidoraDjokovic/ComtradeAssignment/blob/main/docker-compose.yml").
````yml
#  nginx:
#    image: nginx:latest
#    container_name: comtrade-assignment-nginx
#    volumes:
#      - ./nginx.conf:/etc/nginx/nginx.conf
#    ports:
#      - "7070:7070"
#    networks:
#      - comtrade-net
#    depends_on:
#      - internal
#      - external
````

Next run the following the command to create the needed containers:
````shell
docker compose up --build
````
###### The following containers will be created:

- **comtrade-assignment-external-app:**  Application running on port 8989 with *internal* profile
- **comtrade-assignment-db-app:** Application running on port 8888 with *external* profile
- **comtrade-assignment-nginx:**   Postgres running on port 7070
- **postgres:**  Postgres running on port 5432

Configuration for nginx are defined in [nginx.conf](https://github.com/isidoraDjokovic/ComtradeAssignment/blob/main/nginx.conf).

Now when calling an endpoint listed below we will be routed to the appropriate instance of the application. 

###### Instance that uses external API:
```
/api/external/hello?language_code=[languageCode]
```
###### Instance that uses DB:
```
/api/db/hello?language_code=[languageCode]
```
##### ISSUE

When enabling the reverse proxy, after submitting the login form we are redirected to the wrong route.
[here](https://github.com/isidoraDjokovic/ComtradeAssignment/issues/16)
