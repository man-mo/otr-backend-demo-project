pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        sh '''pwd
./gradlew clean build'''
      }
    }
    stage('build') {
          steps {
            archiveArtifacts(artifacts: 'build/libs/account-management-0.0.1-SNAPSHOT.jar', fingerprint: true)
          }
        }
    stage('deploy-qa') {
      parallel {
        stage('Deploy current version to qa') {
          steps {
              sh '''cd ../../jobs/jpower-account-management/branches/master/builds/$BUILD_NUMBER/archive/build/libs/;
                    lsof -ti:8090 | xargs kill || true;
                    java -jar -Dfile.encoding=UTF8 account-management-0.0.1-SNAPSHOT.jar --spring.profiles.active=qa > /tmp/service-log/account-management-boot-run.log &'''
          }
        }
      }
    }

  }
}