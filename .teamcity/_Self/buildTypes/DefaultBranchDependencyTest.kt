package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DefaultBranchDependencyTest : BuildType({
    name = "Default branch dependency test"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)
    }

    dependencies {
        artifacts(DefaultBranchTest) {
            buildRule = tag("develop_was_default", "+:develop")
            artifactRules = "+:*"
        }
    }
})
