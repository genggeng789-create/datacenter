FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/microservice_user.jar microservice_user.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /microservice_user.jar'
ENV LC_ALL=zh_CN.UTF-8
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /microservice_user.jar
EXPOSE 9002