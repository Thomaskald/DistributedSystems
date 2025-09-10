pipeline {
    agent any
    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

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
                // Build the Docker image
                sh 'sudo docker build -f nonroot-multistage.Dockerfile -t distributed-app .'

                // Stop and remove previous container if it exists
                sh 'docker stop distributed-app || true'
                sh 'docker rm distributed-app || true'

                // Run the container
                sh 'docker run -d -p 8081:8080 --name distributed-app distributed-app'
            }
        }

    }
}
