package ArtifactDependencyTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ArtifactDependencyTest_ResolveArtifacts : BuildType({
    name = "Resolve Artifacts"

    artifactRules = "+:project/__init__.py => project/"

    dependencies {
        artifacts(ArtifactDependencyTest_DependentBuild) {
            buildRule = lastSuccessful("+:*")
            artifactRules = """
                +:checksum
                +:project/ => new_folder/
            """.trimIndent()
        }
    }
})
