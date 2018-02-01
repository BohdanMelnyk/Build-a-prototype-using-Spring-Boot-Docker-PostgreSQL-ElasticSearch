FROM openjdk

MAINTAINER Bohdan Melnyk <sbohdmelnyk@gmail.com>

VOLUME /tmp
ADD maven/${fileName}.jar ${fileName}.jar
RUN sh -c 'touch /${fileName}.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/${fileName}.jar"]