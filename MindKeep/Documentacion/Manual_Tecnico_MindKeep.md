

Manual Técnico: Proyecto MindKeep
Estado del Proyecto: Fase de Desarrollo Inicial (Login y Estructura)
Fecha: 14 de Mayo, 2026
Desarrollador: Juan D. Hernández

1. Configuración del Entorno
Para asegurar la compatibilidad y el rendimiento, el proyecto utiliza las siguientes especificaciones:

IDE: Android Studio (Versión Ladybug o superior)

Lenguaje: Kotlin

SDK Mínimo: API 24 (Android 7.0 Nougat)

Control de Versiones: Git / GitHub

2. Estructura del Proyecto
Se ha implementado una jerarquía de carpetas para separar las responsabilidades del sistema:

Codigo: Contiene el proyecto de Android Studio con el código fuente en Kotlin.

Documentacion: Almacena este manual y la bitácora de cambios.

Recursos: Repositorio de activos gráficos, iconos y elementos multimedia.

3. Bitácora de Desarrollo (14 de Mayo)
Sesión de la Mañana: Inicio y Configuración
Creación: Proyecto base bajo el paquete com.crow.mindkeep.

Git: Configuración de la estructura de repositorio para el rastreo de archivos.

Gradle: Sincronización completa de dependencias y entorno de construcción.

Sesión de la Tarde: Diseño y Estándares UX
Depuración XML: Resolución de errores de restricciones (Constraints) y jerarquía visual.

Accesibilidad: Implementación de altura mínima de 48dp en elementos interactivos según estándares de Google.

Interfaz: Finalización del archivo activity_main.xml libre de errores de sintaxis.

4. Lógica de Control y Validación
Implementación funcional del módulo de acceso y gestión de datos de entrada.

Vinculación de Componentes
Uso de referencias entre la interfaz XML y el código Kotlin mediante identificadores únicos y variables inmutables para garantizar la estabilidad del código.

Gestión de Eventos y Usuario
Validación: Control de flujos condicionales para verificar campos obligatorios.

Feedback: Implementación de mensajes Toast para confirmación de interacción.

Optimización: Configuración de importación automática de librerías para mantener un código fuente limpio.

El módulo de acceso es funcional, valida entradas de texto y proporciona respuesta visual










