plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.mow"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.mow"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            //TO DO
        }
        create("staging") {
            //TO DO
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        // dataBinding = true
    }
}

dependencies {
    implementation(Deps.kotlinStdlib)
    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraintlayout)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExt)
    androidTestImplementation(Deps.espressoCore)

    //lifecycle and navigation
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)
    implementation(Deps.lifecycle)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)

    /*okhttp、retrofit*/
    implementation(Deps.okhttp)
    implementation(Deps.retrofit)
    implementation(Deps.converterGson)
    implementation(Deps.logging)

    /*glide*/
    implementation(Deps.glide)
    kapt(Deps.glideCompiler)

    /*immersionbar*/
    implementation(Deps.immersionbar)
    implementation(Deps.immersionbarktx)


}


