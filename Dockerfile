FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
# deploy the app (jar)
# set working dir
WORKDIR /root
ADD target/demo-0.0.1-SNAPSHOT.jar /root
RUN sh -c 'touch /app.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.config.location=file:/conf/application.properties"]