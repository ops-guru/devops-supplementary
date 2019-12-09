pipeline {
    agent none
    stages {
        stage('Example Build') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Example Deploy') {
            when {
                triggeredBy "TimerTrigger"
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}