# Spring Boot Microservices example

A Spring Boot Microservices example consisting of the following microservices:
1. Music chart service
	1. Makes a single GET call to the chart position data service to retrieve all of the music tracks on the current chart
	2. Makes multiple GET calls (based on the chart list from the previous call) to the music track details service to get each of the track details by ID.
	3. Returns the amalgamation of this data to the API consumer.
2. Chart position data service (Details of music tracks by their ID and their chart position)
3. Music track details microservice (Details of all music tracks in the system)

The postman collection can be found at `Spring Boot Microservices Music Chart API.postman_collection.json`

The repository also includes:
1. Netflix Eureka discovery server <strong>(Eureka server UI can be accessed at http://localhost:8761 and shows all running service instances)</strong>
	1. It can discover all of the client microservices and how many instances of each are up and running.
	2. It can notify the existence of running microservices to the Zuul Proxy gateway server so the proxy knows which routes are available to forward to.
	3. Assists in client side load balancing with Netflix Ribbon by facilitating in service discovery when communicating between microservices e.g. Making a request for example to `http://chart-position-data-service/positions` <strong>(Note the Dynamic URL being the name of the service to allow for load balancing e.g. between a service running on http://localhost:8080, http://localhost:8081 and http://localhost:8082)</strong> using WebClient will load balance between instances of the Chart position data service automatically.
2. Netflix Zuul Proxy Gateway server, which allows all of the routes to proxy through a single server e.g. in this case http://localhost:8762

## Prerequisites

The following installed:

1. Java (JDK 8)
2. Maven

## Running from the command line

1. Run `mvn clean install` inside each of the subfolders.
2. Run `java -jar target/<NAME_OF_JAR>.jar` to start each of the Spring Boot microservices from each subfolder.

Once each application is up and running:

Import the Postman collection into Postman and test out calling each of the microservices.

The postman collection can be found at `Spring Boot Microservices Music Chart API.postman_collection.json`

You will notice that a single request URL http://localhost:8762 is used because requests are made through the Zuul gateway proxy. This makes it extremely easy to access all microservices from one dedicated location.

Zuul uses Ribbon to automatically load balance requests when forwarding to each microservice.

You can test running multiple instances of each microservice using
`java -Dserver.port=<PORT> -jar <NAME_OF_JAR>.jar`
and navigating to http://localhost:8761 which is the Eureka server GUI.

It will show you all of the running microservices and how many instances of each are up and running.
