pipeline {
    agent any  // Usa cualquier agente disponible, puede ser un nodo Linux

    environment {
        MAVEN_HOME = '/opt/maven'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Preparar Maven') {
            steps {
                script {
                    echo "Instalando Maven..."
                    // Descarga Maven si no está instalado
                    sh '''
                        if ! command -v mvn &> /dev/null
                        then
                            echo "Maven no encontrado, instalando..."
                            sudo apt-get update -y
                            sudo apt-get install -y maven
                        else
                            echo "Maven ya está instalado."
                        fi
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
