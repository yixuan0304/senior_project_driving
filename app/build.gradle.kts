plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.driving_constraintlayout"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.driving_constraintlayout"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

//dependencies {
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    implementation(libs.androidx.navigation.compose)
//    implementation(libs.androidx.runner)
//    implementation(libs.androidx.espresso.core)
//    //constraintlayout
//    implementation(libs.androidx.constraintlayout.compose.android)
//    implementation(libs.play.services.maps)
//
//    //lifecycle
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    implementation(libs.androidx.lifecycle.runtime.compose)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
//
//
//    //camerax
//    implementation(libs.androidx.camera.view)
//    implementation(libs.androidx.camera.extensions)
//    implementation(libs.androidx.camera.core)
//    implementation(libs.androidx.camera.compose)
//    implementation(libs.androidx.camera.lifecycle)
//    implementation(libs.androidx.camera.camera2)
//    implementation(libs.accompanist.permissions)
//
//    // Google maps
//    implementation ("com.google.android.gms:play-services-maps:19.1.0")
//    implementation ("com.google.android.gms:play-services-location:21.3.0")
//    // Google maps for compose
//    implementation ("com.google.maps.android:maps-compose:4.4.1")
//
//    // Import the Firebase BoM
//    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
//    // When using the BoM, don't specify versions in Firebase dependencies
//    implementation(libs.firebvase.auth.ktx)
//    implementation("com.google.android.gms:play-services-auth")
//    //implementation(libs.firebase.auth)
//
//    val credentialsManagerVersion = ":1.5.0-alpha05"
//    implementation("androidx.credentials:credentials:$credentialsManagerVersion")
//    implementation("androidx.credentials:credentials-play-services-auth:$credentialsManagerVersion")
//    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
//
//    implementation("io.coil-kt:coil-compose:2.2.2")
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.espresso.core)

    // ConstraintLayout
    implementation(libs.androidx.constraintlayout.compose.android)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // CameraX
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.compose)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.accompanist.permissions)

    // Google Maps - 使用一種引入方式即可
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location) // 你應該在 libs.versions.toml 中定義這個
    implementation(libs.maps.compose) // 你應該在 libs.versions.toml 中定義這個

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation(libs.firebase.auth.ktx)
    //implementation(libs.play.services.auth) // 你應該在 libs.versions.toml 中定義這個
    implementation ("com.google.android.gms:play-services-auth:21.1.1")
    // Credentials Manager
    val credentialsManagerVersion = "1.5.0-alpha05"
    implementation("androidx.credentials:credentials:$credentialsManagerVersion")
    implementation("androidx.credentials:credentials-play-services-auth:$credentialsManagerVersion")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}