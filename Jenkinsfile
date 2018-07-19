def label = "mypod-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
    containerTemplate(name: 'java', image: 'airdock/oracle-jdk:1.8', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:v2.7.2', command: 'cat', ttyEnabled: true)
  ],
  volumes:[
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
  ]) {

    node(label) {

        checkout scm

        stage('Build') {
            container('java') {
                try{
                    sh './gradlew clean build'
                }catch (err) {
                    slackSend channel: '#kubefire', color: '#FF0000', message: 'Project [portal] build failed'
                    currentBuild.result = 'FAILURE'
                }
            }
        }

        stage('Analyze') {
            container('java') {
                try{
                    // TODO: 2/17/18 Enable tests
                    sh './gradlew sonarqube'
                    slackSend channel: '#kubefire', color: '#00FF00', message: 'Project [portal] build success'
                }catch (err) {
                    slackSend channel: '#kubefire', color: '#FF0000', message: 'Project [portal] build failed'
                    currentBuild.result = 'FAILURE'
                }
            }
        }

    }
}

