plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.10"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.batman.charoom"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.batman.charoom"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    implementation(libs.firebase.auth.ktx)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    //room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    //navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    //Coil
    implementation("io.coil-kt:coil-compose:2.7.0")
    //material icons
    implementation ("androidx.compose.material:material-icons-extended:1.7.2")
    implementation ("com.github.commandiron:ComposeLoading:1.0.4")



}