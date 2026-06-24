# Bitácora de Avances de Desarrollo - MindKeep

Este archivo registra el progreso histórico, las mejoras aplicadas sobre el código base inicial y la integración de nuevos módulos de la aplicación **MindKeep**.

---

## Hito 1: Evolución del Módulo de Acceso e Integración del Dashboard

### Línea de Tiempo del Desarrollo

#### 🔹 Fase Inicial (14 de Mayo de 2026)
* **Fundación del Proyecto:** Creación de la estructura base en Android Studio.
* **Diseño Primitivo del Login (`activity_main.xml`):** Implementación de la interfaz inicial con los componentes esenciales (`editTextText`, `editTextTextPassword`, `button`) y el flujo básico de validación para el nombre de usuario en `MainActivity.kt`.
* *Evidencia Histórica:* Almacenamiento de las capturas iniciales de esta fase en la carpeta de documentación para registrar el punto de partida del proyecto.

#### 🔹 Fase de Integración y Maduración (25 de Mayo de 2026)
* **Optimización de Seguridad en Acceso:** Refactorización de la lógica en `MainActivity.kt` para transformarla en una validación simétrica obligatoria (comprobando que ni el usuario ni la contraseña queden vacíos).
* **Desarrollo de Módulos de Soporte:** Diseño e implementación desde cero de las pantallas complementarias para el control de accesos:
    * **Registro (`activity_register.xml` / `RegisterActivity.kt`):** Formulario para la creación de nuevas cuentas.
    * **Recuperación (`activity_forgot_password.xml` / `ForgotPasswordActivity.kt`):** Interfaz para la restauración de credenciales.
* **Maquetación del Dashboard Principal (`activity_notes_dashboard.xml` / `NotesDashboardActivity.kt`):** Construcción de la interfaz de destino que albergará el listado dinámico (`RecyclerView`) y el botón flotante de acción (`FloatingActionButton`).

#### 🔹 Enrutamiento y Navegación del Sistema (31 de Mayo de 2026)
* Conexión total del flujo mediante componentes `Intent` desde el Login hacia el Registro y la Recuperación.
* Implementación de la directiva `finish()` en el puente hacia el Dashboard de Notas, destruyendo la actividad de Login en memoria para asegurar que el usuario no pueda regresar al formulario de acceso mediante el botón físico "Atrás" del dispositivo.
* Alta y registro formal de todas las actividades dentro del manifiesto global (`AndroidManifest.xml`).

---

## Hito 2: Motor de Persistencia Local y Módulo Maestro-Detalle

#### 🔹 Integración de SQLite y Tarjetas Dinámicas (03 y 04 de Junio de 2026)
* **Estructura de Base de Datos:** Creación de `DatabaseHelper.kt` para inicializar el contenedor local SQLite y la tabla de almacenamiento de notas.
* **Refactorización de Interfaz:** Migración de un volcado plano de texto a un contenedor estructurado basado en tarjetas (`CardView`) mediante el diseño de `item_note.xml`.
* **Despliegue de Datos:** Implementación de `NoteAdapter.kt` para enlazar el flujo del cursor de SQLite de forma dinámica con el `RecyclerView` en el Dashboard.

#### 🔹 Estabilización de la Actividad de Detalle (05 de Junio de 2026)
* **Arquitectura de Navegación Explícita:** Resolución de la referencia no encontrada en la navegación agregando el archivo lógico `NoteDetailActivity.kt` y su respectiva interfaz visual `activity_note_detail.xml`.
* **Transferencia Segura de Datos:** Inyección de la clave primaria mediante `intent.getIntExtra("NOTE_ID", -1)` para realizar consultas parametrizadas en la base de datos local y poblar la vista de detalle.
* **Registro en Manifiesto:** Declaración formal de la actividad con el atributo `exported="false"` para salvaguardar la ejecución interna de la pantalla.

---

## Hito 3: Sincronización Arquitectural, Alarmas Nativas y Mutación de Interfaces (Fase Final)

#### 🔹 Subsistema de Alertas y Notificaciones (22 de Junio de 2026)
* **Integración del Kernel de Android:** Implementación del componente `AlarmReceiver.kt` heredando de `BroadcastReceiver` para interceptar alarmas del sistema en segundo plano.
* **Canales de Notificación Nativos:** Configuración del controlador de alertas mediante `NotificationCompat.Builder` para el despacho de avisos dinámicos en tiempo real con prioridad adaptativa.
* **Persistencia Temporal:** Acoplamiento de la fecha y hora de la nota con el planificador local del dispositivo para activar los recordatorios de manera asíncrona.

#### 🔹 Unificación de SQLite y Flujo de Recuperación Dinámica (23 de Junio de 2026)
* **Resolución de Conflictos Sintácticos:** Consolidación y reestructuración del esquema relacional en `DatabaseHelper.kt` para unificar firmas de métodos y parámetros entre actividades, erradicando los errores de compilación (`Unresolved reference` / `No value passed for parameter`).
* **Mutación Guiada por Estados:** Refactorización total de `ForgotPasswordActivity.kt` introduciendo la bandera lógica `isUserVerified`. El formulario transmuta dinámicamente en tiempo de ejecución (cambiando hints, tipos de entrada y textos de botones) para permitir la sobreescritura de credenciales local sin salir de la misma pantalla.
* **Protocolo de Resiliencia de Datos:** Implementación de rutinas de borrado de almacenamiento físico y caché en el entorno de pruebas para purgar esquemas obsoletos y forzar el aprovisionamiento limpio de tablas relacionales.

### Entregables y Control de Evidencias Finales
* **Capturas de Evolución:** Se incorporan los registros gráficos de la interfaz mutando con éxito ("Ingresa tu nueva contraseña"), las alertas del sistema operando y la consola de Git sincronizada limpiamente.
* **Binario Ejecutable Definitivo:** `app-debug.apk` - Paquete binario compilado y firmado de forma local que unifica el control de accesos, el CRUD completo de notas de usuario, las alertas nativas y la persistencia relacional blindada en el almacenamiento del dispositivo.


