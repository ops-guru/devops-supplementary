pipeline {
    agent { docker 'maven:3.5-alpine' }
    stages {
        stage('Example Build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
