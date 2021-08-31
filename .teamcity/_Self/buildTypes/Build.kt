package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Build : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Build С"

    artifactRules = "+:*"

    triggers {
        vcs {
            id = "vcsTrigger"
            enableQueueOptimization = false
        }
    }

    dependencies {
        dependency(AB) {
            snapshot {
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_6"
                artifactRules = "*"
            }
        }
    }
})
