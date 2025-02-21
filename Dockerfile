# Usar una imagen base con Maven y OpenJDK 11
FROM maven:3.8.4-openjdk-11

# Instalar Docker y Docker Compose
RUN apt-get update && apt-get install -y docker.io docker-compose python3

