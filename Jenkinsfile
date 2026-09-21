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

        stage('Deploy to Staging (Blue-Green)') {
            steps {
                echo "5. Iniciando despliegue Blue-Green..."
                sh 'echo "Desplegando nueva versión en entorno GREEN..."'
            }
        }

        stage('Acceptance Tests (Gate)') {
            steps {
                echo "6. Validando Acceptance Gate en entorno GREEN..."
                sh 'mvn test -Dtest=LoginAcceptanceTest'
            }
        }
    }

    post {
        success {
            echo "Acceptance Gate superado. Entorno GREEN es ahora productivo."
        }
        failure {
            echo "Fallo detectado. INICIANDO ROLLBACK AUTOMÁTICO..."
            sh 'echo "Redirigiendo tráfico al entorno BLUE estable para recuperación."'
        }
        always {
            cleanWs()
        }
    }
}