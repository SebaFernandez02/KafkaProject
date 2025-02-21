#!groovy
node {

   //Etapa:Configurar environment

   stage 'Configurar environment'
   echo 'Configurando environment'
   def mvnHome = tool 'mvn'
   env.PATH = "${mvnHome}/bin:${env.PATH}"
   echo "var mvnHome='${mvnHome}'"
   echo "var env.PATH='${env.PATH}'"


   // Etapa: Compilar aplicación

   stage 'Compilar Aplicación'
   echo 'Descargando código'
   sh 'rm -rf *'
   checkout scm
   echo 'Compilando aplicación'
   sh 'mvn clean compile'

   // Etapa: Instalar y guardar JAR

   stage 'Instalar y guardar JAR'
   echo 'Instala el paquete generado en el repositorio maven'
   sh 'mvn install -Dmaven.test.skip=true'
   step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar, **/target/*.war', fingerprint: true])


   // Etapa: Build Imagen

   stage 'Build Imagen y subir a DockerHub'
   echo 'Buildear la imagen'
   sh 'docker-compose build'

   //  ETAPA: ejecutar contenedores

   stage 'Ejecutando contenedores'
   echo 'Ejecutando contenedores'
   sh 'docker-compose down'
   sh 'docker-compose up --build -d'
}
