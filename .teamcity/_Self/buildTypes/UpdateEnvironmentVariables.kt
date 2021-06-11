package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object UpdateEnvironmentVariables : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Update Environment Variables"

    steps {
        script {
            name = "export PATH"
            id = "RUNNER_25"
            enabled = false
            scriptContent = """
                echo "##teamcity[setParameter name='test' value='${'$'}0']"
                echo "Current PATH: ${'$'}PATH"
                echo "Current env.PATH: %env.PATH%"
                export PATH=${'$'}PATH:~/opt/bin/npm/serverles
            """.trimIndent()
        }
        script {
            name = "source previous script"
            id = "RUNNER_30"
            enabled = false
            scriptContent = "source %test%"
        }
        python {
            id = "RUNNER_32"
            command = script {
                content = """
                    import os
                    
                    path = os.environ["PATH"]
                    print(path)
                    os.environ["PATH"] = path + ":/opt/npm/serverless"
                    print(os.environ["PATH"])
                    print("%env.PATH%")
                """.trimIndent()
            }
        }
        script {
            name = "echo PATHs"
            id = "RUNNER_31"
            scriptContent = """
                echo "Current PATH: ${'$'}PATH"
                echo "Current env.PATH: %env.PATH%"
                echo "Checkout directory: %system.teamcity.build.checkoutDir%"
                echo "Current directory: ${'$'}(pwd)"
            """.trimIndent()
        }
    }
    
    disableSettings("RUNNER_1")
})
