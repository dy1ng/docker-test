package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildStatusCheck : BuildType({
    name = "Build status check"

    params {
        param("teamcity.build.serviceMessages.logOriginal", "true")
    }

    steps {
        script {
            name = "Successful build step"
            scriptContent = """
                #!/usr/local/bin/python3
                print('This build step will exit with zero exit code')
                exit(0)
            """.trimIndent()
        }
        script {
            name = "Run this step only if build is successful"
            executionMode = BuildStep.ExecutionMode.RUN_ON_SUCCESS
            scriptContent = """
                #!/usr/local/bin/python3
                print('Build is still successful!')
            """.trimIndent()
        }
        script {
            name = "Set build status to FAILED"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            scriptContent = """
                #!/usr/local/bin/python3
                print('The build will now fail!')
                print('##teamcity[buildStatus status=\'FAILURE\' text=\'{build.status.text} let the build fail!\']')
            """.trimIndent()
        }
        script {
            name = "Step to be skipped"
            executionMode = BuildStep.ExecutionMode.RUN_ON_SUCCESS
            scriptContent = """
                #!/usr/local/bin/python3
                print('This will never be printed')
            """.trimIndent()
        }
        script {
            name = "Set build status to SUCCESS (1)"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            scriptContent = """
                #!/usr/local/bin/python3
                print('The build will now succeed!')
                print('##teamcity[buildStatus status=\'SUCCESS\' text=\'{build.status.text} let the build succeed!\']')
            """.trimIndent()
        }
    }
})
