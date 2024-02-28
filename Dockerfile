FROM openjdk:17
RUN  mkdir -p /test
EXPOSE 8080
WORKDIR /test
COPY target/Hesabbook.jar  /test/Hesabbook.jar
CMD ["java","-jar","Hesabbook.jar"]


