package _Self.buildTypes

import _Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object AB : BuildType({
    name = "A + B"

    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "%buildN%"

    params {
        param("buildN", "${BuildA.depParamRefs.buildNumber}")
    }

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
            vcsRootExtId = "${HttpsGithubComDy1ngTcTestProjectMasterWPullRequests.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:9746a541-2862-4203-a1e7-fbed268ff191"
                }
            }
            param("github_oauth_user", "dy1ng")
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
