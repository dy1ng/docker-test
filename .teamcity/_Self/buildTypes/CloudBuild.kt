package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CloudBuild : BuildType({
    name = "Cloud build"

    requirements {
        exists("docker.version")
        exists("docker.server.osType")
        contains("system.agent.name", "kube")
    }
})
