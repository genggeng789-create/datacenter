FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/quixmart.jar quixmart.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /quixmart.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /quixmart.jar
EXPOSE 8085