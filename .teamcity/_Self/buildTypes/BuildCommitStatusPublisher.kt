package _Self.buildTypes

import _Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildCommitStatusPublisher : Template({
    name = "Build % Commit Status Publisher"

    publishArtifacts = PublishMode.SUCCESSFUL

    params {
        param("system.sdfs", "123")
        param("env.dfgsdfgh", "234")
        param("ololo", "%build.number%")
        param("test", "1")
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests)
    }

    steps {
        script {
            name = "simple echo"
            id = "RUNNER_1"
            scriptContent = """echo "Step echo""""
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_2"
            vcsRootExtId = "${HttpsGithubComDy1ngTcTestProjectMasterWPullRequests.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:19b447d6-628f-49e4-aad9-31bb547635b9"
                }
            }
        }
    }
})
