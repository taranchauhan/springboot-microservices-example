# Spring Boot Microservices example

A Spring Boot Microservices example consisting of the following microservices:
1. Music chart service
	1. Makes a single GET call to the chart position data service to retrieve all of the music tracks on the current chart
	2. Makes multiple GET calls (based on the chart list from the previous call) to the music track details service to get each of the track details by ID.
2. Chart position data service (Details of music tracks by their ID and their chart position)
3. Music track details microservice (Details of all music tracks in the system)

The postman collection can be found at `Spring Boot Microservices Music Chart API.postman_collection.json`

The repository also includes:
1. Netflix Zuul Proxy Gateway server, which allows all of the routes to proxy through a single server e.g. in this case http://localhost:8762
2. Netflix Eureka discovery server
	1. It can discover all of the client microservices and how many instances of each are up and running.
	2. It can notify the existence of running services to the Zuul Proxy gateway server so the proxy knows which routes to forward to.
	3. Assists in client side load balancing with Netflix Ribbon by facilitating in service discovery when communicating between microservices e.g. Making a request for example to `http://chart-position-data-service/positions` using WebClient will load balance between instances of the Chart position data service automatically.

## Prerequisites

The following installed:

1. Java (JDK 8)
2. Maven

## Running from the command line

1. Run `mvn clean install` inside each of the subfolders.
2. Run `java -jar target/<NAME_OF_JAR>.jar` to start the Spring Boot microservices in each subfolder.

Once each application is up and running:

Import the Postman collection into Postman and test out calling each of the microservices. 

You will notice that a single URL http://localhost:8762 is used because they all go through the Zuul gateway proxy.

Zuul uses Ribbon to automatically load balance when forwarding to each microservice.

You can test running multiple instances of each microservice using
`java -Dserver.port=<PORT> -jar <NAME_OF_JAR>.jar`
and navigating to http://localhost:8761 which is the Eureka server GUI.

It will show you all of the running microservices and how many of each are up and running.
