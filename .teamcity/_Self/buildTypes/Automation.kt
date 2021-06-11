package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Automation : BuildType({
    name = "Automation"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)
    }

    triggers {
        vcs {
        }
    }
})
