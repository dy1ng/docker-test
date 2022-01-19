package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'BuildA'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("BuildA")) {
    params {
        add {
            param("env.GIT_CURL_VERBOSE", "%teamcity.build.checkoutDir%/git_trace.log")
        }
        add {
            param("teamcity.git.useBuildEnv", "true")
        }
        add {
            param("env.GIT_TRACE", "%teamcity.build.checkoutDir%/git_trace.log")
        }
        add {
            param("env.GIT_TRACE_PERFORMANCE", "%teamcity.build.checkoutDir%/git_trace.log")
        }
    }

    expectSteps {
        script {
            id = "RUNNER_5"
            scriptContent = "echo %env.pass_for_docker%%"
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
            dockerPull = true
            dockerImage = "alpine:latest"
        }
        script {
            id = "RUNNER_7"
            enabled = false
            scriptContent = "ssh -o StrictHostKeyChecking=no user:%env.pass_for_docker%@172.31.140.46"
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
            dockerPull = true
            dockerImage = "kroniak/ssh-client:latest"
        }
    }
    steps {
        update<ScriptBuildStep>(0) {
            id = "RUNNER_5"
            clearConditions()
            dockerImage = ""
        }
    }

    features {
        val feature1 = find<PullRequests> {
            pullRequests {
                id = "BUILD_EXT_3"
                vcsRootExtId = "TestProject_HttpsGithubComDy1ngTcTestProjectRefsPullMerge"
                provider = github {
                    authType = vcsRoot()
                    filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
                }
            }
        }
        feature1.apply {
            vcsRootExtId = "TestProject_HttpsGithubComDy1ngTcTestProjectMasterWPullRequests"
        }
    }
}
