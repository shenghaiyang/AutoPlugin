
object Libs {

    object AutoService {
        private const val version = "1.0"
        const val annotation = "com.google.auto.service:auto-service-annotations:$version"
        const val processor = "com.google.auto.service:auto-service:$version"
    }

    object MavenPublish {
        private const val version = "0.15.1"
        const val gradlePlugin = "com.vanniktech:gradle-maven-publish-plugin:$version"
    }
}

private const val mavenPublish = "com.vanniktech.maven.publish"

fun org.gradle.plugin.use.PluginDependenciesSpec.javaLibrary(): org.gradle.plugin.use.PluginDependencySpec =
    id("java-library")

fun org.gradle.plugin.use.PluginDependenciesSpec.mavenPublish(): org.gradle.plugin.use.PluginDependencySpec =
    id(mavenPublish)


object Repos {

    const val aliyunJcenter = "https://maven.aliyun.com/repository/public"
    const val aliyunGoogle = "https://maven.aliyun.com/repository/google"
    const val mozilla = "https://maven.mozilla.org/maven2/"
    const val mavenSnapshot = "https://oss.sonatype.org/content/repositories/snapshots/"
}