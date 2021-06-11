package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComDy1ngTcTestProjectGit4 : GitVcsRoot({
    name = "https://github.com/dy1ng/TC-Test-Project.git with parameter in default branch"
    url = "https://github.com/dy1ng/TC-Test-Project.git"
    branch = "refs/heads/%parameter1%"
    authMethod = password {
        userName = "dy1ng"
        password = "credentialsJSON:a500278e-89e1-4869-930e-2e5e8d7193bd"
    }
    param("useAlternates", "true")
})
