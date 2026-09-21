pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "1. Descargando código desde el repositorio..."
                checkout scm
            }
        }

        stage('Build (Compilación)') {
            steps {
                echo "2. Compilando el proyecto y empaquetando..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Pruebas Unitarias') {
            steps {
                echo "3. Ejecutando Pruebas Unitarias (Surefire)..."
                sh 'mvn test'
            }
        }

        stage('Pruebas de Integración') {
            steps {
                echo "4. Ejecutando Pruebas de Integración (Failsafe)..."
                sh 'mvn failsafe:integration-test failsafe:verify'
            }
        }
    }

    post {
        success {
            echo "Integración Continua completada exitosamente. El código es estable."
        }
        failure {
            echo "Fallo en la Integración Continua. Revisa los logs de compilación o pruebas."
        }
        always {
            cleanWs()
        }
    }
}