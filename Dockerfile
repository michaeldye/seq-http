FROM amd64/openjdk:8-jre-alpine3.8
LABEL maintainer="mdye@us.ibm.com"

ENV VERSION=0.2.0

ADD ./target/seq_http-$VERSION-standalone.jar /seq_http.jar
CMD ["java", "-jar", "/seq_http.jar"]
