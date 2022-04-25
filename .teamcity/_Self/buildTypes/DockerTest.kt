package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object DockerTest : BuildType({
    name = "Docker-test"

    params {
        param("test_param", "default value")
        param("teamcity.build.serviceMessages.logOriginal", "true")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Check files exist"
            scriptContent = "ls -lh"
        }
        dockerCommand {
            name = "Build image"
            commandType = build {
                source = file {
                    path = "Dockerfile"
                }
                namesAndTags = "docker-test:latest"
                commandArgs = "--pull"
            }
        }
        script {
            name = "Echo the parameter before chaning"
            scriptContent = "echo %test_param%"
        }
        script {
            name = "Update the parameter in docker"
            scriptContent = "docker run docker-test:latest"
        }
        script {
            name = "Echo the parameter after chaning"
            scriptContent = "echo %test_param%"
        }
    }

    triggers {
        vcs {
            enabled = false
        }
    }
})
