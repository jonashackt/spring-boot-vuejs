mvn clean install -DskipTests=true
java -Dserver.port=$PORT -jar backend/target/backend-0.0.1-SNAPSHOT.jar
