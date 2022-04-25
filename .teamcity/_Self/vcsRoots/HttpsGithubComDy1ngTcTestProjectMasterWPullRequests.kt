package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComDy1ngTcTestProjectMasterWPullRequests : GitVcsRoot({
    name = "https://github.com/dy1ng/TC-Test-Project master w/ pull requests"
    url = "https://github.com/dy1ng/TC-Test-Project"
    branch = "refs/heads/master"
    branchSpec = "+:refs/pull/*/merge"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "dy1ng"
        password = "credentialsJSON:9746a541-2862-4203-a1e7-fbed268ff191"
    }
})
