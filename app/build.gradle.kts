/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
plugins {
    id("com.android.application")
//    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")

}

android {
    compileSdk=33

    defaultConfig {
        applicationId ="com.cexup.ui"
        minSdk =23
        targetSdk =33
        versionCode= 1
        versionName ="1.0"
        multiDexEnabled = true
        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = false
    }

    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled =true


        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose= true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_compiler_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":lib"))

    //Loads packaged libraries in the libs folder
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(Libs.AndroidX.Multidex.multidex)
    implementation(Libs.AndroidX.Core.coreKtx)

    //jetpack compose
    implementation(Libs.AndroidX.Compose.Ui.ui)
    implementation(Libs.AndroidX.Compose.Material.material)
    implementation(Libs.AndroidX.Compose.Material.iconsExtended)
    implementation(Libs.AndroidX.Compose.Material.material)
    implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libs.AndroidX.Compose.Runtime.runtime)
    implementation(Libs.AndroidX.Navigation.navigationCompose)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.Io.CoilKt.coilCompose)

//    implementation("androidx.paging:paging-compose:1.0.0-alpha14")

    with(Libs.Com.Google.Accompanist){
        implementation(accompanistSystemUiController)
        implementation(accompanistPager)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistNavigationMaterial)
        implementation(accompanistInset)
        implementation(accompanistSwipeRefresh)
        implementation(accompanistShimmer)
        implementation(accompanistPagerIndicator)
    }

    debugImplementation(Libs.AndroidX.Compose.Ui.uiTooling)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTest)
    androidTestImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit4)

    //toast
    implementation(Libs.Com.Github.GrenderG.toasty)


    //dependency injection
    //hilt dagger (support viewModel)
    //https://developer.android.com/training/dependency-injection/hilt-android#setup
    //https://developer.android.com/training/dependency-injection/hilt-testing?hl=id
    with(Libs.Dagger){
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
        androidTestImplementation(hiltAndroidTesting)
    }

    with(Libs.AndroidX.Hilt) {
        //supaya bisa inject viewModel ke navigation
        //https://developer.android.com/jetpack/compose/libraries#hilt
        implementation(hiltNavigationCompose)
        implementation(hiltLifecycleViewmodel)
        kapt(hiltCompiler)
    }

    //lifecycle
    with(Libs.AndroidX.Lifecycle){
        implementation(lifecycleViewmodelCompose)
        implementation(lifecycleRuntimeKtx)
        implementation(lifecycleViewmodel)
        implementation(lifecycleViewmodelKtx)
        implementation(lifecycleRuntime)
        implementation(lifecycleViewmodelSavedstate)
        kapt(lifecycleCompiler)
    }

    //testing
    testImplementation(Libs.Junit.junit)


    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}