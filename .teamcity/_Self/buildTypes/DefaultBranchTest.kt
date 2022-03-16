package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DefaultBranchTest : BuildType({
    name = "Default branch test"

    artifactRules = "+:checksum"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit5)
    }
})
