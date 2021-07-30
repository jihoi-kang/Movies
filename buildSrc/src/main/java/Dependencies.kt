object Versions {
    // gradle
    const val kotlin = "1.5.21"
    const val buildGradle = "4.2.2"
    const val navigation = "2.3.5"
    const val hilt = "2.38"

    // dependencies
    const val x = "1.3.0"
    const val material = "1.4.0"
    const val recyclerview = "1.2.1"
    const val constraintLayout = "2.0.4"
    const val core = "1.6.0"
    const val activityKtx = "1.2.4"
    const val fragmentKtx = "1.3.6"
    const val flexbox = "2.0.1"

    const val navigationUi = "2.3.5"
    const val navigationFragment = "2.3.5"
    const val lifecycle = "2.2.0"
    const val lifecycleLivedata = "2.3.1"
    
    const val coroutines = "1.5.1"

    const val glide = "4.11.0"
    const val glidePalette = "2.1.2"

    const val hiltViewmodel = "1.0.0-alpha03"

    const val gson = "2.8.7"
    const val retrofit = "2.9.0"
    const val okhttpLogging = "4.9.0"

    const val junit = "4.13.2"
    const val espresso = "3.4.0"
    const val xTest = "1.1.3"
}

object BuildPlugins {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object Dependencies {
    const val x = "androidx.appcompat:appcompat:${Versions.x}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val core = "androidx.core:core-ktx:$${Versions.core}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedata}"

    const val flexbox = "com.google.android:flexbox:${Versions.flexbox}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glidePalette = "com.github.florent37:glidepalette:${Versions.glidePalette}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewmodel}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val junit = "junit:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val xTest = "androidx.test.ext:junit:${Versions.xTest}"
}