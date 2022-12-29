def errorHendler(error) {
  print(error)
  env.cloneResult = false
  currentBuild.result = 'FAILURE'
}

pipeline {
  agent any

  stages {
    stage('Docker build') {
      steps {
        script {
          try {
            sh 'docker build -t seongilyoon/blog-backend-spring:0.1 .'
           } catch(error){
            errorHendler(error)
            return
          }
        }
      }
    }
    stage('upload to DockerHub') {
      steps {
        script {
          try {
            sh "docker push seongilyoon/blog-backend-spring:0.1"
            }
          } catch(error){
            errorHendler(error)
            return
          }
        }
      }
    }
  }
}