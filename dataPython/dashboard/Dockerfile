# Usa una imagen base de Python 3.8
FROM python:3.8-slim

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo de requisitos y el código de la aplicación
COPY ./dataPython/dashboard /app
COPY output/reporte_areas.csv output/reporte_areas.csv

# Instalar las dependencias necesarias
RUN pip install --no-cache-dir -r requirements.txt

# Exponer el puerto en el que la aplicación Dash va a correr
EXPOSE 8050

# Comando para ejecutar la aplicación Dash
CMD ["python", "dashboard.py"]
