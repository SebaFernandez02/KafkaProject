pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-11'  // Imagen con Maven y OpenJDK 11
            args '-v /var/run/docker.sock:/var/run/docker.sock'  // Monta el socket de Docker
        }
    }

    environment {
        PATH = "/usr/local/bin:$PATH"  // Asegura que los binarios de Docker estén en el PATH
    }

    stages {
        stage('Build Maven') {
            steps {
                script {
                    echo "Compilando el proyecto con Maven..."
                    sh 'mvn clean install'
                }
            }
        }

        stage('Levantar Contenedores') {
            steps {
                script {
                    echo "Levantando los contenedores con Docker Compose..."
                    sh '''
                        # Instalar Docker y Docker Compose si no están instalados
                        apt-get update && apt-get install -y docker.io docker-compose
                        docker-compose up --build -d
                    '''
                }
            }
        }

        stage('Ejecutar Dashboard Python') {
            steps {
                script {
                    echo "Ejecutando el archivo dashboard.py..."
                    sh 'python3 dataPython/dashboard/dashboard.py'
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
