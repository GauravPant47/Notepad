FROM eclipse-temurin:17

MAINTAINER notepad.in

COPY target/textEditor-1.0.0.jar textEditor-1.0.0.jar

ENTRYPOINT ["java","-jar","textEditor-1.0.0.jar"]
