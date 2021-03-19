
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AndroidConfig.id
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
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
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(AndroidX.appcompat)
    implementation(AndroidX.cardView)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
    implementation(AndroidX.pagingRuntime)
    implementation(AndroidX.swipeRefreshLayout)
    implementation(AndroidX.workRuntimeKtx)
    implementation(BaseLibrary.baseLibrary)
    implementation(DataStore.dataStore)
    implementation(Glide.glide)
    implementation(Hilt.dagger)
    implementation(Hilt.vm)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.compile)
    implementation(Material.material)
    implementation(OkHttp.logging)
    implementation(Retrofit.gson)
    implementation(Retrofit.runtime)
    kapt(Room.compiler)
    implementation(Room.runtime)
    implementation(Room.ktx)

    testImplementation(Depend.junit)
    androidTestImplementation(Depend.androidTestJunit)
    androidTestImplementation(Depend.espressoCore)
}