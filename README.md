= Cardiff service

Service for customer's something...

= Build prerequisities
Java 17

= Build info
mvn clean package

= Run info
/usr/lib/jvm/java-17-openjdk-amd64/bin/java -jar target/cardiff-1.0.0-SNAPSHOT.jar

= Environment info
server port: 8888
endpoints:
 - localhost:8888/customers
 - localhost:8888/quotations
 - localhost:8888/subscriptions

## Task
Implement:
- Spring Boot micro-service written in Java
- Build with Maven
- Data will be stored in embeded H2 database
- micro-service will be able to run locally without any other external DB (etc.) dependencies
- provide source code to us preferably with link publicly available on the internet or via ZIP file attached to the email

### Quotation
Create endpoint for creation of Quotation.
Quotation has business attributes:
- beginingOfInsurance
- insuredAmount
- dateOfSigningMortgage
- Customer

### Subscription
Create endpoint for creation of Subscription.
Create endpoint for retrieving single Subscription object.
Subscription has business attributes:
- Quotation
- startDate
- validUntil

### Customer
Create endpoint for updating Customer attributes.
Endpoint for updating Customer should be able to update and/or remove existing values of any/all attributes.
Customer has business attributes:
- firstName
- lastName
- middleName
- email
- phoneNumber
- birthDate
