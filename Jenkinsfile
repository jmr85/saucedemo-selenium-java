pipeline {
    agent any

    triggers {
        // Runs at 3 AM every day
        cron('H 3 * * *')
    }

    parameters {
        // Create a choice parameter named 'TEST'
        choice(name: 'TEST', choices: ['PurchaseOrderTest'], description: 'Seleccione el test a ejecutar')
    }

    options {
        githubProjectProperty(projectUrlStr: "https://github.com/jmr85/saucedemo-selenium-java")
    }

    tools {
        maven 'apache-maven-3.9.8'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/jmr85/saucedemo-selenium-java.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh "mvn test -P=${params.TEST}"
                    } else {
                        bat "mvn test -P=${params.TEST}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Generate a TestNG report based on the test results
            // You must previously have TestNG Results Plugin installed in Jenkins
            testNG(reportFilenamePattern: '**/testng-results.xml')
        }
    }
}
