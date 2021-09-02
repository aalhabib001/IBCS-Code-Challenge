pipeline {
   agent any

   stages {
      stage('Git Checkout') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/aalhabib001/IBCS-Code-Challenge.git'

         }
      }
      stage('Build') {
         steps {

            // Run Maven on a Unix agent.
            sh "mvn clean package"

         }
      }
      stage('Deploy') {
         steps {

            //Run Maven on a Unix agent.

            sh """
            fuser -k 8000/tcp
            JENKINS_NODE_COOKIE=dontKillMe nohup java -jar ./target/ibcs-code-challenge-0.0.1-SNAPSHOT.jar &
            """

         }
      }
   }
}