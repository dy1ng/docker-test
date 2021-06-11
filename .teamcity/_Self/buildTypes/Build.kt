package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
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

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_1"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = password {
                    userName = "dy1ng"
                    password = "credentialsJSON:a500278e-89e1-4869-930e-2e5e8d7193bd"
                }
            }
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
