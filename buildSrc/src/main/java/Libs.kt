import org.gradle.kotlin.dsl.provideDelegate

object Libs {
    object Com{
        object Google{

            //accompanist(external library for jetpack compose)
            object Accompanist {
                const val accompanistVersion = "0.25.1"
                val accompanistSystemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion" }
                val accompanistPager by lazy { "com.google.accompanist:accompanist-pager:$accompanistVersion" }
                val accompanistNavigationAnimation by lazy { "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion" }
                val accompanistNavigationMaterial by lazy { "com.google.accompanist:accompanist-navigation-material:$accompanistVersion" }
                val accompanistInset by lazy { "com.google.accompanist:accompanist-insets:$accompanistVersion" }
                val accompanistSwipeRefresh by lazy { "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion" }
                val accompanistShimmer by lazy { "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion" }
                val accompanistPagerIndicator by lazy { "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion" }
            }
            object Material {
                val material by lazy { "com.google.android.material:material:1.4.0" }
            }
        }
        object Github {
            object GrenderG {
                val toasty by lazy { "com.github.GrenderG:Toasty:1.5.2" }
            }
            object PhilJay {
                val mpAndroidChart by lazy { "com.github.PhilJay:MPAndroidChart:v3.1.0" }
            }
            object Skydoves {
                val landscapist by lazy { "com.github.skydoves:landscapist-coil:1.4.1" }
            }
        }


    }
    object Org{
        object Robolectric{
            val robolectric by lazy { "org.robolectric:robolectric:4.7" }
        }
    }

    object Io {
        object CoilKt {
            val coilCompose by lazy { "io.coil-kt:coil-compose:1.4.0" }
        }
    }
    object Dagger{
        private const val dagger_hilt_version = "2.38.1"
        val hiltAndroid by lazy{"com.google.dagger:hilt-android:$dagger_hilt_version"}
        val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:$dagger_hilt_version" }
        val hiltAndroidTesting by lazy { "com.google.dagger:hilt-android-testing:$dagger_hilt_version" }

    }
    object AndroidX{
        object Appcompat {
            private const val appcompat_version = "1.4.1"

            val appcompat by lazy { "androidx.appcompat:appcompat:$appcompat_version" }

        }
        object Activity {
            val activityCompose by lazy { "androidx.activity:activity-compose:1.4.0" }
        }
        object Core{
            val coreKtx by lazy { "androidx.core:core-ktx:1.7.0" }
        }
        object Multidex {
            val multidex by lazy { "androidx.multidex:multidex:2.0.1" }
        }
        object Compose{
            object Ui{
                val ui by lazy { "androidx.compose.ui:ui:${Version.compose_version}" }
                val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Version.compose_version}" }
                val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Version.compose_version}" }
                val uiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4:${Version.compose_version}" }
                val uiTestManifest by lazy {"androidx.compose.ui:ui-test-manifest:${Version.compose_version}"}
                val uiTest by lazy { "androidx.compose.ui:ui-test:${Version.compose_version}" }
            }
            object Runtime{
                val runtime by lazy { "androidx.compose.runtime:runtime:${Version.compose_version}" }


            }
            object Material{
                val icons by lazy { "androidx.compose.material:material-icons-core:${Version.compose_version}" }
                val iconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Version.compose_version}" }
                val material by lazy {"androidx.compose.material:material:${Version.compose_version}"}
            }
        }
        object Navigation{
            val navigationCompose by lazy { "androidx.navigation:navigation-compose:2.5.1" }
        }
        object Hilt{
            val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:1.0.0" }

            //https://stackoverflow.com/questions/67020214/hiltworker-annotation-can-not-be-found
            val hiltWork by lazy { "androidx.hilt:hilt-work:1.0.0" }
            val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:1.0.0" }
            val hiltLifecycleViewmodel by lazy { "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03" }
        }



        object Lifecycle {
            private const val lifecycle_version = "2.5.0-alpha01"
            val lifecycleViewmodelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07" }
            val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0" }
            val lifecycleViewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version" }
            val lifecycleViewmodelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version" }
            val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime:$lifecycle_version" }
            val lifecycleViewmodelSavedstate by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version" }
            val lifecycleCompiler by lazy { "androidx.lifecycle:lifecycle-compiler:$lifecycle_version" }
        }

    }
    object Br {
        object Com {
            object Devsrsouza {
                object Compose {
                    object Icons {
                        object Android {
                            val octicons by lazy { "br.com.devsrsouza.compose.icons.android:octicons:1.0.0" }
                            val feather by lazy { "br.com.devsrsouza.compose.icons.android:feather:1.0.0" }
                        }
                    }
                }
            }
        }
    }
    object Junit {
        val junit by lazy { "junit:junit:4.13.2" }
    }


}