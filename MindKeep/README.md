# Proyecto Final – Aplicación Android: MindKeep

## Introducción
Este proyecto final corresponde al desarrollo de una aplicación móvil en Android, cuyo propósito es aplicar los conocimientos adquiridos durante el curso en un entorno práctico, realista y de alto rigor técnico. La propuesta se centra en la aplicación **MindKeep**, un sistema de Notas y Recordatorios diseñado específicamente para estudiantes y usuarios que requieren una herramienta ligera, intuitiva y ágil para organizar sus tareas diarias. El documento busca presentar la idea de manera clara, organizada y profesional, integrando la justificación de diseño con la implementación de código real, siguiendo los más altos estándares de la redacción académica en ingeniería de software.

## Contexto y justificación
En el ámbito académico contemporáneo, es común que los estudiantes olviden actividades, plazos o compromisos importantes debido a la alta carga de flujos informativos. Aunque existen aplicaciones avanzadas en el mercado, muchas de ellas incluyen funciones complejas y dependencias en la nube que saturan el rendimiento del dispositivo y comprometen la privacidad del usuario. La propuesta de este proyecto responde a esa necesidad latente al ofrecer una solución intuitiva, ligera y enfocada en lo esencial, que permita crear notas rápidas y configurar recordatorios básicos en un entorno estrictamente local. De esta manera, se consolida la práctica de programación nativa en Kotlin y el uso de Android Studio, utilizando un esquema de persistencia independiente que no requiere conectividad externa para salvaguardar los datos.

## Objetivos
* **Objetivo General:** Desarrollar una aplicación móvil nativa en Android que permita gestionar notas personales y recordatorios de manera simple, eficiente y segura en el dispositivo.
* **Objetivos Específicos:**
    * Implementar funciones CRUD básicas de creación, lectura, edición y eliminación de notas mediante persistencia local.
    * Configurar recordatorios activos integrando el subsistema de notificaciones locales de la barra de estado de Android.
    * Diseñar una interfaz clara, responsiva y amigable orientada a la experiencia de usuario en entornos académicos.
    * Documentar detalladamente la arquitectura y el historial de progreso en GitHub, aplicando buenas prácticas de control de versiones distribuidas.

## Plataforma y Requisitos Tecnológicos
* **Lenguaje:** Kotlin (Sintaxis limpia y programación orientada a objetos).
* **Entorno de desarrollo:** Android Studio (Configurado para compilación sobre API 34).
* **Ejecución y Persistencia:** Local, mediante el motor de base de datos relacional SQLite, sin integración en servicios de nube para simplificar el alcance y optimizar la privacidad.
* **Compatibilidad:** Emulador AVD (Android Virtual Device) y dispositivos físicos Android en modo de depuración.

## Estructura Física del Proyecto
A continuación se detalla la organización jerárquica del repositorio local y la distribución de los componentes lógicos, recursos de diseño y evidencias de compilación que conforman el sistema:

/home/crow/AndEjer/Universidad/Android/Proyecto_Final/MindKeep/
├── Avances/
│   ├── 1_Login_Azul.png
│   ├── 2_Registro_Azul.png
│   ├── 3_Recuperar_Contrasena.png
│   ├── 4_Dashboard_Mis_Notas.png
│   ├── 5_Dashboard_Mis_Notas_1.jpg
│   ├── 6_Dashboard_Mis_Notas_2.png
│   ├── 7_Manejo_Notas.jpg
│   ├── 8_Prueba_borrado.png
│   ├── 9_Prueba_borrado_1.png
│   ├── 10_Alarma.png
│   ├── 11_Recuperar_contrasena.png
│   ├── 12_Recuperar_contrasena_1.png
│   ├── app-debug.apk
│   ├── MindKeep_Modulo_Acceso_V1.apk
│   ├── MindKeep_Modulo_Acceso_V2.apk
│   └── README.md
├── Codigo/
│   └── app/
│       └── src/
│           └── main/
│               ├── AndroidManifest.xml
│               ├── java/com/example/mindkeep/
│               │   ├── AlarmReceiver.kt
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
│                   ├── activity_register.xml
│                   └── item_note.xml
├── Documentacion/
└── Recursos/

## Flujo de Diseño y Navegación Real del Sistema
1. **Pantalla de Login (Principal) (`MainActivity.kt` / `activity_main.xml`):** Actúa como el punto de acceso principal. Contiene los campos de usuario y contraseña, el botón de ingreso y el enlace hacia el registro. Si el usuario ingresa sus credenciales correctamente, pasa directo a la pantalla de notas. Si selecciona "Registro", se abre la pantalla de creación de cuenta. Tras el éxito, destruye el Login en memoria mediante `finish()` para evitar retrocesos inseguros.
2. **Pantalla de Registro (`RegisterActivity.kt` / `activity_register.xml`):** Formulario para la creación de nuevas cuentas que solicita usuario, contraseña y correo electrónico. Una vez creado el usuario, regresa automáticamente a la pantalla principal de Login para iniciar sesión.
3. **Pantalla de Recuperación (`ForgotPasswordActivity.kt` / `activity_forgot_password.xml`):** Interfaz adaptativa que valida el correo del usuario contra SQLite. Tras la confirmación, muta sus componentes gráficos en tiempo de ejecución para capturar la nueva contraseña local de forma directa.
4. **Pantalla de Notas o Dashboard (`NotesDashboardActivity.kt` / `activity_notes_dashboard.xml`):** Panel secundario principal que renderiza de manera eficiente la lista de notas existentes mediante un `RecyclerView` alimentado por el adaptador personalizado `NoteAdapter.kt`. Contiene un botón "+" para crear una nueva nota y las opciones de editar y eliminar en cada registro.
5. **Pantalla de Formulario (Crear/Editar nota) (`NoteFormActivity.kt` / `activity_note_form.xml`):** Interfaz de captura con los campos para título, contenido y fecha/hora del recordatorio. Al presionar el botón "Guardar", regresa automáticamente a la pantalla de Notas. Si se entra desde la acción "editar", el formulario carga los datos preexistentes de la nota seleccionada.
6. **Pantalla de Detalle (`NoteDetailActivity.kt` / `activity_note_detail.xml`):** Vista de lectura aislada que recibe el identificador único de la nota para poblar los campos de texto correspondientes.

## Funcionalidad e Implementación Técnica
La robustez de la aplicación radica en el desacoplamiento de sus funciones de persistencia y el control estricto de eventos del sistema operativo:
* **Crear Nota:** Inserción segura de registros en la tabla local de SQLite a través de controladores parametrizados.
* **Editar Nota Existente:** Modificación reactiva del contenido indexado mediante consultas basadas en la clave primaria.
* **Eliminar Nota:** Remoción física del registro en base de datos y actualización dinámica del adaptador gráfico.
* **Configurar Recordatorio con Notificación:** Integración del componente `AlarmReceiver.kt` vinculado al gestor de alarmas nativo, lanzando una alerta emergente en la barra de estado cuando se cumple la fecha y hora seleccionada.

## Conclusión
El proyecto final integra con éxito los aprendizajes de App Inventor, GitHub y Android Studio, consolidando de manera armónica la teoría y la práctica en un desarrollo alcanzable dentro de las metas del curso. La aplicación propuesta tiene un impacto directo en la vida académica de los estudiantes al ofrecer una solución práctica para la organización personal y el resguardo seguro de datos locales. Refuerza competencias críticas en programación orientada a objetos con Kotlin, diseño de interfaces gráficas adaptativas y gestión de proyectos bajo el principio de Defensa en Profundidad, promoviendo la transparencia y la responsabilidad ética en la construcción colectiva de software.


