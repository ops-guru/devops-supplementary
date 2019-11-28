node {
   stage('Checkout') {
      git 'https://github.com/levep/my-awesome-project.git'
   }
   stage('Say Hello') {
         sh "./say_hello.sh"
   }
} 