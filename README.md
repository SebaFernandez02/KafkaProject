## Autor: Sebastián Agustín Fernández

# Sebalandia: Sistema de Gestión de Usuarios y Suscripciones 🎢

¡Bienvenido a **Sebalandia**, el sistema de gestión para usuarios y suscripciones del parque de atracciones más emocionante! Este proyecto permite registrar usuarios y suscripciones a las distintas áreas del parque, procesar los datos en tiempo real y generar reportes en formato CSV para su análisis.

## 🛠️ Tecnologías Utilizadas

Este sistema está desarrollado utilizando las siguientes tecnologías:
- **Java**: Lenguaje principal para el backend.
- **Spring Boot**: Framework utilizado para crear los microservicios.
- **Apache Kafka**: Procesamiento y transmisión de datos de forma asíncrona.
- **PostgreSQL**: Base de datos relacional para almacenar la información de usuarios y suscripciones.
- **Python**: Script para la recolección y generación de reportes en CSV.
- **Maven**: Herramienta para la gestión de dependencias y construcción del proyecto.

## ✨ Funcionalidades

### Backend
1. **Gestión de Usuarios**:
   - Registro de usuarios con datos como nombre de usuario, correo electrónico.
2. **Gestión de Suscripciones**:
   - Registro de las áreas del parque a las que cada usuario se suscribe.
3. **Microservicios con Kafka**:
   - Procesamiento en tiempo real de eventos relacionados con usuarios y suscripciones.
   - Uso de streams para combinar datos de diferentes servicios y generar información consolidada.

### Reportes
- **Generación de Reportes en CSV**:  
  Un script en Python recolecta los datos de las suscripciones desde un microservicio y genera reportes en formato CSV


## Ejecución de la aplicación
1. Ejecutar mvn clean install en la carpeta raíz del proyecto
2. Ejecutar docker-compose up --build para arrancar los contenedores
3. En la ubicacion **/dataPython/dashboard** ejecutar el archivo dashboard.py
3. Prueba de aplicación en:
   - Creación de Usuarios: http://localhost:8090/swagger-ui/index.html
   - Creación de Suscripciones: http://localhost:8091/swagger-ui/index.html
   - Consulta de Suscriptores: http://localhost:8092/swagger-ui/index.html
   - Sonar: http://localhost:9000/
   - Grafana: http://localhost:3000/
   - Redpanda: http://localhost:8080/
   - python-dashboard: http://localhost:8050/

NOTA: Al levantar todos los contenedores se van a auto crear los topics, 
este proyecto es solo una demo ya que se deberían configurar.

NOTA2: El reporte se encuentra en la carpeta output del proyecto y se actualiza cada 2min.