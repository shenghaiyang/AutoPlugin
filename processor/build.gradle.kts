plugins {
    javaLibrary()
    mavenPublish()
}

dependencies {
    implementation("com.shenghaiyang.auto.plugin:annotations:${project.version}")

    implementation(Libs.AutoService.annotation)
    annotationProcessor(Libs.AutoService.processor)
}