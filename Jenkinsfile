pipeline {

    agent any

    tools {
        maven 'Maven-3.9.16'
        jdk 'JDK-22'
    }

    stages {

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'

                bat 'docker build -t software-testing-app .'
            }
        }

        stage('Run Application') {
            steps {
                echo 'Starting application container...'

                bat 'docker rm -f software-testing-container 2>NUL || exit /b 0'

                bat 'docker run -d --name software-testing-container -p 8080:80 software-testing-app'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo 'Running Selenium automated tests...'

                bat 'mvn clean test'
            }
        }
    }

    post {

        always {
            echo 'Stopping Docker container...'

            bat 'docker rm -f software-testing-container 2>NUL || exit /b 0'

            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'CI/CD Pipeline completed successfully.'
        }

        failure {
            echo 'CI/CD Pipeline failed.'
        }
    }
}
