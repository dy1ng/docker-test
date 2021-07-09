package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object UpdateSoftwareInfoViaPr : BuildType({
    name = "Update Software Info via PR"

    params {
        password("hiddenToken", "credentialsJSON:64b6255c-89a5-4f74-add0-402ed09ee64b", display = ParameterDisplay.HIDDEN, readOnly = true)
        password("github_token", "credentialsJSON:c7d9a019-4a0e-4062-8f4c-467c55d64f54", display = ParameterDisplay.HIDDEN)
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTeamcityDocumentation)
    }

    steps {
        script {
            name = "Checkout into a new branch and create a PR"
            scriptContent = """
                pr_body_msg="PR created by %teamcity.serverUrl%/buildConfiguration/%system.teamcity.buildType.id%/%teamcity.build.id%"
                commit_msg="topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md: update software list"
                
                #if [[ ${'$'}(git status) == *"fatal: not a git repository"* ]]
                #then
                #	git init
                #fi
                remotes=${'$'}(git remote -v | grep "https://github.com/JetBrains/teamcity-documentation.git" | wc -l)
                if [[ ${'$'}remotes -eq 0 ]] 
                then
                	git remote add upstream https://github.com/JetBrains/teamcity-documentation.git
                fi
                
                repo_head=${'$'}(git remote show upstream | awk '/HEAD branch/ {print ${'$'}NF}')
                
                git fetch upstream ${'$'}repo_head
                git checkout -b patch-%teamcity.build.id% upstream/${'$'}repo_head
                
                header=${'$'}(cat << EOM
                [//]: # (title: Preinstalled Software on TeamCity Cloud Ubuntu Agents)
                [//]: # (auxiliary-id: Preinstalled Software on TeamCity Cloud Ubuntu Agents)
                
                <chunk id="ubuntu-jb-agents">
                EOM
                )
                footer=${'$'}(cat << EOM
                </chunk> 
                EOM
                )
                body=${'$'}(cat software.report.md)
                
                cat > topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md << EOM
                ${'$'}header
                
                ${'$'}body
                
                ${'$'}footer
                EOM
                
                git add topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md
                git commit -m "${'$'}commit_msg"
                git push origin patch-%teamcity.build.id%
                json="{\"head\":\"patch-%teamcity.build.id%\", \"base\":\"${'$'}repo_head\", \"body\":\"${'$'}{pr_body_msg}\", \"title\":\"Update topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md\"}"
                echo "${'$'}json"
                curl -u dy1ng:%github_token% -X POST -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/dy1ng/teamcity-documentation/pulls -d "${'$'}json"
            """.trimIndent()
        }
        script {
            name = "Find and close old PRs"
            scriptContent = """
                #curl -u dy1ng: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 -X POST -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/dy1ng/teamcity-documentation/pulls -d "${'$'}json"
                
                #Get list of PRs
                PRS=${'$'}(curl -u dy1ng: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 -X GET -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/dy1ng/teamcity-documentation/pulls)
            """.trimIndent()
        }
    }

    dependencies {
        artifacts(DownloadSoftwareReport) {
            buildRule = lastSuccessful()
            artifactRules = "+:software.report.md"
        }
    }
})
