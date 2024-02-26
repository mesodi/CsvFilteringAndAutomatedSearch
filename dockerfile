# Första steget: Byggsteg
FROM maven:3-amazoncorretto-17 AS build

# Ange arbetsmappen inne i containern
WORKDIR /app/myapp

# Kopiera projektets POM-fil
COPY pom.xml .

# Kopiera källkoden
COPY src ./src

# Bygg projektet och skapa JAR-filen
RUN mvn package

# Andra steget: Utvecklingssteget
FROM amazoncorretto:17-alpine

# Ange arbetsmappen inne i containern
WORKDIR /app/myapp

# Kopiera JAR-filen som byggdes i det föregående steget till den aktuella mappen
# Se till att namnet på JAR-filen matchar det som genereras av Maven
COPY --from=build /app/myapp/target/ROOT.jar app.jar

# Definiera kommandot för att köra applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]
