package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CompositeConfig : BuildType({
    name = "CompositeConfig"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        showDependenciesChanges = true
    }

    dependencies {
        dependency(UpdateSoftwareInfoViaPr) {
            snapshot {
            }

            artifacts {
                artifactRules = "+:software.report.md => ubuntu/software.report.md"
            }
        }
    }
})
