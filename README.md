<p align="center"><a href="https://www.uns.edu.pe" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/1/1a/Universidad_Nacional_del_Santa_Logo.png" width="250" alt="UNS Logo"></a></p>

<p align="center">
  <a href="https://developer.android.com" target="_blank"><img src="https://img.shields.io/badge/Android-3BBC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"></a>
  <a href="https://kotlinlang.org" target="_blank"><img src="https://img.shields.io/badge/Kotlin-7F52DD?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"></a>
  <a href="https://www.objecthunter.net/exp4j/" target="_blank"><img src="https://img.shields.io/badge/exp4j-FF6600?style=for-the-badge&logo=gradle&logoColor=white" alt="exp4j"></a>
  <a href="https://github.com/google/gson" target="_blank"><img src="https://img.shields.io/badge/Gson-353535?style=for-the-badge&logo=json&logoColor=white" alt="Gson"></a>
</p>

<p align="center">
  <a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank"><img src="https://img.shields.io/badge/ViewModel-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="ViewModel"></a>
  <a href="https://developer.android.com/topic/libraries/architecture/livedata" target="_blank"><img src="https://img.shields.io/badge/LiveData-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="LiveData"></a>
  <a href="https://developer.android.com/reference/android/content/SharedPreferences" target="_blank"><img src="https://img.shields.io/badge/SharedPreferences-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="SharedPreferences"></a>
</p>

## 📱 Calculadora Clásica en Android

Una calculadora clásica para Android escrita en Kotlin, con arquitectura **MVVM**, uso de **ViewBinding**, **LiveData**, y almacenamiento persistente de historial con **SharedPreferences**. Compatible desde **Android 7.0 (API 24)**.

## 🎯 Funcionalidades

- 🔁 **Persistencia en cambios de configuración**: La expresión, el resultado actual y el historial se conservan incluso al rotar la pantalla o al cambiar entre modo claro/oscuro, gracias al uso de `ViewModel` y `SharedPreferences`.
- 🧮 **Evaluación matemática** en tiempo real con `exp4j`.
- ✏️ Campo de expresión editable, con **soporte para mover el cursor** manualmente.
- 🔢 **Botones clásicos de calculadora**:
  ```
  [  C  ] [ DEL ] [  ^  ] [  /  ]
  [  7  ] [  8  ] [  9  ] [  *  ]
  [  4  ] [  5  ] [  6  ] [  -  ]
  [  1  ] [  2  ] [  3  ] [  +  ]
  [ ( ) ] [  0  ] [  .  ] [  =  ]
  ```
- 🧠 **Botón `( )` inteligente**: inserta paréntesis según el contexto.
- 💡 Resultado actualizado **en tiempo real** mientras escribes.
- ✅ Botón `=` copia el resultado a la expresión y guarda en historial.

## 📸 Capturas

### Modo claro vertical

<img width="250" alt="image" src="https://github.com/user-attachments/assets/f67a84b1-b638-4b2d-9a43-3ffdddbe4e4a" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/4deb3df6-1657-42a7-b49e-9cd7cfa0d20a" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/8f84e29f-aa91-419c-a0f2-a0ba749d145e" />

### Modo claro horizontal

<img height="250" alt="image" src="https://github.com/user-attachments/assets/76778a06-0f41-45fb-ab31-aafef1688455" />
<img height="250" alt="image" src="https://github.com/user-attachments/assets/998602c8-dbe3-4c6c-808c-edcbe0cd257e" />

### Modo oscuro vertical

<img width="250" alt="image" src="https://github.com/user-attachments/assets/886f9c9d-6fc3-4bff-adc6-6913a8071781" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/87d76564-9407-456a-928f-c83874155827" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/938dbb8a-09ff-48c8-bb72-75b8b5acea55" />

### Modo oscuro horizontal

<img height="250" alt="image" src="https://github.com/user-attachments/assets/b0faa0d1-01cb-44a6-b665-c47353447c60" />
<img height="250" alt="image" src="https://github.com/user-attachments/assets/391a86c8-6632-45e1-8ef6-926a89ddd096" />

## 📚 Historial de operaciones recuperable

- Accesible desde botón junto al resultado.
- Muestra las operaciones previas (`2 + 3 = 5`) en un `RecyclerView`.
- Al hacer clic en una operación, se inserta de nuevo en la expresión.
- Se puede **borrar todo el historial** desde el fragmento.

## 🛠️ Tecnologías utilizadas

- Kotlin
- MVVM (ViewModel + LiveData)
- ViewBinding
- SharedPreferences
- exp4j (motor de evaluación matemática)
- RecyclerView
- Fragment
- API mínima: **24 (Android 7.0)**

## 📦 Estructura del proyecto

```
📁 app/
├── 📁 manifests/
│   └── 📄 AndroidManifest.xml             # Declaración de componentes de la app
│
├── 📁 kotlin + java/
│   └── 📦 com.episi.androidapp_s12_calculadora/
│       ├── 🧩 MainActivity                # Actividad principal, interfaz
│       ├── 🧩 CalculatorViewModel         # Lógica y estado de la calculadora con LiveData
│       ├── 🧩 CalculatorViewModelFactory  # Factory para inyectar dependencias en ViewModel
│       ├── 🧩 CalculatorRepository        # Manejo del historial usando SharedPreferences
│       ├── 🧩 HistoryFragment             # Fragmento que muestra el historial de operaciones
│       └── 🧩 HistoryAdapter              # Adaptador para RecyclerView del historial
│
├── 📁 res/
│   ├── 📁 drawable/                       # Recursos gráficos estáticos
│   │
│   ├── 📁 layout/
│   │   ├── 📄 activity_main.xml           # Diseño principal con botones
│   │   ├── 📄 fragment_history.xml        # Layout del historial
│   │   └── 📄 item_history.xml            # Vista individual del historial
│   │
│   ├── 📁 mipmap/                         # Íconos de launcher en distintas resoluciones
│   │
│   ├── 📁 values/
│   │   ├── 📁 colors/
│   │   │   ├── 📄 colors.xml              # Colores para modo claro
│   │   │   └── 📄 colors.xml (night)      # Colores para modo oscuro
│   │   ├── 📁 dimens/
│   │   │   ├── 📄 dimens.xml              # Dimensiones por defecto
│   │   │   └── 📄 dimens.xml (land)       # Dimensiones en orientación horizontal
│   │   ├── 📄 strings.xml                 # Cadenas de texto
│   │   ├── 📄 styles.xml                  # Estilos comunes
│   │   └── 📁 themes/
│   │       ├── 📄 themes.xml              # Tema para modo claro
│   │       └── 📄 themes.xml (night)      # Tema para modo oscuro
│   │
│   └── 📁 xml/                            # Archivos XML generales
```

## ✅ Cómo correr el proyecto

### Requisitos

- Android Studio
- Gradle (usado automáticamente por Android Studio)
- Emulador o dispositivo físico con Android 7.0 (API 24)

### Clonar el repositorio

1. Abre Android Studio Meerkat.
2. Ve a `File > New > Project from Version Control`.
3. Ingresa la URL del repositorio: `https://github.com/josevasquezramos/AndroidApp_s12_calculadora.git`
4. Haz clic en **Clone**.
5. Android Studio descargará y abrirá el proyecto automáticamente.

### Configurar el entorno

#### Verificar el SDK de Android 7.0 (API 24)

- Ve a `Tools > SDK Manager`.
- En la pestaña **SDK Platforms**, asegúrate de que **Android 7.0 (Nougat)** esté seleccionado.
- En la pestaña **SDK Tools**, verifica que estén instalados:
  - Android SDK Build-Tools
  - Android Emulator
  - Android SDK Platform-Tools

#### Seleccionar o crear un emulador compatible

- Ve a `Tools > Device Manager`.
- Crea un nuevo dispositivo virtual (AVD) con Android 7.0 si no tienes uno.
- Inicia el emulador.

### Ejecutar la aplicación

1. Asegúrate de que el emulador esté corriendo o el dispositivo esté conectado.
2. Haz clic en el botón **Run** (ícono ▶️ en la barra superior) en Android Studio.
3. Selecciona el dispositivo o emulador deseado.
4. La app se compilará e instalará automáticamente.

___

**Alumno:** Jose Manuel Vasquez Ramos  
**Asignatura:** Aplicaciones Móviles
