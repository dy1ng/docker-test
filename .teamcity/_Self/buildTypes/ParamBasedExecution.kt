package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object ParamBasedExecution : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Param-based Execution"

    steps {
        script {
            name = "Execute if Parameter doesn't have value"
            id = "RUNNER_21"

            conditions {
                matches("ololo", "(?:)")
            }
            scriptContent = "echo Empty Parameter"
        }
    }
})
