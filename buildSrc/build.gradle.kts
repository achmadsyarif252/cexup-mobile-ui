/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.google.com")
}