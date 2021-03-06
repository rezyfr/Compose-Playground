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
//    FEATURE_CHARACTERS
//    FEATURE_EPISODES
//    FEATURE_LOCATIONS
//    FEATURE_SETTINGS

    addNavigationDependencies()

    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
    implementation(DaggerHiltLib.Compose)
}