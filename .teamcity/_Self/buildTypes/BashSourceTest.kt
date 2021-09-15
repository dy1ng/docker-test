package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BashSourceTest : BuildType({
    name = "Bash Source test"

    steps {
        script {
            name = "create sh. script"
            scriptContent = """
                cat > source_test.sh << EOM
                echo "setting env.TEST_PARAM via service messages"
                echo "##teamcity[setParameter name='env.TEST_PARAM' value='SUCCESS']"
                EOM
            """.trimIndent()
        }
        script {
            name = "source the script"
            scriptContent = "source source_test.sh"
        }
        script {
            name = "check parameters"
            scriptContent = """
                echo "This is the value of TC parameter: %env.TEST_PARAM%"
                echo "This is the value of the corresponding environmental variable: ${'$'}TEST_PARAM"
            """.trimIndent()
        }
    }
})
