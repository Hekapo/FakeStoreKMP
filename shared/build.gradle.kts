import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose
            with(compose) {
                api(runtime)
                api(foundation)
                api(material3)
                api(materialIconsExtended)
                api(components.uiToolingPreview)

            }

            // Ktor
            api(libs.ktor.client.core)
            api(libs.ktor.serialization)
            api(libs.ktor.content.negotiation)
            api(libs.ktor.client.logging)

            // Coroutines
            api(libs.kotlinx.coroutines.core)

            // Decompose
            api(libs.decompose)
            api(libs.decompose.extensions.compose)

            // Koin
            api(libs.koin.core)

            // MVIKotlin
            api(libs.mvi.kotlin)
            api(libs.mvi.kotlin.main)
            api(libs.mvi.kotlin.coroutines.ext)

            // Coil
            api(libs.coil)
            api(libs.coil.compose)
        }

        androidMain.dependencies {
            // Compose
            implementation(compose.preview)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.uiTooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Ktor
            implementation(libs.ktor.client.android)

            //Koin
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.fakestore.fakestorekmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
