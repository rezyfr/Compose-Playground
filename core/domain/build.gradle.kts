import extensions.*

plugins {
    id("commons.android-library")
    id("dagger.hilt.android.plugin")
}

dependencies {
    DATA
    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
}