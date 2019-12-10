pipeline {
    agent { docker 'maven:3.5-alpine' }
    stages {
        stage ('Checkout') {
          steps {
            git 'https://github.com/lev-tmp/jenkins2-course-spring-petclinic.git'
          }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
    }
}
