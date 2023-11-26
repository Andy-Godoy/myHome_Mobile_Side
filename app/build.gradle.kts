plugins {
    id("com.android.application") //antes era .application
    id("com.google.gms.google-services")


}


android {
    namespace = "com.example.myhome"
    compileSdk = 34

    packagingOptions {
        exclude ("META-INF/DEPENDENCIES")
        exclude ("META-INF/LICENSE")
        exclude ("META-INF/LICENSE.txt")
        exclude ("META-INF/license.txt")
        exclude ("META-INF/NOTICE")
        exclude ("META-INF/NOTICE.txt")
        exclude ("META-INF/notice.txt")
        exclude ("META-INF/ASL2.0")
        exclude ("META-INF/INDEX.LIST")
        exclude ("META-INF/io.netty.versions.properties")
    }


    defaultConfig {
        applicationId = "com.example.myhome"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = false
        dataBinding = false
    }


}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }


}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1") //Libreria para el uso de AppCompat
    implementation("com.google.android.material:material:1.10.0")//Libreria para el uso de Material Design
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") //Libreria para el uso de ConstraintLayout
    implementation("com.android.support:support-annotations:28.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5") //Libreria para el uso de anotaciones

    testImplementation("junit:junit:4.13.2")    //Libreria para el uso de pruebas unitarias
    androidTestImplementation("androidx.test.ext:junit:1.1.5") //Libreria para el uso de pruebas unitarias
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") //Libreria para el uso de pruebas unitarias

    //implementación de librerias para el uso de Google Auth
    implementation ("com.google.android.gms:play-services-auth:20.7.0") //Libreria para el uso de Google Auth

    //******nuevas librerias agregadas para auth
    implementation ("com.google.android.gms:play-services-auth-api-phone:18.0.1") //Libreria para el uso de Google Auth

    implementation ("com.google.android.gms:play-services-auth-base:18.0.10") //Libreria para el uso de Google Auth
    implementation ("com.google.android.gms:play-services-base:18.2.0") //Libreria para el uso de Google Auth
    implementation ("com.google.android.gms:play-services-basement:18.2.0") //Libreria para el uso de Google Auth
    //implementation ("com.google.firebase:firebase-auth:23.0.0")
    implementation ("com.google.android.gms:play-services-tasks:18.0.2") //Libreria para el uso de Google Auth

    //*****************************************


    implementation ("com.github.bumptech.glide:glide:4.12.0") //Libreria para el uso de imagenes
    implementation (platform("com.google.firebase:firebase-bom:32.6.0")) //Libreria para el uso de Firebase
    implementation ("com.google.firebase:firebase-auth:22.3.0")         //Libreria para el uso de Firebase Auth
    //Implementación de libreria para el uso de mapas
    //implementation ("com.google.android.gms:play-services-maps:18.1.0")
    //implementation ("com.google.android.gms:play-services-location:21.0.1")
    //implementation ("com.google.android.gms:play-services-places:17.0.0")
    //implementation ("com.google.android.libraries.places:places:3.2.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation("com.android.volley:volley:1.2.1") //Libreria para el uso de peticiones http

    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28") //Libreria para el uso de gifs
    implementation ("androidx.recyclerview:recyclerview:1.3.2") //Libreria para el uso de RecyclerView
    implementation ("androidx.cardview:cardview:1.0.0") //Libreria para el uso de CardView


    implementation ("com.squareup.retrofit2:retrofit:2.9.0") //Libreria para el uso de Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") //Libreria para el uso de Retrofit
    implementation ("com.sun.mail:android-mail:1.6.5") //NO ACTUALIZAR ESTA LIBRERIA
    implementation ("com.sun.mail:android-activation:1.6.7")
    implementation ("com.google.code.gson:gson:2.10.1") //Libreria para el uso de Gson

    //******librerias para el uso de las cards en propiedades********

    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("com.synnapps:carouselview:0.1.5")
    implementation ("com.dhiwise:endless-viewpager:1.1.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.airbnb.android:lottie:6.2.0")
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation ("androidx.databinding:databinding-runtime:8.1.4")
    //implementation ("com.asksira.android:loopingviewpager:1.4.1")

    implementation ("com.github.moondroid.coverflow:library:1.0") //para el slide de imagenes
    implementation("com.azure:azure-storage-blob:12.25.0") //para el almacenamiento en Azure
    implementation ("com.microsoft.azure.android:azure-storage-android:2.0.0")
    //implementation ("com.azure:azure-storage-common:12.25.0")
    //***************************************************************
    implementation ("de.hdodenhof:circleimageview:3.1.0") //para el uso de imagenes circulares
}
