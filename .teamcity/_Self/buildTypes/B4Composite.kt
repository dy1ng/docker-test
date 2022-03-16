package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object B4Composite : BuildType({
    name = "B4. Composite"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
        }
    }

    dependencies {
        snapshot(A1Master) {
        }
        snapshot(A2Develop) {
        }
    }
})
