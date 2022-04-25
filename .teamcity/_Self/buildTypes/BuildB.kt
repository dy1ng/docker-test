package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BuildB : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Build B"

    artifactRules = "+:Source-Code/*"
})
