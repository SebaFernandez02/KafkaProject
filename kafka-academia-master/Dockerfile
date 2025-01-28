# Usar una imagen base de Python
FROM python:3.9-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar la carpeta `dataPython` al contenedor
COPY ./dataPython /app

# Instalar las dependencias necesarias
RUN pip install requests

# Comando para ejecutar el script
CMD ["python", "script.py"]
