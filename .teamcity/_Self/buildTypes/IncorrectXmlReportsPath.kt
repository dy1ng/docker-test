package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IncorrectXmlReportsPath : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Incorrect XML Reports Path"
})
