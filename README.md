# Stylist appointment service

This is a test task.
The goal is to have a service, where a user can an appointment with a stylist.
There are 3 main things, that users should do with the app:
 * add a stysist
 * book an appointment (without specifying the stylist)
 * get the list of available slots for an appointment
 
## Tech description
 * This service is written in Java with Spring Boot. 
 * To store data it is using PostgreSQL.
 * For testing JUnit with Spring Boot Test is used. 

## Run locally
There are several ways to run the app locally. Here are described two of them:

### Using Docker compose
This approach do not require anything apart from `docker` and `docker-compose` installed.
 1. First of all, build a "fat jar" using maven
    * If you have a Maven installed, just run `mvn -f backend/pom.xml clean package`
    * If not, use Docker for that: 
        ```
        docker build -f build.Dockerfile -t stylist-appointment-mvn-build . && \
        docker run --rm -v "$HOME"/.m2:/home/nonroot/.m2 -v `pwd`/backend:/build stylist-appointment-mvn-build
        ```
 2. Run `docker compose up --build -d` to build and run app in docker and the database.
    * Please wait around 20-30 second until the service will be available  
    * To stop services, use `docker compose down`
    * If you would use the same .jar, you could omit `--build` flag to use cached docker image.
    * To see application logs run `docker logs <CONTAINER_ID>`

### Use spring-boot maven plugin
 * You need `maven` and `JDK-8` installed on your machine.
 * Just run `mvn -f backend/pom.xml spring-boot:run`

## Use the service
By default, the app is served on [http://localhost:8080]()

### API documentation
An up-to-date version of documentation could be found in swagger UI: [http://localhost:8080/swagger-ui.html]()

Please keep in mind, that it is not secure to open to everyone such documentation on the production environment.
But this topic is out of scope for this test task. 

Also be aware, that one of the endpoints, which creates `Stylist` entity, is not listed there.

### API usage examples
 * Creating the stylist:
    ```
    curl -v --header "Content-Type: application/json" \
      --request POST \
      --data '{ "name": "Stylist Name", "email": "stylist@email.com" }' \
      http://localhost:8080/stylists
    ```
 * Creating an appointment:
    ```
    curl -v --header "Content-Type: application/json" \
      --request POST \
      --data '{"slotDefinitionId": 3, "date": "2019-04-29", "customerId": 1}' \
      http://localhost:8080/appointments
    ```
 * Getting potential appointment slots: 
    ```
    curl "http://localhost:8080/availability?dateFrom=2019-04-29&dateTo=2019-04-30"
    ```