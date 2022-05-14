buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}