FROM ubuntu-jdk

MAINTAINER Demian2710 "gabriel.leoni.um@gmail.com"

ENV version=aws-db-usage
ENV jdbcurl=jdbc:postgresql://proydatabaseaws.cycxoswmvpzy.sa-east-1.rds.amazonaws.com:5432/postgres
ENV	dbuser=postgres
ENV	dbpass=password321

WORKDIR /usr/local/bin

ADD target/project-management-app.jar .

ENTRYPOINT ["java", "-jar", "project-management-app.jar"]