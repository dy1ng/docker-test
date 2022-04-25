package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComJetBrainsTeamcityDocumentation : GitVcsRoot({
    name = "https://github.com/JetBrains/teamcity-documentation"
    url = "https://github.com/JetBrains/teamcity-documentation"
    branch = "mefremov-dev"
})
