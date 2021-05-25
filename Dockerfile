FROM adoptopenjdk/openjdk11:latest

COPY target/pet-0.0.1-SNAPSHOT.jar pet-1.0.0.jar

ENTRYPOINT ["java","-jar","/pet-1.0.0.jar"]