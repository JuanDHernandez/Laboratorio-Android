# Proyecto Final – Aplicación Android: MindKeep

## Descripción del proyecto
Este proyecto final corresponde al desarrollo de una aplicación móvil nativa en Android, cuyo propósito es aplicar los conocimientos técnicos adquiridos durante el curso en un entorno práctico, realista y de alto rigor metodológico. La plataforma, denominada MindKeep, se concibe como un sistema integrado de Notas y Recordatorios diseñado específicamente para estudiantes y usuarios que requieren una herramienta ligera, intuitiva y de alto rendimiento para organizar sus flujos de tareas diarias. El documento unifica la justificación conceptual con la implementación física del software, asegurando la consistencia entre los requisitos lógicos y el código real alojado en el repositorio.

## Exposición del problema
En el escenario académico y profesional contemporáneo, los usuarios se enfrentan a una saturación constante de información, lo que provoca la pérdida de plazos, el olvido de compromisos críticos y una disminución en la productividad. Aunque el mercado ofrece soluciones comerciales complejas, la gran mayoría de estas herramientas introducen dependencias severas de conectividad a la nube, interfaces saturadas de publicidad y un consumo excesivo de recursos de hardware que compromete el rendimiento del terminal móvil. Adicionalmente, la centralización de notas personales en servidores externos expone la privacidad del estudiante. Esta problemática plantea la necesidad imperativa de desarrollar una alternativa de software ágil, ligera y autónoma, que opere bajo un esquema de persistencia estrictamente local para garantizar la disponibilidad inmediata y la protección de los datos.

## Plataforma
* Lenguaje de Programación: Kotlin (Sintaxis moderna y tipado seguro).
* Entorno de Desarrollo (IDE): Android Studio (Configurado para compilación nativa bajo API 34).
* Motor de Persistencia: SQLite de forma embebida, garantizando transacciones atómicas y almacenamiento relacional sin consumo de datos ni dependencias de red.
* Entorno de Pruebas: Emulador AVD (Android Virtual Device) y terminales físicos en modo de depuración USB.

## Interfaz de usuario e interfaz de administrador
* Interfaz de Usuario (Client-Side): Diseñada de forma adaptativa y limpia a través de layouts XML, utilizando componentes visuales modernos como CardView y RecyclerView (gestionados dinámicamente por NoteAdapter.kt) para renderizar las tarjetas de notas. El flujo inicia en la pantalla de acceso (MainActivity.kt), permitiendo la navegación fluida hacia los formularios de registro, recuperación de credenciales modificables en tiempo de ejecución, panel principal o Dashboard (NotesDashboardActivity.kt), y la vista detallada de los registros.
* Interfaz de Administrador (System Administration): Dado el enfoque de arquitectura local y de privacidad de la aplicación, el rol de administración no reside en un panel web externo expuesto a vectores de ataque, sino que se encuentra integrado de manera monolítica dentro de la lógica del sistema a través de la clase controladora DatabaseHelper.kt. Esta estructura otorga al usuario el control absoluto y local del entorno, actuando como el administrador directo de su propio diccionario de datos mediante operaciones CRUD securizadas que limpian e inicializan las tablas de usuarios y notas en el almacenamiento aislado de la aplicación.

## Funcionalidad
La solidez operativa del sistema se sustenta en el desacoplamiento de sus funciones lógicas y la gestión de eventos del sistema operativo:
* Autenticación Completa: Módulo seguro de Login, Registro de nuevas cuentas locales y Recuperación reactiva de contraseña con validación de correo directo en SQLite.
* Gestión de Notas (CRUD): Inserción, lectura analítica, edición de registros preexistentes y remoción física de notas con actualización dinámica de la interfaz gráfica.
* Subsistema de Alertas Activas: Integración del componente AlarmReceiver.kt (derivado de BroadcastReceiver) que intercepta los eventos temporales del reloj de Android para despachar notificaciones emergentes en la barra de estado en tiempo real, incluso si la aplicación no está en primer plano.

## Diseño (wireframes o esquemas de página)
La arquitectura de pantallas mantiene una correlación directa entre las plantillas de maquetación XML y las clases lógicas de Kotlin, estructuradas de la siguiente manera:
1. Acceso Principal: activity_main.xml conectado a MainActivity.kt.
2. Creamiento de Cuentas: activity_register.xml conectado a RegisterActivity.kt.
3. Restablecimiento: activity_forgot_password.xml conectado a ForgotPasswordActivity.kt.
4. Panel General (Dashboard): activity_notes_dashboard.xml y tarjetas dinámicas item_note.xml, gestionados por NotesDashboardActivity.kt.
5. Formulario de Captura: activity_note_form.xml conectado a NoteFormActivity.kt.
6. Lectura y Detalle: activity_note_detail.xml conectado a NoteDetailActivity.kt.

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
La culminación de MindKeep demuestra que es viable integrar metodologías de diseño visual y desarrollo nativo orientado a objetos bajo estándares académicos rigorosos. La adopción de un modelo relacional local y el control estricto del ciclo de vida de las actividades resuelven de forma contundente la problemática expuesta, sentando las bases para proyectos a gran escala fundamentados en el principio de Defensa en Profundidad. La transparencia en la documentación de este repositorio refleja un compromiso ético ineludible con la calidad del software, la responsabilidad técnica y la libre construcción colectiva del conocimiento en beneficio del progreso de la ingeniería de sistemas.



