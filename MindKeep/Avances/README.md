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

#### 🔹 Estabilización de la Actividad de Detalle (05 de Junio de 2026 - Hoy)
* **Arquitectura de Navegación Explícita:** Resolución de la referencia no encontrada en la navegación agregando el archivo lógico `NoteDetailActivity.kt` y su respectiva interfaz visual `activity_note_detail.xml`.
* **Transferencia Segura de Datos:** Inyección de la clave primaria mediante `intent.getIntExtra("NOTE_ID", -1)` para realizar consultas parametrizadas en la base de datos local y poblar la vista de detalle.
* **Registro en Manifiesto:** Declaración formal de la actividad con el atributo `exported="false"` para salvaguardar la ejecución interna de la pantalla.

### Entregables y Control de Evidencias
* **Capturas de Evolución:** Se anexan a las evidencias previas los registros gráficos del Dashboard dinámico y la interfaz funcional de visualización de notas.
* **Binario Funcional Actualizado:** `MindKeep_Modulo_Acceso_V2.apk` - Paquete distribuible que unifica la persistencia de datos local y la navegación del módulo Maestro-Detalle completamente estabilizada.



