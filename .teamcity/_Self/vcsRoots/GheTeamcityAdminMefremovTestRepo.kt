package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object GheTeamcityAdminMefremovTestRepo : GitVcsRoot({
    name = "GHE_teamcity-admin_mefremov-test-repo"
    url = "git@ghe.labs.intellij.net:teamcity-admin/mefremov-test-repo.git"
    branch = "refs/heads/master"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = uploadedKey {
        uploadedKey = "ghe_teamcity-admin_mefremov"
    }
})
