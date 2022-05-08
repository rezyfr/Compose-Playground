import extensions.THEME
import extensions.DATA
import extensions.implementation

plugins {
    id("commons.android-library")
    id("commons.android-compose")
}

dependencies {
    THEME
    DATA
    implementation(SupportLib.Timber)

}