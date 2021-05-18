plugins {
    javaLibrary()
    mavenPublish()
}

dependencies {
    implementation("com.shenghaiyang.auto.plugin:annotations:${project.version}")
    annotationProcessor("com.shenghaiyang.auto.plugin:processor:${project.version}")
}
