# MyHome v2.0.0

Aplicación de Android para administrar propiedades inmobiliarias.

## Descripción

MyHome es una aplicación de Android que permite a los usuarios administrar y consultar propiedades inmobiliarias. 


## IDE
Android Studio Giraffe 2022.3.1


### SDK Versions

Api Level "34"

compileSdk "34"

minSdkVersion "26"

targetSdkVersion 33


### Libraries
implementation("androidx.appcompat:appcompat:1.6.1") //Libreria para el uso de AppCompat
implementation("com.google.android.material:material:1.10.0")//Libreria para el uso de Material Design
implementation("androidx.constraintlayout:constraintlayout:2.1.4") //Libreria para el uso de ConstraintLayout
implementation("com.android.support:support-annotations:28.0.0") //Libreria para el uso de anotaciones
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") //Libreria para el uso de LiveData
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") //Libreria para el uso de ViewModel
implementation("androidx.navigation:navigation-fragment:2.7.5") //Libreria para el uso de Navigation
implementation("androidx.navigation:navigation-ui:2.7.5")       //Libreria para el uso de Navigation
implementation("com.google.firebase:firebase-analytics:21.5.0") //Libreria para el uso de Firebase Analytics
implementation("com.google.firebase:firebase-database:20.3.0") //Libreria para el uso de anotaciones
testImplementation("junit:junit:4.13.2")    //Libreria para el uso de pruebas unitarias
androidTestImplementation("androidx.test.ext:junit:1.1.5") //Libreria para el uso de pruebas unitarias
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") //Libreria para el uso de pruebas unitarias
implementation ("com.google.android.gms:play-services-auth-api-phone:18.0.1") //Libreria para el uso de Google Auth
implementation ("com.google.android.gms:play-services-base:18.2.0") //Libreria para el uso de Google Auth
implementation ("com.google.android.gms:play-services-basement:18.2.0") //Libreria para el uso de Google Auth
implementation ("com.google.android.gms:play-services-tasks:18.0.2") //Libreria para el uso de Google Auth
implementation ("com.google.android.gms:play-services-auth:20.7.0") //Libreria para el uso de Google Auth
implementation(platform("com.google.firebase:firebase-bom:32.6.0")) //Libreria para el uso de Firebase
implementation("com.google.firebase:firebase-auth") //Libreria para el uso de Firebase Auth
implementation ("com.github.bumptech.glide:glide:4.12.0")   //Libreria para el uso de Glide
implementation ("com.google.android.gms:play-services-location:21.0.1") //Libreria para el uso de Google Location
implementation ("com.github.bumptech.glide:glide:4.12.0") //Libreria para el uso de imagenes
implementation  ("com.android.volley:volley:1.2.1") //Libreria para el uso de peticiones http
implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28") //Libreria para el uso de gifs
implementation ("androidx.recyclerview:recyclerview:1.3.2") //Libreria para el uso de RecyclerView
implementation ("androidx.cardview:cardview:1.0.0") //Libreria para el uso de CardView
implementation ("com.squareup.retrofit2:retrofit:2.9.0") //Libreria para el uso de Retrofit
implementation ("com.squareup.retrofit2:converter-gson:2.9.0") //Libreria para el uso de Retrofit
implementation ("com.sun.mail:android-mail:1.6.5") //NO ACTUALIZAR ESTA LIBRERIA
implementation ("com.sun.mail:android-activation:1.6.7") //NO ACTUALIZAR ESTA LIBRERIA
implementation ("com.google.code.gson:gson:2.10.1") //Libreria para el uso de Gson
implementation ("androidx.recyclerview:recyclerview:1.3.2") //Libreria para el uso de RecyclerView
implementation ("com.synnapps:carouselview:0.1.5") //Libreria para el uso de CarouselView
implementation ("com.dhiwise:endless-viewpager:1.1.0") //Libreria para el uso de EndlessViewPager
implementation ("com.github.bumptech.glide:glide:4.12.0") //Libreria para el uso de Glide
annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0") //Libreria para el uso de Glide
implementation ("com.airbnb.android:lottie:6.2.0")  //Libreria para el uso de Lottie
implementation ("io.github.chaosleung:pinview:1.4.4") //Libreria para el uso de PinView
implementation ("androidx.databinding:databinding-runtime:8.1.4") //Libreria para el uso de DataBinding
implementation ("com.github.moondroid.coverflow:library:1.0") //para el slide de imagenes
implementation("com.azure:azure-storage-blob:12.25.0") //para el almacenamiento en Azure
implementation ("com.microsoft.azure.android:azure-storage-android:2.0.0") //para el almacenamiento en Azure
implementation ("de.hdodenhof:circleimageview:3.1.0") //para el uso de imagenes circulares
implementation ("com.squareup.picasso:picasso:2.71828") //para el uso de imagenes
implementation("com.crystal:crystalrangeseekbar:1.1.3") //para el uso de RangeSeekBar
implementation ("com.github.Jay-Goo:RangeSeekBar:v3.0.0") //para el uso de RangeSeekBar
implementation("org.florescu.android.rangeseekbar:rangeseekbar-library:0.3.0") //para el uso de RangeSeekBar

### Clases
Api 
    --- AgencyApi
    --- Apitest
    --- Myhome
    --- PropertyApi
    --- RatingApi
    --- RetrofitAPI
    --- UsersApi
AzureService
    ---AzureBlobStorageManager
Front
    ---AgenciesProfile
    ---AgenciesRating
    ---CustomSpinnerAdaptar
    ---DetailProperty
    ---DetailUserProperty
    ---EditProperty
    ---FilterUserProperties
    ---ForgotPassword
    ---ForgotPasswordCode
    ---ImageAdapter
    ---ListAgencieProperties
    ---ListAgencieReviews
    ---ListFavoriteProperties
    ---ListUserProperties
    ---ListViewTest
    ---LoginAgencies
    ---LoginUser
    ---MainActivity
    ---MenuHandler
    ---MenuHandlerUsuario
    ---NewPassword
    ---NewProperties
    ---Perfilinmobiliaria
    ---RegisterAgencies
    ---ReserveProperty
    ---SecondActivity
    ---UploadImageActivity
    ---UserSchedule
    ---UserProfile
GoogleService
    ---GoogleMap
Interfaces
    ---AgencyCallBack
    ---LoginCallBack
    ---PropertiesCallBack
    ---RatingCallBack
model:enums
    ---CurrencyType
    ---RoleType
model
    ---Address
    ---Agencies
    ---BasicCredentials
    ---FiltersDTO
    ---GoogleCredentials
    ---Properties
    ---PropertyDTO
    ---PropertySummary
    ---Resenas
    ---Users
Network
    ---NetworkUtils

### Values
* **colors.xml:** Este archivo representa los colores de la aplicación.
* **dimens.xml:** Este archivo representa las dimensiones de la aplicación.
* **strings.xml:** Este archivo representa las cadenas de la aplicación.
* **styles.xml:** Este archivo representa los estilos de la aplicación.
* **themes.xml:** Este archivo representa los temas de la aplicación.

### Drawable
* **ic_baseline_add_24.xml:** Este archivo representa el icono de agregar.
* **ic_baseline_arrow_back_24.xml:** Este archivo representa el icono de retroceso.
* **ic_baseline_arrow_forward_24.xml:** Este archivo representa el icono de avance.
* **ic_baseline_delete_24.xml:** Este archivo representa el icono de eliminar.
* **ic_baseline_edit_24.xml:** Este archivo representa el icono de editar.
* **ic_baseline_email_24.xml:** Este archivo representa el icono de correo electrónico.
* **ic_baseline_exit_to_app_24.xml:** Este archivo representa el icono de salida de la aplicación.
* **ic_baseline_home_24.xml:** Este archivo representa el icono de inicio.
* **ic_baseline_image_24.xml:** Este archivo representa el icono de imagen.
* **ic_baseline_lock_24.xml:** Este archivo representa el icono de bloqueo.
* **ic_baseline_menu_24.xml:** Este archivo representa el icono de menú.
* **ic_baseline_person_24.xml:** Este archivo representa el icono de persona.
* **ic_baseline_phone_24.xml:** Este archivo representa el icono de teléfono.
* **ic_baseline_photo_camera_24.xml:** Este archivo representa el icono de cámara de fotos.
* **ic_baseline_search_24.xml:** Este archivo representa el icono de búsqueda.
* **ic_baseline_settings_24.xml:** Este archivo representa el icono de configuración.
* **ic_baseline_slideshow_24.xml:** Este archivo representa el icono de presentación de diapositivas.
* **ic_baseline_visibility_24.xml:** Este archivo representa el icono de visibilidad.
* **ic_baseline_visibility_off_24.xml:** Este archivo representa el icono de visibilidad desactivada.
* **ic_baseline_warning_24.xml:** Este archivo representa el icono de advertencia.
* **ic_launcher_background.xml:** Este archivo representa el icono de fondo de la aplicación.
* **ic_launcher_foreground.xml:** Este archivo representa el icono de primer plano de la aplicación.


### Menu
* **bottom_nav_menu.xml:** Este archivo representa el menú de navegación inferior de la aplicación.
* **drawer_menu.xml:** Este archivo representa el menú de navegación lateral de la aplicación.
* **menu_agencies_profile.xml:** Este archivo representa el menú de perfil de una agencia inmobiliaria.
* **menu_agencies_state.xml:** Este archivo representa el menú de estado de una agencia inmobiliaria.
* **menu_agencies.xml:** Este archivo representa el menú de una agencia inmobiliaria.
* **menu_home.xml:** Este archivo representa el menú de inicio de la aplicación.
* **menu_library.xml:** Este archivo representa el menú de biblioteca de la aplicación.
* **menu_login_agencies.xml:** Este archivo representa el menú de inicio de sesión de una agencia inmobiliaria.
* **menu_login.xml:** Este archivo representa el menú de inicio de sesión de un usuario.
* **menu_main.xml:** Este archivo representa el menú principal de la aplicación.
* **menu_new_properties.xml:** Este archivo representa el menú de nueva propiedad.
* **menu_properties.xml:** Este archivo representa el menú de una propiedad.
* **menu_register_agencies.xml:** Este archivo representa el menú de registro de una agencia inmobiliaria.
* **menu_register.xml:** Este archivo representa el menú de registro de un usuario.
* **menu_second.xml:** Este archivo representa el menú secundario de la aplicación.
* **menu_slideshow.xml:** Este archivo representa el menú de presentación de diapositivas de una propiedad.
* **menu_users.xml:** Este archivo representa el menú de un usuario.
* **nav_header.xml:** Este archivo representa la cabecera de navegación de la aplicación.
* **nav_menu.xml:** Este archivo representa el menú de navegación de la aplicación.
* **nav_menu_agencies.xml:** Este archivo representa el menú de navegación de una agencia inmobiliaria.
* **nav_menu_agencies_profile.xml:** Este archivo representa el menú de navegación de perfil de una agencia inmobiliaria.
* **nav_menu_agencies_state.xml:** Este archivo representa el menú de navegación de estado de una agencia inmobiliaria.
* **nav_menu_home.xml:** Este archivo representa el menú de navegación de inicio de la aplicación.
* **nav_menu_library.xml:** Este archivo representa el menú de navegación de biblioteca de la aplicación.
* **nav_menu_login.xml:** Este archivo representa el menú de navegación de inicio de sesión de un usuario.
* **nav_menu_new_properties.xml:** Este archivo representa el menú de navegación de nueva propiedad.
* **nav_menu_properties.xml:** Este archivo representa el menú de navegación de una propiedad.
* **nav_menu_register.xml:** Este archivo representa el menú de navegación de registro de un usuario.
* **nav_menu_second.xml:** Este archivo representa el menú de navegación secundario de la aplicación.
* **nav_menu_slideshow.xml:** Este archivo representa el menú de navegación de presentación de diapositivas de una propiedad.
* **nav_menu_users.xml:** Este archivo representa el menú de navegación de un usuario.
* **nav_menu_users_profile.xml:** Este archivo representa el menú de navegación de perfil de un usuario.
* **nav_menu_users_state.xml:** Este archivo representa el menú de navegación de estado de un usuario.
* **nav_menu_users.xml:** Este archivo representa el menú de navegación de un usuario.
* **nav_menu.xml:** Este archivo representa el menú de navegación de la aplicación.


### Anim
* **fade_in.xml:** Este archivo representa la animación de desvanecimiento de entrada.
* **fade_out.xml:** Este archivo representa la animación de desvanecimiento de salida.
* **slide_in_left.xml:** Este archivo representa la animación de deslizamiento hacia la izquierda.
* **slide_in_right.xml:** Este archivo representa la animación de deslizamiento hacia la derecha.
* **slide_out_left.xml:** Este archivo representa la animación de deslizamiento hacia la izquierda.
* **slide_out_right.xml:** Este archivo representa la animación de deslizamiento hacia la derecha.
* **slide_up.xml:** Este archivo representa la animación de deslizamiento hacia arriba.
* **slide_down.xml:** Este archivo representa la animación de deslizamiento hacia abajo.
* **slide_up_down.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo.
* **slide_down_up.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba.
* **slide_left_right.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha.
* **slide_right_left.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda.
* **slide_left_right_fade.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con desvanecimiento.
* **slide_right_left_fade.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con desvanecimiento.
* **slide_up_down_fade.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con desvanecimiento.
* **slide_down_up_fade.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con desvanecimiento.
* **slide_left_right_accelerate.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con aceleración.
* **slide_right_left_accelerate.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con aceleración.
* **slide_up_down_accelerate.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con aceleración.
* **slide_down_up_accelerate.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con aceleración.
* **slide_left_right_decelerate.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con desaceleración.
* **slide_right_left_decelerate.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con desaceleración.
* **slide_up_down_decelerate.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con desaceleración.
* **slide_down_up_decelerate.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con desaceleración.
* **slide_left_right_accelerate_decelerate.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con aceleración y desaceleración.
* **slide_right_left_accelerate_decelerate.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con aceleración y desaceleración.
* **slide_up_down_accelerate_decelerate.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con aceleración y desaceleración.
* **slide_down_up_accelerate_decelerate.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con aceleración y desaceleración.
* **slide_left_right_bounce.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con rebote.
* **slide_right_left_bounce.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con rebote.
* **slide_up_down_bounce.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con rebote.
* **slide_down_up_bounce.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con rebote.
* **slide_left_right_overshoot.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con sobrepaso.
* **slide_right_left_overshoot.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con sobrepaso.
* **slide_up_down_overshoot.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con sobrepaso.
* **slide_down_up_overshoot.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con sobrepaso.
* **slide_left_right_anticipate.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con anticipación.
* **slide_right_left_anticipate.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con anticipación.
* **slide_up_down_anticipate.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con anticipación.
* **slide_down_up_anticipate.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con anticipación.
* **slide_left_right_anticipate_overshoot.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con anticipación y sobrepaso.
* **slide_right_left_anticipate_overshoot.xml:** Este archivo representa la animación de deslizamiento de derecha a izquierda con anticipación y sobrepaso.
* **slide_up_down_anticipate_overshoot.xml:** Este archivo representa la animación de deslizamiento hacia arriba y hacia abajo con anticipación y sobrepaso.
* **slide_down_up_anticipate_overshoot.xml:** Este archivo representa la animación de deslizamiento hacia abajo y hacia arriba con anticipación y sobrepaso.
* **slide_left_right_bounce_accelerate.xml:** Este archivo representa la animación de deslizamiento de izquierda a derecha con rebote y aceleración.



## Instalación

Para instalar la aplicación, siga estos pasos:

1. Abra la APK facilitada por el equipo 10 desde archivos de Teams. Caso contrario:
2. Descargue el proyecto de GitHub  
2. Abra el proyecto en Android Studio.
3. Compile y ejecute la aplicación.



## Autores Equipo 10
Falasco Germán

Godoy Andres

Guzmán Nydia

Leto Marcelo

Salvioli Santiago
