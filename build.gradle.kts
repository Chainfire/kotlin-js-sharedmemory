import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.js") version "1.4.0-rc"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.5"
}

group = "eu.chainfire"
version = "1.0.0"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin {
    js {
        browser()
    }
}

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val gitHubRepo = "Chainfire/kotlin-js-sharedmemory"
val gitHubReadme = "README.md"
val gitHubUrl = "https://github.com/$gitHubRepo"
val gitUrl = "$gitHubUrl.git"

val pomUrl = gitHubUrl
val pomScmUrl = gitUrl
val pomIssueUrl = "$gitHubUrl/issues"
val pomDesc = "External declarations related to shared memory"

val pomLicenseName = "Apache-2.0"
val pomLicenseUrl = "https://www.apache.org/licenses/LICENSE-2.0.txt"
val pomLicenseDist = "repo"

val pomDeveloperId = "Chainfire"
val pomDeveloperName = "Jorrit Jongma"

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")

    // from(sourceSets.getByName("main").allSource) -- sourceSets is empty here?
    from(project.projectDir.absolutePath + File.separator + "src" + File.separator + "main" + File.separator + "kotlin")
}

val docsJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>(artifactName) {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["kotlin"])

            artifact(sourcesJar)
            artifact(docsJar)

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

bintray {
    val properties = Properties()
    if (project.rootProject.file("local.properties").exists()) {
        properties.load(project.rootProject.file("local.properties").inputStream())
    }
    user = properties.getProperty("bintray.user")
    key = properties.getProperty("bintray.apikey")

    setPublications(artifactName)

    dryRun = false
    publish = true
    pkg.apply {
        repo = "maven"
        name = artifactName
        desc = pomDesc
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        vcsUrl = gitUrl
        setLicenses(pomLicenseName)
        publicDownloadNumbers = true
    }
}
