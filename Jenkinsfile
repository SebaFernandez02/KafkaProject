pipeline {
    agent any  // Usar cualquier agente de Jenkins disponible

    environment {
        DATA_PYTHON_PATH = "dataPython/dashboard"
    }

    stages {
        stage('Build Maven') {
            steps {
                script {
                    echo "Compilando el proyecto con Maven..."
                    sh 'docker run --rm -v $(pwd):/usr/src/mymaven -w /usr/src/mymaven maven:3.8.4-openjdk-11 mvn clean install'
                }
            }
        }

        stage('Levantar Contenedores') {
            steps {
                script {
                    echo "Levantando los contenedores con Docker Compose..."
                    sh 'docker-compose up --build -d'
                }
            }
        }

        stage('Ejecutar Dashboard Python') {
            steps {
                script {
                    echo "Ejecutando el archivo dashboard.py..."
                    sh "python3 ${DATA_PYTHON_PATH}/dashboard.py"
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline terminado"
        }
        failure {
            echo "Algo salió mal en el pipeline"
        }
    }
}
