package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object BuildD : BuildType({
    name = "Build D"

    artifactRules = "+:**/Source-Code/**"
    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests, "-:.", "+:Source-Code")
    }

    steps {
        script {
            name = "simple echo"
            scriptContent = """echo "Step echo""""
        }
    }

    triggers {
        finishBuildTrigger {
            buildType = "${Build.id}"
            branchFilter = "+:*"
        }
    }

    features {
        commitStatusPublisher {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:0964f9c9-8c50-44f2-9691-387a7ac025d3"
                }
            }
        }
    }

    dependencies {
        dependency(Build) {
            snapshot {
            }

            artifacts {
                artifactRules = "*"
            }
        }
    }
})
