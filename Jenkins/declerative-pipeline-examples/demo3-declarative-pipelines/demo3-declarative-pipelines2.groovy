@Library('jenkins-library@master') _

pipeline {
    agent any
    stages {
        stage ('Git Checkout') {
        steps {
            gitCheckout(
                branch: "master",
                url: "https://github.com/lev-tmp/jenkins2-course-spring-petclinic.git"
            )}
        }
    }
}