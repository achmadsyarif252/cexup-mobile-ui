plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("maven-publish")
}

val jvmRepositoryEndpoint = findProperty("ENDPOINT_JVM_REPOSITORY")
val jvmRepositoryUsername = findProperty("JVM_REPOSITORY_USERNAME")
val jvmRepositoryToken = findProperty("JVM_REPOSITORY_TOKEN")

println("$jvmRepositoryEndpoint")
println("$jvmRepositoryUsername")
println("$jvmRepositoryToken")

android{
    compileSdk = 33
    defaultConfig {
        aarMetadata {
            minCompileSdk = 23
        }
        minSdk = 23
        targetSdk = 33

        buildConfigField("String", "ENDPOINT_JVM_REPOSITORY", "\"$jvmRepositoryEndpoint\"")
        buildConfigField("String", "JVM_REPOSITORY_USERNAME", "\"$jvmRepositoryUsername\"")
        buildConfigField("String", "JVM_REPOSITORY_TOKEN", "\"$jvmRepositoryToken\"")
    }
    buildFeatures {
        compose= true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_compiler_version
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled =true
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    testOptions{
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.cexup"
                artifactId = "ui"
                version = "1.0.0-alpha13"

                from(components["release"])

            }
        }
        repositories {
            maven {
                url = uri("$jvmRepositoryEndpoint")
                isAllowInsecureProtocol = true
                credentials {
                    username = "$jvmRepositoryUsername"
                    password = "$jvmRepositoryToken"
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0")

    implementation(Libs.Com.Google.Material.material)
    with(Libs.AndroidX.Compose){
        implementation(Libs.AndroidX.Compose.Ui.ui)
        //https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
        implementation(Libs.AndroidX.Compose.Ui.uiTooling)
        implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
        implementation(Libs.AndroidX.Compose.Material.material)
        implementation(Libs.AndroidX.Compose.Material.icons)
        implementation(Libs.AndroidX.Compose.Material.iconsExtended)
        implementation(Libs.AndroidX.Compose.Paging.paging)



    }
    with(Libs.Com.Google.Accompanist){
        implementation(accompanistSystemUiController)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistNavigationMaterial)
        implementation(accompanistInset)
        implementation(accompanistSwipeRefresh)
        implementation(accompanistShimmer)
        implementation(accompanistPagerIndicator)

    }

    implementation(Libs.Com.Github.PhilJay.mpAndroidChart)
    implementation(Libs.Br.Com.Devsrsouza.Compose.Icons.Android.octicons)
    implementation(Libs.AndroidX.Appcompat.appcompat)
    implementation(Libs.Io.CoilKt.coilCompose)
    implementation(Libs.Com.Github.Skydoves.landscapist)
    implementation(Libs.JodaTime.jodaTime)



    //testing
    testImplementation(Libs.Junit.junit)
    testImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit4)
    testImplementation(Libs.Org.Robolectric.robolectric)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")

}
