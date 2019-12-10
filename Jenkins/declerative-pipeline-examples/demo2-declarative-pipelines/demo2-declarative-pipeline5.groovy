
/*********
Multiple job example
Do not run on pipline job didn't work
**********/

pipeline {
    agent {
        docker 'levep79/jdk-alpine'
    }
    stages {
        stage('Example Build') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Example Deploy') {
            agent {
                docker 'maven:3.5-alpine'
            }
            when {
                beforeAgent true
                branch 'master'
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}