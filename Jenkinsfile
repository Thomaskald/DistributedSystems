pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'Code', url: 'https://github.com/Thomaskald/DistributedSystems.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                   # Stop any running instance
                   pkill -f "java -jar" || true

                   # Run the new jar in background
                   nohup java -jar target/*.jar > app.log 2>&1 &
                '''
            }
        }
    }
}
