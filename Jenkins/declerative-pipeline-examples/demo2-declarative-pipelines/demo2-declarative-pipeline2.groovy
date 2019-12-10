// The triggers directive defines the automated ways in which the Pipeline should be re-triggered. 
// The triggers currently available are cron, pollSCM and upstream.
pipeline {
    agent any
    triggers {
        cron('*/1 * * * *')
    }
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }
}