package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.XmlReport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.xmlReport

object IncorrectXmlReportsPath : BuildType({
    templates(BuildCommitStatusPublisher)
    name = "Incorrect XML Reports Path"

    features {
        xmlReport {
            id = "BUILD_EXT_11"
            reportType = XmlReport.XmlReportType.JUNIT
            rules = "+:/**/*.xml"
            verbose = true
        }
    }
})
