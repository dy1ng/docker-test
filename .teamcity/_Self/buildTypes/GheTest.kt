package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object GheTest : BuildType({
    name = "GHE_Test"

    vcs {
        root(_Self.vcsRoots.GheTeamcityAdminMefremovTestRepo)
    }

    steps {
        script {
            name = "echo"
            scriptContent = "echo Yay!"
        }
    }
})
