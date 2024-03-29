plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // needed for the dependency injection
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ca.tetervak.diceroller"
    compileSdk = 34

    defaultConfig {
        applicationId = "ca.tetervak.diceroller"
        minSdk = 31
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // needed for viewModel() function
    //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // needed for the dependency injection
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    // needed for the navigation with view models
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // needed for the view model per destination, the hiltViewModel() function
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // need to receive remote data
    //implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // needed by the local database, not compatible with ksp
    val roomVersion="2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // needed to store settings
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // display images from web or assets
    //implementation("io.coil-kt:coil-compose:2.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}