////////////////////////////////////////////////////////////////////////////////
//
// Jenkins pipeline
//
////////////////////////////////////////////////////////////////////////////////

void asDeployUser(Closure<?> closure) {
  sshagent(['3d3a2bfc-1fcb-4d23-9f0e-f19d16a080cb']) {
    closure()
  }
}

// -------------------------------------------------------------------- Pipeline
pipeline {
    agent any

    triggers {
         pollSCM('* * * * *')
     }

    stages{
        stage('Checkout') {
            steps {
              git branch: "${params.BRANCH}", credentialsId: '', url: 'https://github.com/operep/simple-webapp-sample.git'
            }
        }
        stage('Setting up') {
            steps {
              sh 'rm src/test/resources/test.properties && touch src/test/resources/test.properties'
              sh 'echo "test.local=false" > src/test/resources/test.properties'
              sh 'echo "test.gridUrl=http://$GRID_URL:4444/wd/hub" >> src/test/resources/test.properties'
              sh 'echo "test.mainUrl=https://www.amazon.co.uk" >> src/test/resources/test.properties'
              sh 'cat src/test/resources/test.properties'
            }
        }
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage ('Deployments'){
            parallel{
                stage ('Deploy to Staging'){
                    steps {
                        if (${params.BRANCH} == "master"){
                            asDeployUser({
                                sh "scp -v -o StrictHostKeyChecking=no -r /var/lib/jenkins/workspace/sample-webapp-pipeline/target/*.war ec2-user@${params.TOMCAT_DEV}:/var/lib/tomcat7/webapps"
                            })
                        }
                        echo 'Not deploying to stage as this is not master branch...'
                    }
                }
            }
        }

        stage ('Tests'){
            steps {
                sh 'echo "Test"'
                   sh """
                        mkdir -p selenium-screenshots/error
                        /usr/bin/mvn -B -f pom.xml clean test -X
                      """
            }
        }

        stage ('Clean up'){
            steps {
                cleanWs()
            }
        }
    }
}