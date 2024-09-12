plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id ("kotlin-kapt")
//    id ("kotlin-android-extensions")
}

android {
    namespace = "com.example.pokedex"
    compileSdk = 34 // 34

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = 24 // 24
        targetSdk = 34 // 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.common.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // jatpack compose tables
    implementation("com.github.Breens-Mbaka:BeeTablesCompose:1.2.0")

    // retrofit - para fazer pedidos http e fazer parse de json para classes em kotlin ou java
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // glide - para passar imagens de um link da web para o kotlin
    implementation(libs.glide)
    implementation ("com.github.skydoves:landscapist-glide:1.4.8") // GlideCard

    // ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.fragment:fragment-ktx:1.8.2") // Use the latest version

    // load images from url
    implementation("io.coil-kt:coil-compose:2.4.0")


    // pagination
    var paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime:$paging_version")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    // Room - to save the data locally and see it in offline mode (cache que funciona como uma base de dados sql)
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-paging:2.6.1")

    // for searching the proeminent color of an image
    implementation("androidx.palette:palette:1.0.0")

}