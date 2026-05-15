
Manual Técnico: Proyecto MindKeep
1. Configuración del Entorno de Desarrollo

IDE: Android Studio (Versión Ladybug o superior).

Lenguaje: Kotlin.

SDK Mínimo: API 24 (Android 7.0 Nougat).

Control de Versiones: Git / GitHub.


2. Estructura del Proyecto
Se ha implementado una jerarquía de carpetas profesional para separar las responsabilidades del proyecto:

/Codigo: Contiene el proyecto de Android Studio con el código fuente en Kotlin.

/Documentacion: Almacena este manual y la bitácora de cambios.

/Recursos: Repositorio de activos gráficos (logos, iconos, sonidos).


3. Bitácora de Desarrollo (14 de Mayo)

Sesión de Mañana: Inicio y Configuración

Creación del proyecto base bajo el paquete com.crow.mindkeep.

Configuración de Git para el rastreo automático de archivos nuevos.

Sincronización inicial de Gradle completada con éxito.

Sesión de Tarde: Diseño, Depuración y Estándares UX

Hito: Estabilización del Layout y Accesibilidad

Corrección de Errores Críticos: Se eliminaron los errores de "Missing Constraints" vinculando el título (textView2) al parent y organizando la cascada visual (Título -> Usuario -> Contraseña -> Botón -> Registro).

Depuración XML: Se corrigió el error de sintaxis en los atributos de margen y se eliminaron las restricciones absolutas de edición.

Implementación de Estándares de Accesibilidad (UX):

Configuración de android:minHeight="48dp" en todos los elementos interactivos para cumplir con los estándares de Google para pantallas táctiles.

Estado Actual: El archivo activity_main.xml se encuentra 100% libre de errores rojos y la interfaz está lista para la fase de programación.



Actualización Bitácora (Cierre de Jornada - 14 de Mayo)

4. Implementación de Lógica de Control y Validación

Vinculación de Componentes (View Binding Manual):

Se establecieron las referencias entre la interfaz XML y el código Kotlin utilizando findViewById.

Se declararon variables inmutables (val) para los objetos txtUsuario (EditText) y btnIngresar (Button).

Gestión de Eventos y UX:

Implementación de un setOnClickListener unificado para el botón de ingreso.

Lógica de Validación: Se añadió una estructura condicional if-else para verificar si el campo de usuario está vacío, evitando procesamientos nulos.

Retroalimentación Dinámica: Uso de la clase Toast con interpolación de strings para saludar al usuario de manera personalizada ("Hola $nombre").

Configuración Avanzada del IDE:

Se habilitó y verificó el Auto-Import en Android Studio para optimizar la gestión de librerías (Button, EditText, Toast) de forma automática.

Estado Final: El módulo de acceso es funcional, valida entradas de texto y proporciona respuesta visual al usuario. Código limpio y documentado.













