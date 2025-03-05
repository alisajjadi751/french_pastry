plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ali_sajjadi.frenchpastry"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ali_sajjadi.frenchpastry"
        minSdk = 23
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.15"
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
    implementation(libs.androidx.foundation.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-rxjava3:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //Splash Api
    implementation ("androidx.core:core-splashscreen:1.0.1")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.6.0")

    //constraintlayout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

    //Datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    //Glide
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    //Paging
    implementation("androidx.paging:paging-compose:3.3.5")

    //date
  /*  implementation("com.github.hamooo90:jalali-datepicker-compose:1.0.0")
    implementation("ir.huri:JalaliCalendar:1.3.3")
    implementation ("com.github.samanzamani.persiandate:PersianDate:1.4.0")
*/




}