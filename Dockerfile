FROM gradle:jdk17

ENV GRADLE_VERSION 7.4.2
ENV GRADLE_HOME=/opt/gradle-${GRADLE_VERSION}
ENV APP_HOME=/opt/app

RUN mkdir ${APP_HOME}

#RUN apt-get update && apt-get install -y unzip
#RUN cd /opt && curl -L https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip > gradle-${GRADLE_VERSION}-bin.zip
#RUN ls -l /opt/
#RUN cd /opt && unzip gradle-${GRADLE_VERSION}-bin.zip
#ENV PATH=$PATH:$GRADLE_HOME/bin
#RUN gradle --version

WORKDIR $APP_HOME
COPY src ./src
COPY build.gradle ./build.gradle
COPY gradlew ./gradlew
COPY settings.gradle settings.gradle
COPY gradle ./gradle
RUN ./gradlew clean build
COPY build/libs/absa-bookstore.jar absa-bookstore.jar
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "absa-bookstore.jar"]