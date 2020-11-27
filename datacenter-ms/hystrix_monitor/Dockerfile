FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/hystrix_monitor.jar hystrix_monitor.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /hystrix_monitor.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /hystrix_monitor.jar
EXPOSE 7777