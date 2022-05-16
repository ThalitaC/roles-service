FROM openjdk:17-jdk-alpine
COPY target/roleService-0.0.1-SNAPSHOT.jar roleService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/roleService-0.0.1-SNAPSHOT.jar"]