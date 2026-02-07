# MyProyectAndroid - Ejemplo de Desarrollo Moderno en Android

Este proyecto es una aplicación de ejemplo para Android desarrollada con Kotlin y Jetpack Compose. Sirve como demostración práctica de varias integraciones y funcionalidades clave en el desarrollo de aplicaciones modernas.

El código fuente de este proyecto complementa el artículo y libro ["Primeros Pasos en Android Studio con Kotlin"](https://www.desarrollolibre.net/libros/libro-primeros-pasos-en-android-studio-con-kotlin-en-el-desarrollo-moderno-con-jetpack-compose), que ofrece una guía detallada para iniciarse en el desarrollo de Android con herramientas y prácticas actuales.

## Características

La aplicación incluye las siguientes funcionalidades:

- **Integración de Pagos con PayPal:** Una implementación funcional del SDK de PayPal para procesar pagos directamente desde la aplicación.
- **Generador de Códigos QR:** Una pantalla que genera y muestra un código QR a partir de un texto predefinido, utilizando la librería ZXing.
- **Gestor de Tareas (Base de Datos Room):** Un ejemplo de una lista de tareas (CRUD) que utiliza la librería Room para la persistencia de datos en una base de datos local SQLite. Aunque parte de este código está comentado en `MainActivity.kt`, se puede descomentar para probar su funcionamiento.

## Tecnologías y Librerías Utilizadas

- **Lenguaje:** Kotlin
- **Interfaz de Usuario:** Jetpack Compose
- **Base de Datos:** Room Persistence Library
- **Pagos:** PayPal Checkout SDK
- **Códigos QR:** ZXing ("Zebra Crossing")
- **Dependencias adicionales:** Corrutinas de Kotlin para operaciones asíncronas, ViewModel para la gestión del estado de la UI.

## Configuración del Proyecto

Para poder compilar y ejecutar este proyecto correctamente, necesitarás configurar tus propias credenciales en los siguientes lugares:

1.  **ID de Cliente de PayPal:**
    -   Abre el archivo `app/src/main/java/com/example/myproyectandroid/MainActivity.kt`.
    -   Busca la línea `clientId = "YOUR_CLIENT_ID_HERE"`.
    -   Reemplaza `"YOUR_CLIENT_ID_HERE"` por tu ID de cliente de la API de PayPal.

2.  **API Key de Google Maps:**
    -   Abre el archivo `app/src/main/AndroidManifest.xml`.
    -   Busca la línea `<meta-data android:name="com.google.android.geo.API_KEY" android:value="TuAPI" />`.
    -   Reemplaza `"TuAPI"` por tu clave de API de Google Maps.

Una vez configuradas las credenciales, puedes compilar y ejecutar la aplicación en un emulador o dispositivo físico.

---
