import extensions.*

plugins {
    id("commons.android-feature")
    id("commons.android-compose")
    id("com.google.devtools.ksp")
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "home")
}

dependencies {
    PROVIDER
    THEME
    DOMAIN
    COMPONENT
    NAVIGATION

    addNavigationDependencies()

    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
    implementation(DaggerHiltLib.Compose)
}