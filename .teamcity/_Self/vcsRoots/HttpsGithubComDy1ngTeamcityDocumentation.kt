package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComDy1ngTeamcityDocumentation : GitVcsRoot({
    name = "https://github.com/dy1ng/teamcity-documentation"
    url = "https://github.com/dy1ng/teamcity-documentation"
    branch = "refs/heads/mefremov-dev"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "dy1ng"
        password = "credentialsJSON:9746a541-2862-4203-a1e7-fbed268ff191"
    }
    param("oauthProviderId", "PROJECT_EXT_3")
})
