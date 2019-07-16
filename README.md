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

## Running the microservices 

<strong> NOTE: Run the projects in this order in separate windows so you can see the logs for each.</strong>
1. discovery-server (This must be running before you start 2, 3 and 4)
2. chart-position-data-service
3. music-track-details-service
4. music-chart-service
5. zuul-gateway-proxy-server (Wait for 1, 2, 3 and 4 to be running before starting this)

## From the command line

1. Run `mvn clean install` inside each of the subfolders to generate the JAR for that microservice. You can find the name of the JAR inside the `target/` folder e.g. for the `chart-position-data-microservice`, the JAR is called `chart-position-data-service-0.0.1-SNAPSHOT.jar`
2. In the order specified above, Run `java -jar target/<NAME_OF_JAR>.jar` to start each of the Spring Boot microservices from each subfolder.

## From Spring Tool Suite

1. Import each of the subfolders as Maven projects into Spring Tool Suite.
2. Wait for the Maven dependencies to be installed.
3. In the order specified above, <strong>Right click on each project -> Run as -> Spring Boot app</strong>
	
## Testing the microservices using POSTman

Once each application is up and running:

Import the Postman collection into Postman and test out calling each of the microservices.

The postman collection can be found at `Spring Boot Microservices Music Chart API.postman_collection.json`

You will notice that a single request URL http://localhost:8762 is used because requests are made through the Zuul gateway proxy. This makes it extremely easy to access all microservices from one dedicated location.

Zuul uses Ribbon to automatically load balance requests when forwarding to each microservice.

You can test running multiple instances of each microservice using
`java -Dserver.port=<PORT> -jar <NAME_OF_JAR>.jar`
and navigating to http://localhost:8761 which is the Eureka server GUI.

It will show you all of the running microservices and how many instances of each are up and running.
