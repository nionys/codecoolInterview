This is a Java Spring Boot application backed by two PostgreSQL databases.

To run this application, you must create two PostgreSQL databases, and copy their user
credentials into the src/main/resources/application.properties file:

app.<dbsource/dbtarget>.jdbc-url = jdbc:postgresql://localhost:5432/<your dbname here>
app.<dbsource/dbtarget>.username=<your username here>
app.<dbsource/dbtarget>.password=<your password here>

IMPORTANT: this file is tracked by git, do not publish this file after you change it.

Then, you need to run the following commands:
```mvn clean package```
```java -jar ./target/codecool-interview-0.0.1-SNAPSHOT.jar```
make sure you are at the project's root folder, and that you have installed maven and the latest java runtime.

Alternatively, load the project in IntelliJ IDEA and run the CodecoolInterviewApplication class.

The application handles a read-write operation between two databases:
- The first (source) database has records containing a JSON string
- The second (target) database is normalized and contains several tables
The source database's records are assumed not to be updated, new records may be added to it.
The target database has create and read endpoints for testing purposes.

endpoints:
base url: http://localhost:8080
GET /source/record/all
GET /source/record/{id}
POST /source/record
- body is JSON text

GET /target/person/all
GET /target/person/id/{id}
GET /target/person/email/{email}
POST /target/mentor
- content type is application/json and body has name and email

GET /target/student/all
GET /target/student/id/{id}
POST /target/student
- content type is application/json and body has name and email
POST target/student/promote
- body is an email address

GET /target/mentor/all
GET /target/mentor/id/{id}
POST /target/mentor
- content type is application/json and body has name and email
POST target/mentor/promote
- body is an email address

GET /copy
GET /statistics/average-result/student/email/{email}
GET /statistics/mentor-pass-rates

you can find examples of most of these requests in the /src/test/resources/apitest.http file

