package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SshExecTest : BuildType({
    templates(SimpleBuildTemplate)
    name = "SSH-Exec-Test"

    steps {
        step {
            id = "RUNNER_14"
            type = "ssh-exec-runner"
            param("jetbrains.buildServer.deployer.username", "test-user")
            param("jetbrains.buildServer.sshexec.command", "id")
            param("jetbrains.buildServer.deployer.targetUrl", "172.31.136.48")
            param("jetbrains.buildServer.sshexec.authMethod", "DEFAULT_KEY")
        }
    }

    requirements {
        equals("teamcity.agent.name", "VSphere-Ubuntu-Agent-1", "RQ_5")
    }
    
    disableSettings("vcsTrigger")
})
