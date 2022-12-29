def errorHandler(error) {
  print(error)
  env.cloneResult = false
  currentBuild.result = 'FAILURE'
}

pipeline {
  agent any

  environment {
  		DOCKERHUB_CREDENTIALS=credentials('docker_hub_ysi')
  }
  options {
      withAWS(credentials:'aws_key')
  }

  stages {
    stage('Docker build') {
      steps {
        script {
          try {
            sh 'docker build -t seongilyoon/blog-backend-spring:0.1 .'
           } catch(error){
            errorHandler(error)
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
            errorHandler(error)
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
            errorHandler(error)
            return
          }
        }
      }
    }
  }
  stage('Set env File') {
    steps {
      script {
        try {
          sh "echo \"${params.env}\" > .env"
          sh "echo \"${params.compose_file}\" > docker-compose.yml"
        } catch(error) {
          errorHandler(error)
          return
        }
      }
    }
  }
  stage('zip files'){
    steps{
      script{
        try {
          sh "rm *.tar"
          sh "tar -cf back-spring.tar .env docker-compose.yml"
        } catch (error) {
          errorHendler(error)
          return
        }
      }
    }
  }
  stage('upload to S3'){
    steps{
      script{
        try{
          withAWS(region:"ap-northeast-1") {
            s3Upload(
              file:"back-spring.tar",
              bucket:"osakabluesblog",
              path:"back-spring.tar")
          }
        } catch(error){
          errorHendler(error)
          return
        }
      }
    }
  }
  stage('deploy to EC2'){
    steps{
      script{
        try{
          withAWS(region:'ap-northeast-1') {
            createDeployment(
              applicationName: 'OsakaBluesblog',
              deploymentGroupName: 'blog-back-spring',
              deploymentConfigName: 'CodeDeployDefault.OneAtATime',
              description: 'test deploy to back-spring',
              waitForCompletion: true,
              s3Bucket: 'osakabluesblog',
              s3Key: 'back-spring.tar',
              s3BundleType: 'tar',
              fileExistsBehavior: 'OVERWRITE',
            )
          }
        } catch(error){
          errorHandler(error)
          return
        }
      }
    }
  }

  post {
    always {
      script {
        sh 'docker logout'
      }
    }
  }
}