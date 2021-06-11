package _Self.buildTypes

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
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:0964f9c9-8c50-44f2-9691-387a7ac025d3"
                }
            }
        }
    }
})
