package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object SimpleBuildTemplate : Template({
    name = "Simple Build Template"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }
})
