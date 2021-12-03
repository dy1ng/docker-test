package ArtifactDependencyTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ArtifactDependencyTest_DependentBuild : BuildType({
    name = "Dependent Build"

    artifactRules = """
        +:checksum
        +:project
    """.trimIndent()

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectGit)
    }
})
