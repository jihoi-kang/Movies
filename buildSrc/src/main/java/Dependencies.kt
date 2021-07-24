object Versions {
    const val compile_sdk = 30
    const val min_sdk = 23
    const val target_sdk = 30

    const val version_code = 1
    const val version_name = "1.0"

    const val x = "1.3.0"
    const val material = "1.4.0"
    const val recyclerview = "1.2.1"
    const val constraint_layout = "2.0.4"
    const val core = "1.6.0"
    const val activity_ktx = "1.2.4"
    const val fragment_ktx = "1.3.6"
    const val navigation_ui = "2.3.5"
    const val navigation_fragment = "2.3.5"
    const val lifecycle = "2.2.0"
    const val lifecycle_livedata = "2.3.1"
    const val coroutines = "1.5.1"

    const val flexbox = "2.0.1"

    const val kotlin = "1.4.20"

    const val glide = "4.11.0"
    const val glide_palette = "2.1.2"

    const val hilt = "2.38"
    const val hilt_viewmodel = "1.0.0-alpha03"

    const val gson = "2.8.7"
    const val retrofit = "2.9.0"
    const val okhttp_logging = "4.9.0"

    const val junit = "4.13.2"
    const val espresso = "3.4.0"
    const val x_test = "1.1.3"
}

object Libs {
    val x = "androidx.appcompat:appcompat:${Versions.x}"
    val material = "com.google.android.material:material:${Versions.material}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val core = "androidx.core:core-ktx:$${Versions.core}"
    val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ui}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_fragment}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata}"

    val flexbox = "com.google.android:flexbox:${Versions.flexbox}"

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_palette = "com.github.florent37:glidepalette:${Versions.glide_palette}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel}"
    val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val junit = "junit:junit:${Versions.junit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val x_test = "androidx.test.ext:junit:${Versions.x_test}"
}