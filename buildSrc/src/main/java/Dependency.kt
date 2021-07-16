

/**
 * @author Pure Jiangure
 * @date 2021/2/24.
 */
object Versions {
    const val anko = "0.10.8"
    const val appcompat = "1.2.0"
    const val appStartup = "1.0.0"
    const val baseLibrary = "0.0.4"
    const val cardView = "1.0.0"
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.3.2"
    const val dataStore = "1.0.0-alpha08"
    const val espressoCore = "3.2.0"
    const val fragment = "1.3.0-alpha06"
    const val glide = "4.10.0"
    const val gradleVersion = "4.1.1"
    const val hilt = "2.28-alpha"
    const val hiltViewModel = "1.0.0-alpha02"
    const val inject = "0.5.2"
    const val koin = "2.1.5"
    const val kotlin = "1.4.20"
    const val leakCanary ="2.3"
    const val lifecycle = "2.2.0"
    const val material = "1.2.1"
    const val mmkv = "1.2.8'"
    const val navigation = "2.3.3"
    const val okHttpLogging = "4.9.0"
    const val paging = "3.0.0-alpha02"
    const val preference = "1.1.0-alpha04"
    const val recyclerview = "1.0.0"
    const val retrofit = "2.9.0"
    const val room = "2.3.0-alpha01"
    const val swipeRefreshLayout = "1.1.0"
    const val timber = "4.7.1"
    const val viewPage2 = "1.0.0"
    const val work = "2.2.0"

    const val junit = "4.12"
    const val junitExt = "1.1.1"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val preference =
        "androidx.preference:preference:${Versions.preference}"
}

object AppStartup{
    const val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"
}
object Anko {
    const val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    const val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    const val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    const val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}
object BaseLibrary {
    const val baseLibrary = "com.github.jiangpure:baselibrary:${Versions.baseLibrary}"
}

object DataStore {
    const val core = "androidx.datastore:datastore-preferences-core:${Versions.dataStore}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
}

object Depend {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Fragment {
    const val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    const val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Hilt {
    const val dagger = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val vm =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val compile = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"

}

object Inject {
    const val inject = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.inject}"
}

object Koin {
    const val core = "org.koin:koin-core:${Versions.koin}"
    const val androidCore = "org.koin:koin-android:${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val androidScope = "org.koin:koin-android-scope:$${Versions.koin}"
}

object Kt {
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object LeakCanary {
    const val android = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}

object Lifecycle {
    const val ext = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val vm = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object Material {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object MMKV{
    const val material = "com.tencent:mmkv-static:${Versions.mmkv}"
}

object Navigation{
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object OkHttp {
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
}

object Retrofit {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}
object Work{
    const val workRuntimeKtx = "androidx.work:work-runtime-ktx:${Versions.work}"
    const val workTesting = "androidx.work:work-testing:${Versions.work}"
}

object ViewPage{
    const val viewPage2 =  "androidx.viewpager2:viewpager2:${Versions.viewPage2}"
}
