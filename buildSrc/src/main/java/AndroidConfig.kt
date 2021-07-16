
object AndroidConfig {
    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val buildToolsVersion = "30.0.0"

    const val versionCode = 1
    const val versionName = "1.0"

    const val id = "com.jpure.rickandmorty"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

interface BuildTypes {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildTypes {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildTypes {
    override val isMinifyEnabled = false
}

