dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "AutoPlugin"

include(
    ":annotations",
    ":processor",
    ":sample",
)
