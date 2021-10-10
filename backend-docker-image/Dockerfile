FROM adoptopenjdk/openjdk11

ADD target/lib .
WORKDIR /

# Just echo so we can see, if everything is there :)
RUN ls -l

VOLUME /tmp

# Add Spring Boot app.jar to Container
RUN mv *.jar app.jar

ENV JAVA_OPTS=""

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]