pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
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
                sh 'mvn clean compile'
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
