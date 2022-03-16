package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object Deployment : BuildType({
    name = "Deployment"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)
    }

    triggers {
        finishBuildTrigger {
            buildType = "${Automation.id}"
            branchFilter = "+:*"
        }
    }

    dependencies {
        snapshot(Automation) {
        }
    }
})
