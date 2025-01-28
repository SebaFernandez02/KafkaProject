import logging
import time
import requests
import csv
from collections import Counter
from datetime import datetime

# Configuración del logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    handlers=[logging.StreamHandler()]
)

# URL de tu API (cambiar por la real)
BASE_URL = "http://suscriptores-get:8080/suscribers"


def save_to_csv(subscription_counts, totals, filename="/app/output/reporte_areas.csv"):
    """Guarda las estadísticas de suscripciones en un archivo CSV."""
    try:
        logging.info("Guardando las estadísticas en el archivo CSV...")
        with open(filename, mode="w", newline="") as file:
            writer = csv.writer(file)

            # Escribir las estadísticas de suscripciones
            writer.writerow(["Área del Parque", "Cantidad de Usuarios"])
            for area, count in subscription_counts.items():
                writer.writerow([area, count])

            # Escribir los totales (total de suscriptores y por fecha)
            writer.writerow([])  # Espacio en blanco entre las secciones
            writer.writerow(["Total de Suscriptores", totals["total"]])
            writer.writerow(["Fecha", "Cantidad de Usuarios"])
            for date, count in totals["by_date"].items():
                writer.writerow([date, count])

        logging.info(f"Reporte guardado exitosamente como '{filename}'")
    except Exception as e:
        logging.error(f"Error al guardar el archivo CSV: {str(e)}")


def generate_statistics():
    """Obtiene la lista de usuarios desde el API y genera estadísticas en un archivo CSV."""
    try:
        logging.info("Obteniendo datos desde la API...")
        response = requests.get(f"{BASE_URL}/getSuscribers")

        if response.status_code == 200:
            suscribers = response.json()
            logging.info(f"Datos obtenidos: {len(suscribers)} suscriptores")

            # Verificar la estructura de los datos obtenidos
            logging.info(f"Ejemplo de suscriber: {suscribers[0] if suscribers else 'No hay suscriptores'}")

            # Procesar suscripciones
            subscriptions = []
            for suscriber in suscribers:
                if isinstance(suscriber, dict):  # Verificar si el suscriber es un diccionario
                    # Acceder a suscripciones directamente, ya que es una lista de strings
                    subscriptions.extend(suscriber.get("suscriptions", []))
                else:
                    logging.warning(f"Suscriber no es un diccionario: {suscriber}")

            subscription_counts = Counter(subscriptions)

            # Calcular total de suscriptores
            total_suscribers = len(suscribers)

            # Procesar suscriptores por fecha (solo día, sin hora)
            dates = []
            for suscriber in suscribers:
                if isinstance(suscriber, dict):  # Verificar si el suscriber es un diccionario
                    try:
                        # Convertir la fecha en formato "YYYY-MM-DD"
                        date = datetime.strptime(suscriber["date"], "%Y-%m-%dT%H:%M:%S.%f").date().isoformat()
                        dates.append(date)
                    except Exception as e:
                        logging.error(f"Error al procesar la fecha para suscriber {suscriber['id']}: {e}")
                else:
                    logging.warning(f"Suscriber no es un diccionario: {suscriber}")

            date_counts = Counter(dates)

            # Guardar las estadísticas en un archivo CSV
            totals = {
                "total": total_suscribers,
                "by_date": date_counts
            }
            save_to_csv(subscription_counts, totals)
        else:
            logging.error(f"Error al obtener usuarios: {response.status_code} - {response.text}")
    except Exception as e:
        logging.error(f"Ocurrió un error: {str(e)}")


def main():
    while True:
        logging.info("Iniciando generación de estadísticas...")
        generate_statistics()
        logging.info("Esperando 2 minutos antes de la siguiente ejecución...")
        time.sleep(120)


if __name__ == "__main__":
    logging.info("Script de estadísticas iniciado.")
    main()
