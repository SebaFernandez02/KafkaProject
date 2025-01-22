# Dockerfile que lanza un script que genera mensajes de log
FROM python:3.12.0a5-alpine3.17
ENV LOG_DIRECTORY /app/logs
WORKDIR /app
RUN mkdir logs
COPY ./log_producer/producer.py producer.py
ENTRYPOINT [ "python", "producer.py" ]