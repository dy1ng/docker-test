package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.sshExec

object SshExecTest : BuildType({
    templates(SimpleBuildTemplate)
    name = "SSH-Exec-Test"

    steps {
        sshExec {
            id = "RUNNER_14"
            commands = "id"
            targetUrl = "172.31.136.48"
            authMethod = defaultPrivateKey {
                username = "test-user"
            }
        }
    }

    requirements {
        equals("teamcity.agent.name", "VSphere-Ubuntu-Agent-1", "RQ_5")
    }
    
    disableSettings("vcsTrigger")
})
