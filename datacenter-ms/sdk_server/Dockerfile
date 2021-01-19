FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/sdk_server.jar sdk_server.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /sdk_server.jar'
ENV LC_ALL=zh_CN.UTF-8
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /sdk_server.jar
EXPOSE 8054