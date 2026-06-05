# MindKeep - Módulo de Acceso y Gestión de Notas
## Repositorio Académico de Desarrollo Móvil en Android

Este proyecto comprende el diseño, estructuración e implementación de **MindKeep**, una aplicación nativa para el sistema operativo Android enfocada en la gestión eficiente de anotaciones personales. El desarrollo se fundamenta en una arquitectura modular, garantizando la scalabilidad del código, la separación de responsabilidades y la integridad en la persistencia local de la información.

---

## 1. Arquitectura de Directorios del Proyecto

El espacio de trabajo se encuentra organizado bajo una estructura jerárquica estricta que separa los entregables académicos, los artefactos de compilación y el código fuente del sistema:

* **`Avances/`**: Contiene la documentación visual del sistema, incluyendo capturas de pantalla de las interfaces de usuario actualizadas (`Login`, `Registro`, `Recuperación de Contraseña`, `Dashboard` y `Detalle de Nota`) junto con el paquete ejecutable distribuible de la fase actual.
* **`Codigo/`**: Directorio raíz del entorno de desarrollo en Android Studio. Alberga la configuración de Gradle, el manifiesto de la aplicación, las clases de lógica de negocio en Kotlin y las plantillas de diseño de interfaz en formato XML.
* **`Documentacion/`**: Espacio reservado para los diagramas de arquitectura, especificaciones de requerimientos y guías metodológicas del proyecto.
* **`Recursos/`**: Repositorio de recursos multimedia, vectores e íconos estáticos compartidos a lo largo de las fases de diseño.

```text
/home/crow/AndEjer/Universidad/Android/Proyecto_Final/MindKeep/
├── Avances/
│   ├── 1_Login_Azul.png
│   ├── 2_Registro_Azul.png
│   ├── 3_Recuperar_Contrasena.png
│   ├── 4_Dashboard_Mis_Notas.png
│   ├── 5_Dashboard_Mis_Notas_1.jpg
│   ├── 6_Dashboard_Mis_Notas_2.png
│   ├── 7_Manejo_Notas.jpg
│   └── MindKeep_Modulo_Acceso_V2.apk
├── Codigo/
│   └── app/
│       └── src/
│           └── main/
│               ├── AndroidManifest.xml
│               ├── java/com/example/mindkeep/
│               │   ├── DatabaseHelper.kt
│               │   ├── ForgotPasswordActivity.kt
│               │   ├── MainActivity.kt
│               │   ├── NoteAdapter.kt
│               │   ├── NoteDetailActivity.kt
│               │   ├── NoteFormActivity.kt
│               │   ├── NotesDashboardActivity.kt
│               │   └── RegisterActivity.kt
│               └── res/layout/
│                   ├── activity_forgot_password.xml
│                   ├── activity_main.xml
│                   ├── activity_note_detail.xml
│                   ├── activity_note_form.xml
│                   ├── activity_notes_dashboard.xml
│                   └── item_note.xml
├── Documentacion/
└── Recursos/



