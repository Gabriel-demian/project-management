# project-management
  A project management webb app with Spring Boot, Spring Web MVC, AOP, Hibernate, Thymeleaf, JPA, Spring Security, RESTful services

pending modifications
  -Spring Security  usando token csrf   (En progreso)
  -Web Services API
  -Improving the application.


Para cambiar el rol de un usuario desde la DB 

	update user_accounts
	set role ='ROLE_ADMIN'
	where username = ?

	update user_accounts
	set role ='ROLE_USER'
	where username = ?




Para ejecutar con Docker
    ******************************************************************************************************************************************

    To see all the containers running "docker container ls"

    To delete all containers that are not running "docker container prune"

    To stop all containers that are running "docker ps -aq" -->  "docker stop $(docker ps -aq)"


    Run the app:

    Maven install


    Create file Dockerfile:
    ---------------------------------------------------------------------------
    FROM ubuntu:latest

    MAINTAINER User "User@gmail.com"

    RUN apt-get update && apt-get install -y openjdk-8-jdk

    ENV version=docker

    WORKDIR /usr/local/bin

    ADD project-management-app.jar .

    ENTRYPOINT ["java", "-jar", "project-management-app.jar"]

    ---------------------------------------------------------------------------

    Run the file with the jar in the same folder:

    docker image build -t proy-from-docker .

    docker container run -p 8999:8080 proy-from-docker

    it will run on "http://localhost:8999/"


    application.properties must have spring.profiles.active=docker


    ******************************************************************************************************************************************
