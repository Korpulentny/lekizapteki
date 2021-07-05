FROM adoptopenjdk/maven-openjdk11

WORKDIR /lekizapteki

COPY api/ api
COPY app/ app
COPY pom.xml pom.xml
COPY entrypoint.sh entrypoint.sh

RUN mvn clean install

ENTRYPOINT ["./entrypoint.sh"]
