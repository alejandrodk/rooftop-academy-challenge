ARG APP_ROOT="/usr/app"

FROM maven:3-jdk-8-alpine

ARG APP_ROOT
COPY . $APP_ROOT

WORKDIR $APP_ROOT

RUN mvn clean package
EXPOSE 8080
CMD ["java","-jar","./target/challenge-fat-jar-boot.jar"]