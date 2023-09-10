plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.mansi.jokesapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mansi.jokesapplication"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        android.buildFeatures.buildConfig = true
        buildConfigField ("String","BASE_URL" , "\"https://geek-jokes.sameerkumar.website/\"")
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

    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

val kotlinVersion = "1.9.0"
val retrofitVersion = "2.9.0"
val moshiVersion = "1.14.0"
val converterMoshiVersion = "2.9.0"
val mockitoKotlinVersion = "1.5.0"
val mockitoInlineVersion = "2.11.0"
val coroutinesVersion = "1.6.4"
val koinVersion = "3.4.3"
val koinArchitectureVersion = "0.8.2"
val mockkVersion = "1.9.3"
val lifecycleViewmodelVersion = "2.6.1"
val roomVersion = "2.5.2"

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation ("androidx.multidex:multidex:2.0.1")
    //Room
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("androidx.room:room-runtime:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")
    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewmodelVersion")
    //UI
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    //REST - APIService
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$converterMoshiVersion")
    implementation ("com.squareup.moshi:moshi:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.8.1")
    //Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-android:$koinVersion")
    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}