package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object UpdateSoftwareInfoViaPr : BuildType({
    name = "Update Software Info via PR"

    params {
        param("teamcity.cloud.documentation.repo_name", "dy1ng/teamcity-documentation")
        param("teamcity.cloud.documentation.git.user.name", "Mikhail Efremov")
        password("github_token", "credentialsJSON:c7d9a019-4a0e-4062-8f4c-467c55d64f54", display = ParameterDisplay.HIDDEN)
        password("hiddenToken", "credentialsJSON:64b6255c-89a5-4f74-add0-402ed09ee64b", display = ParameterDisplay.HIDDEN, readOnly = true)
        param("teamcity.cloud.documentation.branch.name.prefix", "dev-patch-")
        param("teamcity.cloud.documentation.login", "dy1ng")
        param("teamcity.cloud.documentation.token", "%github_token%")
        param("teamcity.cloud.documentation.git.user.email", "mikhail.efremov@jetbrains.com")
    }

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTeamcityDocumentation)
    }

    steps {
        script {
            name = "Checkout into a new branch and create a PR"
            scriptContent = """
                #!/bin/bash
                
                pr_body_msg="PR created by %teamcity.serverUrl%/buildConfiguration/%system.teamcity.buildType.id%/%teamcity.build.id%"
                commit_msg="update agents' preinstalled software list"
                
                echo "###### Check if the script runs is in a git repository"
                if [ -d .git ]
                then
                    echo "######### It is. Proceeding."
                else
                    echo "######### It is not. Something is wrong. Probably checkout rules. Exiting"
                    exit 1
                fi
                echo "######"
                
                echo "###### Set username and email for this repository"
                git config user.name "%teamcity.cloud.documentation.git.user.name%" || exit 1
                git config user.email "%teamcity.cloud.documentation.git.user.email%" || exit 1
                echo "######"
                
                echo "###### Check remotes"
                remotes=${'$'}(git remote -v | grep "https://github.com/%teamcity.cloud.documentation.repo_name%.git" | wc -l)
                if [[ ${'$'}remotes -eq 0 ]] 
                then
                    echo "######### Add upstream remote"
                    git remote add upstream https://github.com/%teamcity.cloud.documentation.repo_name%.git || exit 1
                    echo "######### Current remotes list:"
                    git remote -v || exit 1
                fi
                echo "######"
                
                echo "###### Fetch, checkout a new temp branch from upstream's HEAD branch."
                repo_head=${'$'}(git remote show upstream | awk '/HEAD branch/ {print ${'$'}NF}')
                
                git fetch upstream ${'$'}repo_head || exit 1
                git checkout -b %teamcity.cloud.documentation.branch.name.prefix%%teamcity.build.id% upstream/${'$'}repo_head || exit 1
                echo "######"
                
                header_ubuntu=${'$'}(cat << EOM
                [//]: # (title: Preinstalled Software on TeamCity Cloud Ubuntu Agents)
                [//]: # (auxiliary-id: Preinstalled Software on TeamCity Cloud Ubuntu Agents)
                
                <chunk id="ubuntu-jb-agents">
                EOM
                )
                header_windows=${'$'}(cat << EOM
                [//]: # (title: Preinstalled Software on TeamCity Cloud Windows Agents)
                [//]: # (auxiliary-id: Preinstalled Software on TeamCity Cloud Windows Agents)
                
                <chunk id="windows-jb-agents">
                EOM
                )
                footer=${'$'}(cat << EOM
                </chunk> 
                EOM
                )
                body_ubuntu=${'$'}(cat ../scripts/ubuntu/software.report.md)
                body_windows=${'$'}(cat ../scripts/windows/software.report.md)
                
                echo "###### Write software reports files"
                cat > topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md << EOM
                ${'$'}header_ubuntu
                
                ${'$'}body_ubuntu
                
                ${'$'}footer
                EOM
                cat > topics/preinstalled-software-on-teamcity-cloud-windows-agents.md << EOM
                ${'$'}header_windows
                
                ${'$'}body_windows
                
                ${'$'}footer
                EOM
                echo "######"
                
                echo "###### Git add, commit, push temp branch to origin"
                git add topics/preinstalled-software-on-teamcity-cloud-ubuntu-agents.md || exit 1
                git add topics/preinstalled-software-on-teamcity-cloud-windows-agents.md || exit 1
                git commit -m "${'$'}commit_msg" || exit 1
                git push https://%teamcity.cloud.documentation.login%:%teamcity.cloud.documentation.token%@github.com/%teamcity.cloud.documentation.fork_name% %teamcity.cloud.documentation.branch.name.prefix%%teamcity.build.id% || exit 1
                echo "######"
                
                json="{\"head\":\"%teamcity.cloud.documentation.branch.name.prefix%%teamcity.build.id%\", \"base\":\"${'$'}repo_head\", \"body\":\"${'$'}{pr_body_msg}\", \"title\":\"Update preinstalled software list for TCC agents\"}"
                #echo "${'$'}json"
                echo "###### Create PR in documentation repo form temp branch"
                curl -u %teamcity.cloud.documentation.token% -X POST -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/%teamcity.cloud.documentation.repo_name%/pulls -d "${'$'}json"
                echo "######"
            """.trimIndent()
        }
        script {
            name = "Generate requirements.txt"
            scriptContent = """echo "PyGithub==1.55" > requirements.txt"""
        }
        python {
            name = "Cleanup old PRs and patch branches"
            environment = venv {
            }
            command = script {
                content = """
                    from github import Github, UnknownObjectException
                    
                    
                    def delete_branch(branch_name, rep):
                        try:
                            ref = rep.get_git_ref(f"heads/{branch_name}")
                            ref.delete()
                        except UnknownObjectException:
                            print('No such branch', branch_name)
                    
                    
                    if __name__ == '__main__':
                        pr_list = []
                        branch_list = {}
                        g = Github("%teamcity.cloud.documentation.token%")
                        docs_repo = g.get_repo("%teamcity.cloud.documentation.repo_name%")
                        all_open_prs = docs_repo.get_pulls(state='open')
                        # Filter PRs by login and name of head branches
                        for pr in all_open_prs:
                            if pr.user.login == g.get_user().login and '%teamcity.cloud.documentation.branch.name.prefix%' in pr.head.ref:
                                pr_list.append(pr)
                        # Sort the list with PRs by create date and pop the most recent item
                        pr_list.sort(key=lambda x: x.created_at, reverse=True)
                        if len(pr_list) > 0:
                            pr_list.pop(0)
                        else:
                            print("No PRs found. Exiting.")
                            exit(0)
                        for pr in pr_list:
                            print("Closing the following PR as obsolete:")
                            print(f"\tTitle: '{pr.title}'; URL: '{pr.html_url}'; Created at: '{pr.created_at}'")
                            pr.create_issue_comment("Closing this PR as obsolete, there is a more recent one")
                            pr.edit(state='closed')
                            # Store to-be-deleted branches that were used has head in PRs we just closed
                            branch_list[pr.head.repo] = pr.head.ref
                        for repo, branch in branch_list.items():
                            print(f"Removing branch '{branch}' in repository '{repo.full_name}'")
                            delete_branch(branch, repo)
                """.trimIndent()
            }
        }
    }

    dependencies {
        artifacts(DownloadSoftwareReport) {
            buildRule = lastSuccessful()
            artifactRules = "+:software.report.md"
        }
    }
})
