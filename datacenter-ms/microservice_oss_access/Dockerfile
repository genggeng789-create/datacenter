FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/oss_access.jar oss_access.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /oss_access.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /oss_access.jar
EXPOSE 8087