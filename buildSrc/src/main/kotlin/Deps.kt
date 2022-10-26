
object Versions{
    const val gradle_version = "7.0.2"
    const val kotlin_version = "1.5.31"
    const val core_version = "1.7.0"
    const val compat_version = "1.5.1"
    const val material_version = "1.6.1"
    const val constraint_version = "2.1.4"

    const val junit_version = "4.13.2"
    const val extJunit_version = "1.1.3"
    const val espresso_version = "3.4.0"

    const val navigation_version = "2.5.3"
    const val lifecycle_version = "2.2.0"

    const val room_version = "2.2.5"

    const val okhttp_version = "4.9.0"
    const val retrofit_version = "2.9.0"
    const val gson_version = "2.9.0"
    const val logging_version = "3.12.0"

    const val glide_version = "4.11.0"

    const val immersionbar_version = "3.2.2"


}

object Deps {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"

    const val junit = "junit:junit:${Versions.junit_version}"
    const val junitExt = "androidx.test.ext:junit:${Versions.extJunit_version}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"

    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation_version}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation_version}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"

    /*okhttp、retrofit*/
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.gson_version}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_version}"

    /*glide*/
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"

    /*immersionbar*/
    const val immersionbar = "com.geyifeng.immersionbar:immersionbar:${Versions.immersionbar_version}"
    const val immersionbarktx = "com.geyifeng.immersionbar:immersionbar-ktx:${Versions.immersionbar_version}"
}