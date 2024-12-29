pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = 'ahmeddeb123/jenkins-projet:latest'
        REMOTE_SERVER = 'ahmed@192.168.162.129'
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt Git
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Compiler le projet
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Exécuter les tests unitaires
                sh 'mvn test'
            }
            post {
                always {
                    // Archiver les rapports de test
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                // Emballer le projet (générer le fichier jar/war)
                sh 'mvn package'
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub') {
                        def appImage = docker.build("${env.IMAGE_NAME}")
                        appImage.push()
                    }
                }
            }
        }
        stage('Deploy to Remote Server') {
            steps {
                script {
                    sshagent(['ssh-key']) {
                        sh """
                        ssh -o StrictHostKeyChecking=no ${env.REMOTE_SERVER} << EOF
                        docker pull ${env.IMAGE_NAME}
                        docker stop app || true
                        docker rm app || true
                        docker run -d --name app -p 8080:8080 ${env.IMAGE_NAME}
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            // Actions après succès du pipeline
            echo 'Pipeline exécuté avec succès.'
        }
        failure {
            // Actions en cas d'échec du pipeline
            echo 'Le pipeline a échoué.'
        }
    }
}
