package _Self.buildTypes

import _Self.vcsRoots.BitbucketDy1ng
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.replaceContent
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MSBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.msBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object SimpleBuild : BuildType({
    name = "Simple build"

    artifactRules = "+:Source-Code/hello-world.py"

    params {
        param("pidt_password", "123456")
        text("prodBuildVersion", "", label = "Prod Artifacts Version", description = "Version of release artifacts in pom, for example 18.8.0.0", display = ParameterDisplay.PROMPT, allowEmpty = false)
        param("env.SBT_CREDENTIALS", "%system.teamcity.build.checkoutDir%/credentials")
        password("secure", "credentialsJSON:a139135a-f0df-44be-a637-dad1a7e773c4", display = ParameterDisplay.HIDDEN)
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit6, "+:Source-Code/hello-world.py=>Source-Code/hello-world.py")
        root(_Self.vcsRoots.BitbucketDy1ng)
    }

    steps {
        msBuild {
            enabled = false
            path = "path/to/file"
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V3_5
        }
        script {
            scriptContent = """
                echo %env.SBT_CREDENTIALS%
                sleep 10
            """.trimIndent()
        }
        script {
            name = "sleep 10 seconds"
            scriptContent = "sleep 10"
        }
        script {
            name = "Execute always"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            scriptContent = "echo Some text"
        }
        step {
            type = "TestProject1_MultipleVcsRoots"
            executionMode = BuildStep.ExecutionMode.ALWAYS
        }
        python {
            executionMode = BuildStep.ExecutionMode.ALWAYS
            command = script {
                content = """print("execute always")"""
            }
        }
    }

    features {
        pullRequests {
            enabled = false
            vcsRootExtId = "${BitbucketDy1ng.id}"
            provider = bitbucketServer {
                authType = vcsRoot()
            }
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print"
            replacement = "print1"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print1"
            replacement = "print2"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print2"
            replacement = "print3"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print3"
            replacement = "print4"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print4"
            replacement = "print5"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print5"
            replacement = "print6"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print6"
            replacement = "print7"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print7"
            replacement = "print8"
        }
        replaceContent {
            fileRules = "+:Source-Code/hello-world.py"
            pattern = "print8"
            replacement = "print9"
        }
    }
})
