

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android{

    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 30
    }
    buildFeatures {
        compose= true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_compiler_version
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
dependencies {


    implementation(Libs.Com.Google.Material.material)
    with(Libs.AndroidX.Compose){
        implementation(Libs.AndroidX.Compose.Ui.ui)
        //https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
        implementation(Libs.AndroidX.Compose.Ui.uiTooling)
        implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
        implementation(Libs.AndroidX.Compose.Material.material)
        implementation(Libs.AndroidX.Compose.Material.icons)
        implementation(Libs.AndroidX.Compose.Material.iconsExtended)



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
    implementation(Libs.Com.Github.Skydoves.landscapist)
    implementation(Libs.AndroidX.Appcompat.appcompat)



    //testing
    testImplementation(Libs.Junit.junit)
    testImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit4)
    testImplementation(Libs.Org.Robolectric.robolectric)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-rc01")

}
