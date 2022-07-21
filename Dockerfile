FROM eclipse-temurin:17.0.2_8-jdk

COPY target/comtrade-dora.jar application.jar

ENTRYPOINT ["java", "-jar", "-Xmx1g", "/application.jar"]

