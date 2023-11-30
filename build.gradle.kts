// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }

dependencies {
    classpath ("com.google.gms:google-services:4.3.0")
    classpath ("com.android.tools.build:gradle:8.1.4")
}
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()

    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.3.0" apply false


}


