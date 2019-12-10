// Stages in Declarative Pipeline may have a stages section containing a list of nested stages 
// to be run in sequential order. Note that a stage must have one and only one of 
// steps, stages, parallel, or matrix. It is not possible to nest a parallel or matrix block 
// within a stage directive if that stage directive is nested within a parallel or matrix block itself. 
// However, a stage directive within a parallel or matrix block can use all other functionality 
// of a stage, including agent, tools, when, etc.

pipeline {
    agent none
    stages {
        stage('Non-Sequential Stage') {
            agent {
                docker 'levep79/jdk-alpine'
            }
            steps {
                echo "On Non-Sequential Stage"
            }
        }
        stage('Sequential') {
            agent {
                docker 'maven:3.5-alpine'
            }
            environment {
                FOR_SEQUENTIAL = "some-value"
            }
            stages {
                stage('In Sequential 1') {
                    steps {
                        echo "In Sequential 1"
                    }
                }
                stage('In Sequential 2') {
                    steps {
                        echo "In Sequential 2"
                    }
                }
                stage('Parallel In Sequential') {
                    parallel {
                        stage('In Parallel 1') {
                            steps {
                                echo "In Parallel 1"
                            }
                        }
                        stage('In Parallel 2') {
                            steps {
                                echo "In Parallel 2"
                            }
                        }
                    }
                }
            }
        }
    }
}
