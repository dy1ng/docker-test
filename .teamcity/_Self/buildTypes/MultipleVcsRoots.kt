package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python

object MultipleVcsRoots : BuildType({
    name = "Multiple VCS roots"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComDy1ngTcTestProjectMasterWPullRequests)
        root(_Self.vcsRoots.BitbucketDy1ng, "+:. => bitbucket")
    }

    steps {
        python {
            command = file {
                filename = "project/__init__.py"
            }
        }
    }
})
