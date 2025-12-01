# Etapa 1: Construcción
FROM eclipse-temurin:21-jdk AS build

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen ligera
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar jar construido desde la etapa de build
COPY --from=build /app/target/productor-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto (opcional)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]
