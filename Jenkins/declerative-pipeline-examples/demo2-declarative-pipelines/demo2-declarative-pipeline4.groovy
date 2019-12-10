// The when directive allows the Pipeline to determine whether the stage should be executed
// depending on the given condition. The when directive must contain at least one condition. 
// If the when directive contains more than one condition, all the child conditions must return
// true for the stage to execute.

pipeline {
    agent any
    stages {
        stage('Example Build') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Example Deploy') {
            when {
                branch 'master'
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}