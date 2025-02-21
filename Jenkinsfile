pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-11'  // Usamos una imagen que tiene Maven y OpenJDK 11
            args '-v /var/jenkins_home:/var/jenkins_home'  // Configurar volúmenes si es necesario
        }
    }

    environment {
        DATA_PYTHON_PATH = "dataPython/dashboard"
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
