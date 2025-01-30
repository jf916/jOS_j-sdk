import org.lineageos.generatebp.GenerateBpPlugin
import org.lineageos.generatebp.GenerateBpPluginExtension
import org.lineageos.generatebp.models.Module

val Ver: String = rootProject.extra["libVersion"] as String;
val libMinSdk: Int = rootProject.extra["libMinSdk"] as Int;
val libCompileSdk: Int = rootProject.extra["libCompileSdk"] as Int;

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    `maven-publish`
    id("com.vanniktech.maven.publish")
}

apply {
    plugin<GenerateBpPlugin>()
}

buildscript {
    repositories {
        maven("https://raw.githubusercontent.com/lineage-next/gradle-generatebp/v1.21/.m2")
    }

    dependencies {
        //noinspection GradleDynamicVersion
        classpath("org.lineageos:gradle-generatebp:+")
    }
}

android.buildFeatures.buildConfig=true

group = "io.github.dot166"
version = Ver

android {
    namespace = "io.github.dot166.jLib"
    compileSdk = libCompileSdk

    defaultConfig {
        minSdk = libMinSdk

        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "LIBVersion", "\"$version\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures { compose = true }
}

dependencies {
    api("androidx.appcompat:appcompat:1.7.0")
    api("androidx.constraintlayout:constraintlayout:2.2.0")
    api("androidx.recyclerview:recyclerview:1.4.0")
    //noinspection KtxExtensionAvailable
    api("androidx.preference:preference:1.2.1")
    api("com.google.android.material:material:1.12.0")
    api("androidx.core:core-ktx:1.15.0")
    api("androidx.browser:browser:1.8.0")
    api("androidx.activity:activity-compose:1.10.0")
    api("androidx.compose.ui:ui-android:1.7.7")
    api("androidx.compose.material3:material3-android:1.3.1")
    api("androidx.compose.material:material-android:1.7.6")
    api("io.coil-kt:coil-compose:2.7.0")
    api("com.google.accompanist:accompanist-drawablepainter:0.37.0")
    api("com.mikepenz:aboutlibraries-core:11.5.0")
    api("com.mikepenz:aboutlibraries-compose-m3:11.5.0")
}

mavenPublishing {
    coordinates(group.toString(), rootProject.name, version.toString())

    pom {
        name = "j Common Library"
        description = "jLib - a common library that contains a custom actionbar based on material components Toolbar and some other things."
        inceptionYear = "2024"
        url = "https://github.com/dot166/jOS_j-lib"
        licenses {
            license {
                name.set("MIT License")
                url.set("https://choosealicense.com/licenses/mit/")
            }
        }
        developers {
            developer {
                id = "dot166"
                name = "._______166"
                url = "https://dot166.github.io"
            }
        }
        scm {
            url = "https://github.com/github.com/dot166/jOS_j-lib"
            connection = "scm:git:git://github.com/github.com/dot166/jOS_j-lib.git"
            developerConnection = "scm:git:ssh://git@github.com/github.com/dot166/jOS_j-lib.git"
        }
    }
}

configure<GenerateBpPluginExtension> {
    minSdk.set(libMinSdk)
    targetSdk.set(libCompileSdk)
    availableInAOSP.set { module: Module ->
        when {
            module.group.startsWith("org.jetbrains.compose") -> false
            module.name.startsWith("atomicfu") -> false
            module.name.startsWith("kotlinx-collections-immutable") -> false
            module.group.startsWith("org.jetbrains.androidx") -> false
            module.group.startsWith("org.jetbrains") -> true
            module.group == "com.google.accompanist" -> false
            module.group == "com.google.android.material" -> false // TEMPORARY, is set to false until AOSP updates their version of material components to 1.12.0 or newer
            module.group.startsWith("androidx") -> true
            module.group.startsWith("com.google") -> true
            else -> false
        }
    }
}
