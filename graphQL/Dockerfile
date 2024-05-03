FROM openjdk:17-jdk-alpine
EXPOSE 8088
ARG JAR_FILE=target/*.jar
COPY ./target/graphQL-0.0.1-SNAPSHOT.jar graphql.jar
ENTRYPOINT ["java","-jar","/graphql.jar"]