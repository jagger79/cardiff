# Cardiff service

Service for customer's something...

## Build prerequisities
Java 17

## Build info
mvn clean package

## Run info
/usr/lib/jvm/java-17-openjdk-amd64/bin/java -jar target/cardiff-1.0.0-SNAPSHOT.jar

## Environment info
server port: 8888

endpoints:
 - localhost:8888/customers
 - localhost:8888/quotations
 - localhost:8888/subscriptions
