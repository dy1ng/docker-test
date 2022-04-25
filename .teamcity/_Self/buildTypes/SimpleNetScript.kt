package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.csharpScript

object SimpleNetScript : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Simple .NET script"

    steps {
        csharpScript {
            id = "RUNNER_11"
            content = """
                string version = Environment.GetEnvironmentVariable("TEAMCITY_VERSION");
                Console.WriteLine(version);
            """.trimIndent()
            tool = "%teamcity.tool.TeamCity.csi.DEFAULT%"
        }
    }
    
    disableSettings("RUNNER_1")
})
