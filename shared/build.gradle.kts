import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcFrameworkName = "XCPowerplayKMP"
    val xcFramework = XCFramework(xcFrameworkName)

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcFrameworkName
            //binaryOption("bundleID", "org.example.${xcFrameworkName}")
            xcFramework.add(this)
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.powerplaykmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

publishing {
    repositories {
        group = "com.company.powerplay"
        version = System.getenv("VERSION_NAME")
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Deepakgoyal-iOS/powerplay_kmp")
            credentials {
                username = "Deepakgoyal-iOS"
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
