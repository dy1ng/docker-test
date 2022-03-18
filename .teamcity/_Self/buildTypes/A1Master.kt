package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object A1Master : BuildType({
    templates(SimpleBuildTemplate)
    name = "A1: Master"

    params {
        checkbox("pr_enabled", "", label = "Send PR to Github", description = "This option defines whether a PR is created during the build or not",
                  checked = "true", unchecked = "false")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            enabled = false
            branchFilter = "+:master"
        }
    }
})
