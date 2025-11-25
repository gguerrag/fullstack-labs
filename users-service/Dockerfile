# ================================
#   Etapa 1 - Build
# ================================
FROM eclipse-temurin:25-jdk-alpine AS build
RUN apk add --no-cache maven

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ================================
#   Etapa 2 - Ejecutar
# ================================
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copia el JAR construido
COPY --from=build /app/target/*.jar app.jar

# OJO: SIN COPY DEL WALLET AQUÍ
# El wallet se montará por volumen en docker-compose

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
