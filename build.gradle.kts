/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * Top-level build file where you can add configuration options common to all sub-projects/modules.
 **/

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.1")


    }
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

plugins{
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    kotlin("android") version "1.7.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.0" apply false
    id("maven-publish")
}

tasks.create<Delete>("cleanRp"){
    delete(
        rootProject.buildDir
    )
}
