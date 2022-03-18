package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object A1Master : BuildType({
    templates(SimpleBuildTemplate)
    name = "A1: Master"

    triggers {
        vcs {
            id = "vcsTrigger"
            enabled = false
            branchFilter = "+:master"
        }
    }
})
