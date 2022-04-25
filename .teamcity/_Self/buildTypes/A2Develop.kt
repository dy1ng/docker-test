package _Self.buildTypes

import _Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object A2Develop : BuildType({
    templates(SimpleBuildTemplate)
    name = "A2: Develop"

    steps {
        script {
            id = "RUNNER_12"
            scriptContent = """echo "This is a build step, yay!""""
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            branchFilter = """
                +:*
                -:develop
            """.trimIndent()
        }
    }

    features {
        pullRequests {
            id = "BUILD_EXT_12"
            vcsRootExtId = "${HttpsGithubComDy1ngTcTestProjectGit.id}"
            provider = github {
                authType = vcsRoot()
                filterTargetBranch = "+:refs/heads/master"
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})
