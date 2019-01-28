FROM amd64/openjdk:8-jre-alpine3.8
LABEL maintainer="mdye@us.ibm.com"

ENV VERSION=0.2.0

ADD ./target/seq-http-$VERSION-standalone.jar /seq-http.jar
CMD ["java", "-jar", "/seq-http.jar"]
