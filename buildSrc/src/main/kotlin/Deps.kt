
object Versions{
    const val gradle_version = "7.0.2"
    const val kotlin_version = "1.5.31"
    const val core_version = "1.7.0"
    const val compat_version = "1.5.1"
    const val material_version = "1.6.1"
    const val constraint_version = "2.1.4"
    const val navigationFragment_version = "2.3.2"
    const val navigation_version = "2.3.2"
    const val lifecycle_version = "2.2.0"
    const val room_version = "2.2.5"
    const val junit_version = "4.13.2"
    const val extJunit_version = "1.1.3"
    const val espresso_version = "3.4.0"
}

object Deps {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigationFragment_version}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation_version}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment_version}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val junit = "junit:junit:${Versions.junit_version}"
    const val junitExt = "androidx.test.ext:junit:${Versions.extJunit_version}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
}