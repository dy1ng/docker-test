package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object B2vcsBuildTrigger : BuildType({
    templates(SimpleBuildTemplate)
    name = "B2. VCS Build trigger"

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
