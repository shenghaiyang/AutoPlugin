buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Libs.MavenPublish.gradlePlugin)
    }
}

subprojects {
    tasks.withType(JavaCompile::class) {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
    plugins.withType(com.vanniktech.maven.publish.MavenPublishPlugin::class.java) {
        val extension = extensions.getByType(com.vanniktech.maven.publish.MavenPublishPluginExtension::class)
        extension.sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}