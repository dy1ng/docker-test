package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HttpsGithubComDy1ngTcTestProjectGit : GitVcsRoot({
    name = "https://github.com/dy1ng/TC-Test-Project.git"
    url = "https://github.com/dy1ng/TC-Test-Project.git"
    branch = "refs/heads/master"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "dy1ng"
        password = "credentialsJSON:a500278e-89e1-4869-930e-2e5e8d7193bd"
    }
    param("useAlternates", "true")
})
