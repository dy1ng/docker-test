package _Self

import _Self.buildTypes.*
import _Self.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.dockerRegistry
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.gitlabConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.nuGetFeed
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.s3Storage

object Project : Project({

    vcsRoot(HttpsGithubComDy1ngTcTestProjectGit3)
    vcsRoot(HttpsGithubComDy1ngTcTestProjectGit)
    vcsRoot(HttpsGithubComDy1ngTeamcityDocumentation)
    vcsRoot(HttpsGithubComDy1ngTcTestProjectGit4)
    vcsRoot(HttpsGithubComDy1ngTcTestProjectGit5)
    vcsRoot(BitbucketDy1ng)
    vcsRoot(GheTeamcityAdminMefremovTestRepo)
    vcsRoot(HttpsGithubComDy1ngTcTestProjectGit6)
    vcsRoot(HttpsGithubComJetBrainsTeamcityDocumentation)
    vcsRoot(HttpsGithubComDy1ngTcTestProjectMasterWPullRequests)

    buildType(Build)
    buildType(B3Deploy)
    buildType(DefaultBranchDependencyTest)
    buildType(BuildA)
    buildType(IncorrectXmlReportsPath)
    buildType(BuildB)
    buildType(SonarRunnerTest)
    buildType(BuildD)
    buildType(DefaultBranchTest)
    buildType(DownloadSoftwareReport)
    buildType(BashSourceTest)
    buildType(AB)
    buildType(A2Develop)
    buildType(B1FinishBuildTriggers)
    buildType(BuildStatusCheck)
    buildType(B4Composite)
    buildType(CloudBuild)
    buildType(CompositeConfig)
    buildType(A1Master)
    buildType(MultipleVcsRoots)
    buildType(B2vcsBuildTrigger)
    buildType(SshExecTest)
    buildType(UpdateEnvironmentVariables)
    buildType(DockerTest)
    buildType(Deployment)
    buildType(GheTest)
    buildType(SimpleBuild)
    buildType(UpdateSoftwareInfoViaPr)
    buildType(DependencyCheck1)
    buildType(DependencyCheck2)
    buildType(Automation)
    buildType(ParamBasedExecution)

    template(BuildCommitStatusPublisher)
    template(SimpleBuildTemplate)

    params {
        password("bitbucket.AppPassword", "credentialsJSON:685b014c-35a2-48cb-b305-296e3f4ee59f", description = "Bitbucket.org App Password", display = ParameterDisplay.HIDDEN, readOnly = true)
        param("bitbucket.Username", "")
    }

    features {
        feature {
            id = "PROJECT_EXT_12"
            type = "CloudImage"
            param("${'$'}datacenterId", "datacenter-21")
            param("customizationSpec", "labs.intellij.net")
            param("folder", "group-v1525885")
            param("profileId", "vmw-1")
            param("agent_pool_id", "1")
            param("nickname", "agent-freebsd")
            param("pool", "resgroup-1288981")
            param("behaviour", "FRESH_CLONE")
            param("maxInstances", "3")
            param("sourceVmName", "agent-freebsd-master-261")
            param("snapshot", "__CURRENT_STATE__")
            param("source-id", "agent-freebsd")
        }
        feature {
            id = "PROJECT_EXT_13"
            type = "CloudImage"
            param("customPodTemplate", """
                apiVersion: v1
                kind: Pod
                metadata:
                  name: jetbrains-teamcity-agent-1
                  namespace: default
                spec:
                  containers:
                  - env:
                    - name: TC_K8S_SERVER_UUID
                      value: d360740e-79f4-494d-bc35-b7c01eda5148
                    - name: TC_K8S_SERVER_URL
                      value: http://unit-1592.labs.intellij.net:8111
                    - name: SERVER_URL
                      value: http://unit-1592.labs.intellij.net:8111
                    - name: TEAMCITY_KUBERNETES_IMAGE_NAME
                      value: "teamcity-test"
                    - name: TEAMCITY_KUBERNETES_CLOUD_PROFILE_ID
                      value: kube-1
                    - name: TEAMCITY_KUBERNETES_INSTANCE_NAME
                      value: teamcity-test-2
                    - name: DOCKER_IN_DOCKER
                      value: start
                    image: jetbrains/teamcity-agent:2019.2.2-linux
                    securityContext:
                      privileged: true
                    imagePullPolicy: IfNotPresent
                    name: jetbrains-teamcity-agent-1
                    resources: {}
                    terminationMessagePath: /dev/termination-log
                    terminationMessagePolicy: File
            """.trimIndent())
            param("podTemplateMode", "custom-pod-template")
            param("imageInstanceLimit", "3")
            param("agentNamePrefix", "Kube-agent")
            param("profileId", "kube-1")
            param("imageDescription", "Custom pod template")
            param("agent_pool_id", "1")
            param("sourceDeployment", "jetbrains-teamcity-agent-1")
            param("source-id", "Kube-agent")
        }
        gitlabConnection {
            id = "PROJECT_EXT_2"
            displayName = "GitLab.com"
            applicationId = "ed5d667f0acfec841512ad0d0299bcf1737351171ee11a70e72984817962f7ec"
            clientSecret = "credentialsJSON:2d084fb6-3420-400d-afa8-30190daaf037"
        }
        githubConnection {
            id = "PROJECT_EXT_3"
            displayName = "GitHub.com"
            clientId = "6409eb847b021a59a18b"
            clientSecret = "credentialsJSON:68a738f4-21af-48bb-bedd-cedc938f694c"
        }
        dockerRegistry {
            id = "PROJECT_EXT_4"
            name = "Docker Registry"
            url = "https://docker.io"
            userName = "dy1ng"
            password = "credentialsJSON:b9428692-a455-4e1c-a8a1-cc7f67b6f6d5"
        }
        s3Storage {
            id = "PROJECT_EXT_5"
            storageName = "test.project_s3"
            bucketName = "mefremov-test"
            accessKey = "credentialsJSON:27183c04-9f97-472d-85de-c9d15232abab"
            awsEnvironment = custom {
                endpoint = "https://s3.us-east-2.amazonaws.com"
                awsRegionName = "us-east-2"
            }
            accessKeyID = "AKIA5JH2VERVDU5DXUML"
        }
        feature {
            id = "PROJECT_EXT_7"
            type = "sonar-qube"
            param("password", "zxxb4f24b12694403eb")
            param("name", "test")
            param("id", "228f49a3-39ae-4cc2-a15f-62c2475894f4")
            param("login", "vasya")
            param("url", "localhost:12345")
        }
        feature {
            id = "amazon-1"
            type = "CloudProfile"
            param("profileServerUrl", "")
            param("secure:access-id", "credentialsJSON:53f339d3-2c9a-4af6-b5f8-332d009bfb41")
            param("total-work-time", "")
            param("description", "")
            param("cloud-code", "amazon")
            param("enabled", "true")
            param("max-running-instances", "10")
            param("agentPushPreset", "")
            param("profileId", "amazon-1")
            param("name", "AWS EC2")
            param("next-hour", "")
            param("secure:secret-key", "credentialsJSON:81debfa5-9502-4e09-ac7a-eb668a33f40a")
            param("region", "us-east-1")
            param("terminate-idle-time", "30")
            param("not-checked", "")
        }
        feature {
            id = "kube-1"
            type = "CloudProfile"
            param("secure:authToken", "credentialsJSON:2717bd3e-e231-42d1-87e9-bd1eff7bc3ff")
            param("oidcClientId", "")
            param("secure:eksSecretKey", "")
            param("system.cloud.profile_id", "kube-1")
            param("description", "")
            param("enabled", "true")
            param("agentPushPreset", "")
            param("profileInstanceLimit", "")
            param("idpIssuerUrl", "")
            param("apiServerUrl", "https://kubernetes.docker.internal:6443")
            param("eksAccessId", "")
            param("secure:oidcClientSecret", "")
            param("profileServerUrl", "")
            param("authStrategy", "token")
            param("total-work-time", "")
            param("cloud-code", "kube")
            param("secure:oidcRefreshToken", "")
            param("eksClusterName", "")
            param("profileId", "kube-1")
            param("secure:clientCertData", "")
            param("secure:password", "")
            param("name", "Kubernetes - Test project")
            param("namespace", "")
            param("next-hour", "")
            param("secure:caCertData", "")
            param("terminate-idle-time", "30")
            param("username", "")
        }
        nuGetFeed {
            id = "repository-nuget-test_feed"
            name = "test_feed"
            description = ""
        }
        feature {
            id = "vmw-1"
            type = "CloudProfile"
            param("image", "agent-freebsd-master-261")
            param("profileServerUrl", "")
            param("system.cloud.profile_id", "vmw-1")
            param("total-work-time", "")
            param("vmware_username", """LABS\Mikhail.Efremov""")
            param("description", "")
            param("cloud-code", "vmw")
            param("enabled", "true")
            param("helperFieldId", "folder")
            param("secure:vmware_password", "credentialsJSON:cb7088b7-81bb-4077-af82-2544da8535be")
            param("vmware_profile_instance_limit", "10")
            param("vmware_server_url", "https://vcenter-srv.labs.intellij.net/sdk")
            param("agentPushPreset", "")
            param("helperFieldValue", "group-v1525885")
            param("profileId", "vmw-1")
            param("name", "vSphere - Test Project")
            param("next-hour", "")
            param("force_trust_manager", "true")
            param("terminate-idle-time", "30")
        }
    }

    cleanup {
        baseRule {
            all(days = 100)
            artifacts(days = 50, artifactPatterns = """
                -:*
                +:*.tar
            """.trimIndent())
        }
    }

    subProject(ArtifactDependencyTest.Project)
})
