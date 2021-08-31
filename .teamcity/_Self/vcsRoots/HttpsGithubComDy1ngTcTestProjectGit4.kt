package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComDy1ngTcTestProjectGit4 : GitVcsRoot({
    name = "https://github.com/dy1ng/TC-Test-Project.git with parameter in default branch"
    url = "https://github.com/dy1ng/TC-Test-Project.git"
    branch = "refs/heads/%parameter1%"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "dy1ng"
        password = "credentialsJSON:19b447d6-628f-49e4-aad9-31bb547635b9"
    }
})
