FROM openjdk:11
Volume /tmp
ADD /target/*.jar eservice_login_services-0.0.1-SNAPSHOT.jar
EXPOSE 1217
ENTRYPOINT ["java", "-Xmx1024m","-jar", "/eservice_login_services-0.0.1-SNAPSHOT.jar"]
