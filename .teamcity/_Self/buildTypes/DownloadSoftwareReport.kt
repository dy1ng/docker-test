package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object DownloadSoftwareReport : BuildType({
    name = "Download software report"

    artifactRules = "resources/software.report.md"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit, "+:resources => resources")
    }

    steps {
        script {
            name = "Dummy step"
            scriptContent = """echo "Dummy step""""
        }
    }
})
