FROM maven:3.8.5-openjdk-17

WORKDIR /CarXpress
COPY . .
RUN mvn clean install -DskipTests

# Add wait-for-it script
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

CMD /wait-for-it.sh carxpress-db:5432 -- mvn spring-boot:run
