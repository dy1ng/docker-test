package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object DependencyCheck2 : BuildType({
    name = "Dependency check 2"

    params {
        param("dep_parameter", "${DependencyCheck1.depParamRefs["parameter1"]}")
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit3)
    }

    steps {
        script {
            scriptContent = """echo "Dependency Parameter Value: %dep_parameter%""""
        }
    }

    triggers {
        finishBuildTrigger {
            buildType = "${DependencyCheck1.id}"
            successfulOnly = true
            branchFilter = """
                +:<default>
                +:/develop
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(DependencyCheck1) {
        }
    }
})
