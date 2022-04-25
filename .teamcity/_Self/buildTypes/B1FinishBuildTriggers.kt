package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object B1FinishBuildTriggers : BuildType({
    templates(SimpleBuildTemplate)
    name = "B1. Finish Build triggers"

    params {
        param("teamcity.buildQueue.allowMerging", "false")
    }

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_5"
            enabled = false
            buildType = "${A1Master.id}"
        }
        finishBuildTrigger {
            id = "TRIGGER_6"
            enabled = false
            buildType = "${A2Develop.id}"
        }
    }

    dependencies {
        snapshot(A1Master) {
        }
        snapshot(A2Develop) {
        }
    }
})
