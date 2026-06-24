# Proyecto Final – Aplicación Android: MindKeep

## Descripción del proyecto
Este proyecto final corresponde al desarrollo de una aplicación móvil nativa en Android, cuyo propósito es aplicar los conocimientos técnicos adquiridos durante el curso en un entorno práctico, realista y de alto rigor metodológico. La plataforma, denominada MindKeep, se concibe como un sistema integrado de Notas y Recordatorios diseñado para estudiantes y usuarios que requieren una herramienta ligera, intuitiva y de alto rendimiento para organizar sus tareas diarias. El documento busca presentar la propuesta de manera clara y profesional, vinculando la justificación de diseño con el código real alojado en el repositorio.

## Exposición del problema
En el escenario académico y profesional contemporáneo, los usuarios se enfrentan a una saturación constante de información, lo que provoca el olvido de compromisos críticos y una disminución en la productividad. Aunque el mercado ofrece soluciones avanzadas, la gran mayoría de estas herramientas introducen funciones complejas que no siempre son necesarias, dependencias severas de conectividad a la nube y un consumo excesivo de recursos de hardware. Esta problemática plantea la necesidad de desarrollar una alternativa de software ágil, ligera y autónoma, que opere bajo un esquema de persistencia local para garantizar la disponibilidad inmediata y la protección de los datos personales.

## Plataforma
* Lenguaje: Kotlin (Programación orientada a objetos y sintaxis segura).
* Entorno de desarrollo: Android Studio (Configurado para compilación nativa).
* Ejecución y Persistencia: Local mediante el motor SQLite, sin integración en servicios de nube para simplificar el alcance y optimizar la privacidad del entorno.
* Compatibilidad: Emulador AVD (Android Virtual Device) y terminales físicos en modo de depuración.

## Interfaz de usuario e interfaz de administrador
* Interfaz de Usuario (Client-Side): Diseñada de forma adaptativa a través de layouts .xml, utilizando componentes lógicos para renderizar las interfaces y facilitar la navegación fluida. El flujo inicia en la pantalla de acceso (MainActivity.kt), permitiendo el enrutamiento hacia los formularios de registro, recuperación de credenciales, panel general (NotesDashboardActivity.kt) y la vista detallada de las notas.
* Interfaz de Administrador (System Administration): Dado el enfoque de arquitectura local de la aplicación, el rol de administración se encuentra integrado de manera segura dentro de los controladores lógicos del sistema a través de la clase DatabaseHelper.kt. Esta estructura otorga al usuario el control directo de su propia base de datos, actuando como el administrador local de su diccionario de datos mediante operaciones de actualización y mantenimiento interno en el almacenamiento aislado de la aplicación.

## Flujo de Diseño y Navegación
1. Pantalla de Login (Principal) (MainActivity.kt / activity_main.xml):
    * Contiene el campo de usuario, campo de contraseña, botón Login y botón Registro.
    * Si el usuario ingresa sus credenciales correctamente, pasa directo a la pantalla de notas.
    * Si selecciona Registro, se abre la pantalla de creación de cuenta.
2. Pantalla de Registro (RegisterActivity.kt / activity_register.xml):
    * Incluye los campos de usuario, contraseña y correo electrónico junto al botón Registrar.
    * Una vez creado el usuario, regresa automáticamente a la pantalla principal de MainActivity.kt para iniciar sesión.
3. Pantalla de Notas o Dashboard (NotesDashboardActivity.kt / activity_notes_dashboard.xml):
    * Renderiza la lista de notas existentes mediante un componente dinámico alimentado por el adaptador NoteAdapter.kt.
    * Proporciona un botón "+" para crear una nueva nota y las opciones de editar y eliminar en cada registro.
4. Pantalla de Formulario (Crear/Editar nota) (NoteFormActivity.kt / activity_note_form.xml):
    * Campos de captura para título, contenido y fecha/hora del recordatorio.
    * Al presionar el botón Guardar, regresa automáticamente a la pantalla de Notas.
    * Si se entra desde la acción "editar", el formulario carga los datos preexistentes de la nota seleccionada.
5. Subsistema de Notificaciones (AlarmReceiver.kt):
    * Despacha una alerta emergente en la barra de estado del sistema operativo cuando se cumple el tiempo asignado a un recordatorio.

## Funcionalidad
La solidez operativa del sistema se sustenta en las siguientes capacidades lógicas y de persistencia local:
* Crear nota: Inserción de registros en las tablas locales de la base de datos.
* Editar nota existente: Actualización selectiva del contenido indexado en el almacenamiento.
* Eliminar nota: Remoción física del registro y refresco dinámico de la interfaz gráfica.
* Configurar recordatorio con notificación: Enrutamiento de eventos temporales para lanzar alertas en la barra de estado.

## Estructura Física del Proyecto
A continuación se detalla la organización jerárquica del repositorio local y la distribución de los componentes lógicos, recursos de diseño y evidencias de compilación que conforman el sistema:


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
```

## Conclusión
El proyecto final integra con éxito los aprendizajes de App Inventor, GitHub y Android Studio, consolidando la teoría y la práctica en un desarrollo alcanzable dentro de las metas del curso. La aplicación propuesta tiene un impacto directo en la vida académica de los estudiantes al ofrecer una solución práctica para la organización personal y el resguardo de datos. Refuerza competencias críticas en programación nativa con Kotlin, diseño de interfaces gráficas .xml y gestión de proyectos bajo el principio de Defensa en Profundidad, promoviendo la transparencia y la responsabilidad ética en la construcción colectiva de software.


