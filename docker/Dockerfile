FROM maven:3.8.6-amazoncorretto-8 AS build
COPY Cinema /home/Cinema
RUN mvn -f /home/Cinema/pom.xml clean package

FROM tomcat:8-jdk8
COPY --from=build /home/Cinema/target/root.war /usr/local/tomcat/webapps/ROOT.war
# COPY wait-for /wait-for
# RUN chmod +x /wait-for
# CMD ["sh", "-c", "/wait-for host:5433 -- /usr/local/tomcat/bin/catalina.sh run"]
RUN apt-get update -y && apt-get install netcat -y
RUN mkdir /root/imgs
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]