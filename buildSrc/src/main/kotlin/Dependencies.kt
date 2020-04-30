import org.gradle.api.JavaVersion

object Config {
    const val kotlinVersion = "1.3.72"

    const val minSdk = 26
    const val compileSdk = 29
    const val targetSdk = 29
    val javaVersion = JavaVersion.VERSION_1_8
}


object Deps {

    // kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Config.kotlinVersion}"

    private const val core_version = "1.2.0"
    const val coreKtx = "androidx.core:core-ktx:$core_version"

    private const val appcompat_version = "1.1.0"
    const val appCompat = "androidx.appcompat:appcompat:$appcompat_version"

    private const val koin_version = "2.1.5"
    const val koin = "org.koin:koin-android:$koin_version"
    const val koinScope = "org.koin:koin-androidx-scope:$koin_version"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koin_version"
    const val koinExt = "org.koin:koin-androidx-ext:$koin_version"

    private const val coroutines_version = "1.3.4"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"

    private const val threetenabp_version = "1.2.3"
    const val threeTen = "com.jakewharton.threetenabp:threetenabp:$threetenabp_version"

    private const val timber_version = "4.7.1"
    const val timber = "com.jakewharton.timber:timber:$timber_version"

    const val browser = "androidx.browser:browser:1.2.0"

    // Compose

    const val compose_version = "0.1.0-dev09"
    const val compose_compiler_version = "1.3.61-dev-withExperimentalGoogleExtensions-20200129"
    const val composeRuntime = "androidx.compose:compose-runtime:$compose_version"

    const val composeFramework = "androidx.ui:ui-framework:$compose_version"
    const val composeLayout = "androidx.ui:ui-layout:$compose_version"
    const val composeMaterial = "androidx.ui:ui-material:$compose_version"
    const val composeTooling = "androidx.ui:ui-tooling:$compose_version"
    const val composeLiveData = "androidx.ui:ui-livedata:$compose_version"

    private const val lifecyclektx_version = "2.3.0-alpha01"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecyclektx_version"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecyclektx_version"

    private const val jsoup_version = "1.13.1"
    const val jsoup = "org.jsoup:jsoup:$jsoup_version"

    // room

    private const val room_version = "2.2.5"
    const val roomKtx = "androidx.room:room-ktx:$room_version"
    const val roomCompiler = "androidx.room:room-compiler:$room_version"

    // work
    private const val work_version = "2.3.4"
    const val workRuntime = "androidx.work:work-runtime-ktx:$work_version"
    const val workGcm = "androidx.work:work-gcm:$work_version"

    // testing

    private const val junit_version = "4.12"
    const val junit = "junit:junit:$junit_version"
}