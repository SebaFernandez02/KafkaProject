pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:/Program Files/apache-maven-3.8.8' // Ajusta esta ruta si tu Maven está en una ubicación diferente
        DOCKER_HOME = '/usr/local/bin/docker' // Ajusta la ruta de Docker si es necesario
    }

    stages {
        stage('Build with Maven') {
            steps {
                script {
                    echo 'Ejecutando mvn clean install...'
                    // Ejecuta el comando mvn clean install en la carpeta raíz del proyecto
                    sh 'mvn clean install'
                }
            }
        }

        stage('Levantar Contenedores Docker') {
            steps {
                script {
                    echo 'Levantando los contenedores con Docker Compose...'
                    // Ejecuta el comando docker-compose up --build en el directorio donde se encuentra el archivo docker-compose.yml
                    sh 'docker-compose up --build -d'
                }
            }
        }

        stage('Ejecutar Dashboard Python') {
            steps {
                script {
                    echo 'Ejecutando el archivo dashboard.py...'
                    // Ejecuta el archivo dashboard.py en la ubicación /dataPython/dashboard
                    sh 'python3 /dataPython/dashboard/dashboard.py'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminada.'
        }
        success {
            echo 'Pipeline ejecutada con éxito.'
        }
        failure {
            echo 'Algo salió mal en la ejecución de la pipeline.'
        }
    }
}
