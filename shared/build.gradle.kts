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
        version = project.findProperty("MAVEN_VERSION")?.toString() ?: "1.0.0"
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
////publishing{
//    publications {
//        create<MavenPublication>("xcframework") {
//            groupId = "com.company.powerplay"
//            artifactId = "xcpowerplaykmp"
//            version = "1.0.1"
//
//            artifact(
//                file("build/xcframeworkZip/XCPowerplayKMP.xcframework.zip")
//            ){
//                extension = "zip"
//            }
//        }
//    }
//
//    repositories {
//        maven {
//            name = "GitHubPackages"
//            url = uri("https://maven.pkg.github.com/Deepakgoyal-iOS/powerplay_kmp")
//            credentials {
//                username = providers.gradleProperty("gpr.user").get()
//                password = providers.gradleProperty("gpr.key").get()
//            }
//        }
//    }
//}

//tasks.register<Zip>("zipXcFramework") {
//
//    // 1️⃣ Make sure XCFramework is built first
//    dependsOn("assembleXCPowerplayKMPXCFramework")
//
//    // 2️⃣ What to zip
//    from(
//        layout.buildDirectory.dir(
//            "XCFrameworks/release/XCPowerplayKMP.xcframework"
//        )
//    )
//
//    // 3️⃣ Zip file name
//    archiveFileName.set("XCPowerplayKMP.xcframework.zip")
//
//    // 4️⃣ Where zip will be created
//    destinationDirectory.set(
//        layout.buildDirectory.dir("xcframeworkZip")
//    )
//}

//
//tasks.register("exportToSwift") {
//    val SDK_VERSION = "1.2.3"
//    val API_BASE_URL = "https://api.example.com"
//    doLast {
//        println("export SDK_VERSION=${SDK_VERSION}")
//        println("export API_BASE_URL=${API_BASE_URL}")
//    }
//}