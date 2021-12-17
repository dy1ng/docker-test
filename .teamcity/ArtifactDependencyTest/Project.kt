package ArtifactDependencyTest

import ArtifactDependencyTest.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ArtifactDependencyTest")
    name = "Artifact Dependency Test"

    buildType(ArtifactDependencyTest_ResolveArtifacts)
    buildType(ArtifactDependencyTest_DependentBuild)
    buildType(ArtifactDependencyTest_GetArtifactsViaRest)
})
