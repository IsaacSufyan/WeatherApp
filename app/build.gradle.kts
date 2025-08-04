plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp.android)
    alias(libs.plugins.compose.compiler)
}

android {

    signingConfigs {
        create("release") {
            storeFile = file("..\\keystore\\weatherapp.jks")
            storePassword = "weatherapp"
            keyAlias = "weatherapp"
            keyPassword = "weatherapp"
        }
    }

    namespace = "com.isaacsufyan.weatherapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.isaacsufyan.weatherapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
    }


    buildTypes {
        release {
            isShrinkResources  = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompExt.get()
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.process)
    implementation(libs.lifecycle.runtime.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.logging.interceptor)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.navigation.compose)
    implementation(libs.location)
    implementation(libs.android.material)
    implementation(project(":CoreUI"))
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.coil)

}