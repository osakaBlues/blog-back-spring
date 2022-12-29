def errorHendler(error) {
  print(error)
  env.cloneResult = false
  currentBuild.result = 'FAILURE'
}

pipeline {
  agent any

  environment {
  		DOCKERHUB_CREDENTIALS=credentials('docker_hub_ysi')
  }

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
    stage('Docker hub login') {
      steps {
        script {
          try {
            sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin"
          } catch(error) {
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
          } catch(error){
            errorHendler(error)
            return
          }
        }
      }
    }
  }
}