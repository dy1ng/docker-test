package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object A2Develop : BuildType({
    templates(SimpleBuildTemplate)
    name = "A2: Develop"

    triggers {
        vcs {
            id = "vcsTrigger"
            enabled = false
            branchFilter = "+:develop"
        }
    }
})
