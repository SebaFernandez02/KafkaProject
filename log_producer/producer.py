"""
    Script que genera mensajes de log aleatorios cada segundo, a consola y fichero
"""
import os
import logging
import random
import time
import uuid

def main():
    logDirectory = os.getenv("LOG_DIRECTORY")
    if logDirectory == None:
        logDirectory = "."

    FORMAT = '%(asctime)s %(process)d %(module)s %(levelname)-8s %(message)s'
    logging.basicConfig(level=logging.DEBUG,
                        format=FORMAT,
                        filename= logDirectory + '/app.log',
                        filemode='w')
    console = logging.StreamHandler()
    console.setFormatter(logging.Formatter(FORMAT))
    console.setLevel(logging.DEBUG)

    logger = logging.getLogger()
    logger.addHandler(console)

    while (True):
        randomLevel = random.choice(
            [logging.DEBUG, logging.INFO, logging.WARNING, logging.ERROR])
        logger.log(randomLevel, f'ID({uuid.uuid4()})' )
        time.sleep(1)


if __name__ == "__main__":
    main()
