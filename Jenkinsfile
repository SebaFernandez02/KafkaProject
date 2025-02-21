pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/maven'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Preparar Maven') {
            steps {
                script {
                    echo "Instalando Maven..."
                    // Instalar Maven
                    sh '''
                        apt-get update -y
                        apt-get install -y maven
                    '''
                }
            }
        }

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
                    sh "python3 dataPython/dashboard/dashboard.py"
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
