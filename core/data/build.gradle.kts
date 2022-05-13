import extensions.*
import extensions.implementation

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

dependencies {
    addStorageDependencies()
    // Network
    addNetworkDependencies()
    implementation(SupportLib.Timber)
    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
}