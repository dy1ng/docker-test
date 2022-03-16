package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object DependencyCheck1 : BuildType({
    name = "Dependency check 1"

    params {
        param("parameter1", "master")
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit4)
    }

    steps {
        script {
            scriptContent = """echo "Original parameter value: %parameter1%""""
        }
    }
})
