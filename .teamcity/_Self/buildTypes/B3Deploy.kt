package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object B3Deploy : BuildType({
    templates(SimpleBuildTemplate)
    name = "B3. Deploy"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_5"
            buildType = "${A1Master.id}"
        }
        finishBuildTrigger {
            id = "TRIGGER_6"
            buildType = "${A2Develop.id}"
        }
        vcs {
            id = "vcsTrigger"
            enabled = false
            enableQueueOptimization = false
        }
    }

    dependencies {
        snapshot(A1Master) {
        }
        snapshot(A2Develop) {
        }
    }
})
