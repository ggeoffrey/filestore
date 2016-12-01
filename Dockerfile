FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/filestore.jar /filestore/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/filestore/app.jar"]
