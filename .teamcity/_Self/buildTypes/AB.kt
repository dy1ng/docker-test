package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object AB : BuildType({
    name = "A + B"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
            enabled = false
        }
    }

    features {
        commitStatusPublisher {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:0964f9c9-8c50-44f2-9691-387a7ac025d3"
                }
            }
        }
    }

    dependencies {
        dependency(BuildA) {
            snapshot {
            }

            artifacts {
                artifactRules = "*"
            }
        }
        dependency(BuildB) {
            snapshot {
            }

            artifacts {
                artifactRules = "*"
            }
        }
    }
})
