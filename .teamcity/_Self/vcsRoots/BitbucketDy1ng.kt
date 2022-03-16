package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object BitbucketDy1ng : GitVcsRoot({
    name = "Bitbucket dy1ng"
    url = "https://dy1ng@bitbucket.org/dy1ng/dummy.repo.for.tc.git"
    branch = "refs/heads/master"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "%bitbucket.Username%"
        password = ""
    }
})
