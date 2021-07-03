FROM openjdk:11

LABEL maintainer="WhyWhale"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/jpa_shop-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} shop.jar

ENTRYPOINT ["java","-jar","/shop.jar"]
