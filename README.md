# MyHome v1.0.0

Aplicación de Android para administrar propiedades inmobiliarias.

## Descripción

MyHome es una aplicación de Android que permite a los usuarios administrar propiedades inmobiliarias. 


Funciones disponibles en esta versión:
- Registro de inmobiliaria
- Reset de contraseña
- Ingreso como inmobiliaria
- Creacion de propiedades
- Visualización de propiedades
- Visualización Perfil de inmobiliaria

## IDE
Android Studio Giraffe 2022.3.1


### SDK Versions

Api Level 34

compileSdk = 34

minSdkVersion "26"

targetSdkVersion 33


### Libraries

implementation("androidx.appcompat:appcompat:1.6.1") //Libreria para el uso de AppCompat
implementation("com.google.android.material:material:1.10.0")//Libreria para el uso de Material Design
implementation("androidx.constraintlayout:constraintlayout:2.1.4") //Libreria para el uso de ConstraintLayout
implementation("com.android.support:support-annotations:28.0.0")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
implementation("androidx.navigation:navigation-fragment:2.7.4")
implementation("androidx.navigation:navigation-ui:2.7.4") //Libreria para el uso de anotaciones
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
implementation ("com.google.android.gms:play-services-tasks:18.0.2") //Libreria para el uso de Google Auth

//*****************************************

implementation ("com.github.bumptech.glide:glide:4.12.0") //Libreria para el uso de imagenes
implementation (platform("com.google.firebase:firebase-bom:32.4.0")) //Libreria para el uso de Firebase
implementation ("com.google.firebase:firebase-auth:22.2.0")         //Libreria para el uso de Firebase Auth
implementation("com.android.volley:volley:1.2.1") //Libreria para el uso de peticiones http
implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28") //Libreria para el uso de gifs
implementation ("androidx.recyclerview:recyclerview:1.3.2") //Libreria para el uso de RecyclerView
implementation ("androidx.cardview:cardview:1.0.0") //Libreria para el uso de CardView
implementation ("com.squareup.retrofit2:retrofit:2.9.0") //Libreria para el uso de Retrofit
implementation ("com.squareup.retrofit2:converter-gson:2.9.0") //Libreria para el uso de Retrofit
implementation ("com.sun.mail:android-mail:1.6.5") //no actualizar la siguiente librería a 1.6.6
implementation ("com.sun.mail:android-activation:1.6.7")
implementation ("com.google.code.gson:gson:2.10.1") //Libreria para el uso de Gson

//******librerias para el uso de las cards en propiedades********

implementation ("androidx.recyclerview:recyclerview:1.3.2")
implementation ("com.synnapps:carouselview:0.1.5")
implementation ("com.dhiwise:endless-viewpager:1.1.0")
implementation ("com.github.bumptech.glide:glide:4.12.0")
annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
implementation ("com.airbnb.android:lottie:6.1.0")
implementation ("io.github.chaosleung:pinview:1.4.4")
implementation ("androidx.databinding:databinding-runtime:8.1.2")
    
//***************************************************************

### Clases
Las clases de la aplicación son las siguientes:

* **Agencies:** Representa una agencia inmobiliaria.
* **AgenciesProfile:** Representa el perfil de una agencia inmobiliaria.
* **AgenciesState:** Representa el estado de una agencia inmobiliaria.
* **Apitest:** Clase de prueba para la API.
* **BasicCredentials:** Clase para almacenar las credenciales básicas de la aplicación.
* **Currency Type:** Representa un tipo de moneda.
* **ForgotPassword:** Clase para restablecer la contraseña de un usuario.
* **ForgotPasswordCode:** Clase para almacenar el código de restablecimiento de contraseña de un usuario.
* **GoogleCredentials:** Clase para almacenar las credenciales de Google.
* **HomeFragment:** Fragmento de la pantalla de inicio.
* **ImageAdapter:** Adaptador de imágenes para la lista de propiedades.
* **ImageSliderSliderModel:** Modelo de datos para un elemento de la diapositiva del control deslizante de imágenes.
* **ImageSliderSliderOneModel:** Modelo de datos para un elemento de la diapositiva uno del control deslizante de imágenes.
* **LibraryFragment:** Fragmento de la pantalla de biblioteca.
* **ListAgencieProperties:** Interfaz para la lista de propiedades de una agencia inmobiliaria.
* **ListUserProperties:** Interfaz para la lista de propiedades de un usuario.
* **ListView Test:** Actividad de prueba para la lista de propiedades.
* **LoginAgencies:** Clase para iniciar sesión en una agencia inmobiliaria.
* **LoginCallback:** Interfaz para el callback de inicio de sesión.
* **Login User:** Actividad para iniciar sesión en un usuario.
* **MainActivity:** Actividad principal de la aplicación.
* **MultiSelectDropDown:** Clase para crear un menú desplegable con múltiples opciones.
* **NewPassword:** Clase para crear una nueva contraseña para un usuario.
* **NewProperties:** Clase para crear una nueva propiedad.
* **Properties:** Representa una propiedad inmobiliaria.
* **RegisterAgencies:** Clase para registrar una agencia inmobiliaria.
* **RetrofitAPI:** Clase para la API.
* **RoleType:** Representa un tipo de rol de usuario.
* **SecondActivity:** Actividad secundaria de la aplicación.
* **Users:** Representa un usuario.
* **UsersApi:** Clase para la API de usuarios.
* 

### Layouts
* **activity_agencies_profile.xml:** Este layout representa la pantalla de perfil de una agencia inmobiliaria.
[Image of Layout activity_agencies_profile.xml]
* **activity_login_agencies.xml:** Este layout representa la pantalla de inicio de sesión de una agencia inmobiliaria.
[Image of Layout activity_login_agencies.xml]
* **bottomsheetlayout.xml:** Este layout representa un diseño de vista inferior.
[Image of Layout bottomsheetlayout.xml]
* **fragment_gallery.xml:** Este layout representa la pantalla de galería de fotos de una propiedad.
[Image of Layout fragment_gallery.xml]
* **fragment_home.xml:** Este layout representa la pantalla de inicio de la aplicación.
[Image of Layout fragment_home.xml]
* **fragment_library.xml:** Este layout representa la pantalla de biblioteca de propiedades de la aplicación.
[Image of Layout fragment_library.xml]
* **fragment_slideshow.xml:** Este layout representa la pantalla de presentación de diapositivas de una propiedad.
[Image of Layout fragment_slideshow.xml]
* **item_image.xml:** Este layout representa un elemento de lista de imágenes.
[Image of Layout item_image.xml]
* **layout_progress_dialog.xml:** Este layout representa un diseño de diálogo de progreso.
[Image of Layout layout_progress_dialog.xml]
* **multi_select_drop_down.xml:** Este layout representa un menú desplegable con múltiples opciones.
[Image of Layout multi_select_drop_down.xml]
* **nav_header.xml:** Este layout representa la cabecera de navegación de la aplicación.
[Image of Layout nav_header.xml]
* **slideritem_propiedades_lista_usuario1.xml:** Este layout representa un elemento de diapositiva de la lista de propiedades de un usuario.
[Image of Layout slideritem_propiedades_lista_usuario1.xml]
* **slideritem_propiedades_lista_usuario2.xml:** Este layout representa un elemento de diapositiva dos de la lista de propiedades de un usuario.
[Image of Layout slideritem_propiedades_lista_usuario2.xml]

## Instalación

Para instalar la aplicación, siga estos pasos:

1. Abra la APK facilitada por el equipo 10 desde archivos de Teams. Caso contrario:
2. Descargue el proyecto de GitHub o 
2. Abra el proyecto en Android Studio.
3. Compile y ejecute la aplicación.

## Uso

Para usar la aplicación, siga estos pasos:

1. Abra la aplicación, luego del splash
2. Presione el botón en Ingresar
3. Si no tiene una cuenta, registrarse con usuario y contraseña (debe ser mínimo de 6 caracteres)
4. Ingresar con los datos registrados en el paso 3.
5. Seleccione propiedades en la barra de navegación inferior para ver sus propiedades.
6. Seleccione nueva propiedad en la barra de navegación inferior para crear propiedades. (complete todos los campos requeridos)
7. Seleccione perfil en la barra de navegación inferior para ver su perfil de inmobiliaria.


## Autores Equipo 10
Falasco Germán
Godoy Andres
Guzmán Nydia
Leto Marcelo
Salvioli Santiago




