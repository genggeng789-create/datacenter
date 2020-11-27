FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/eureka_server.jar eureka_server.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /eureka_server.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka_server.jar
EXPOSE 8888