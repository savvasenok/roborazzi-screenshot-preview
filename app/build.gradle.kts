import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "xyz.savvamirzoyan.android.roborazziscreenshotpreview"
    compileSdk = 36

    defaultConfig {
        applicationId = "xyz.savvamirzoyan.android.roborazziscreenshotpreview"
        minSdk = 26
        targetSdk = 36
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

    testOptions.unitTests {
        isIncludeAndroidResources = true

        all {
            it.maxHeapSize = "6144m"
            it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
        }
    }
}

roborazzi {
    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable.set(true)
        packages.add("xyz.savvamirzoyan.android.roborazziscreenshotpreview")
        includePrivatePreviews.set(true)
        testerQualifiedClassName.set("xyz.savvamirzoyan.android.roborazziscreenshotpreview.MyComposePreviewTester")
    }
}

dependencies {

    implementation("io.github.classgraph:classgraph:4.8.179")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    testImplementation(libs.roborazzi.preview.scanner)
    testImplementation(libs.junit.junit)
    testImplementation(libs.androidx.test.junit.ui)
    testImplementation(libs.robolectric)
    testImplementation(libs.preview.scanner.android)

    testImplementation(libs.androidx.compose.ui.uitoolingpreview)
}
