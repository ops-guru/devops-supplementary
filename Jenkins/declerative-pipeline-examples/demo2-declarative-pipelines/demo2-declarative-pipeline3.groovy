// The input directive on a stage allows you to prompt for input, using the input step. 
// The stage will pause after any options have been applied, 
// and before entering the agent block for that stage or evaluating the when condition of the stage. 
// If the input is approved, the stage will then continue. 
// Any parameters provided as part of the input submission 
// will be available in the environment for the rest of the stage.
pipeline {
    agent any
    stages {
        stage('Example') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, nice to meet you."
            }
        }
    }
}