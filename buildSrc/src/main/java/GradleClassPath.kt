/**
 * @author Pure Jiang
 * @date 2021/2/26.
 */


object GradleClassPath {

    const val GRADLE_BUILD = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    //Hilt依赖注入必要的
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    //导航组件传递数据必要的
    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}