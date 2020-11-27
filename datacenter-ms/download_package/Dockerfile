FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/download_package.jar download_package.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /download_package.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /download_package.jar
EXPOSE 8088