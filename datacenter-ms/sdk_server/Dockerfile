FROM core.harbor.cz.shenlan.com/library/openjdk:8-jdk-alpine
MAINTAINER datacenter
ADD target/search_picture.jar search_picture.jar
RUN echo "Asia/Shanghai" >/etc/timezone
RUN sh -c 'touch /search_picture.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /search_picture.jar
EXPOSE 8086