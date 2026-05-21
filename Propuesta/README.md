

# MindKeep — Android Local

MindKeep es una aplicación nativa de Android diseñada para la gestión eficiente y segura de notas personales, recordatorios y tareas académicas de manera **100% local**. Este repositorio documenta la fase de diseño UI/UX y la arquitectura de navegación inicial mediante Wireframes de alta fidelidad.

---

## Importancia de los Wireframes en el Desarrollo de Software

El desarrollo de software moderno exige que la experiencia de usuario (UX) y la interfaz (UI) se definan firmemente antes de la escritura del código fuente. La integración de wireframes en este proyecto responde a tres pilares fundamentales:

1. **Reducción de Costos de Desarrollo:** Modificar la lógica de navegación o el flujo de componentes visuales directamente en los archivos XML de Android o en composables de Jetpack Compose es costoso y propenso a errores. Los wireframes permiten iterar la interfaz en minutos sin tocar la base de código.
2. **Validación de la Arquitectura de Información:** Permiten estructurar jerárquicamente los datos que el usuario necesita ver en cada pantalla (títulos, fechas, estados de alarmas), asegurando que la interfaz no esté saturada y sea intuitiva.
3. **Alineación con Requerimientos Técnicos:** Actúan como el puente entre el diseño visual y el modelo de datos (Entity/Room Database), definiendo qué componentes interactivos dispararán eventos en el Backend o ciclo de vida de la app.

---

## Utilidad de los Wireframes Específicamente en MindKeep

Para **MindKeep**, los wireframes no fueron solo bocetos, sino el mapa de ruta técnico para implementar las siguientes funcionalidades críticas:

1. Flujo de Autenticación y Seguridad Local
* **Pantallas:** *Login*, *Crear Cuenta* y *Recuperar Contraseña*.
* **Utilidad:** Definieron de forma precisa los campos necesarios para la persistencia local de credenciales (Nombres, Apellidos, Correo, Contraseña). Al ser un sistema estrictamente local, el wireframe de *Recuperar Contraseña* ayudó a diseñar la lógica de validación mediante preguntas/verificación interna, evitando dependencias de servicios en la nube.

2. Gestión de Estado en Tiempo Real (State Management)
* **Pantalla:** *Mis Notas*.
* **Utilidad:** Permitió estructurar el contenedor principal (RecyclerView / LazyColumn) donde se listarán las notas de manera dinámica. Ayudó a planificar visualmente la disposición geométrica de la fecha de creación y el botón flotante (FAB) para agregar nuevas entradas, garantizando una accesibilidad óptima a una sola mano.

3. Persistencia de Datos y Alarmas
* **Pantalla:** *Ver Nota*.
* **Utilidad:** Esta vista es el núcleo operativo. El wireframe facilitó la separación de la UI para tres flujos lógicos clave del ciclo de vida de Android:
  * Operaciones **CRUD** directas mediante los accesos rápidos de `[Editar]` y `[Borrar]`.
  * Visualización limpia del cuerpo de la nota.
  * El estado del componente *Switch* para activar el **Recordatorio / Alarma**, permitiendo programar el comportamiento del *AlarmManager* y los *BroadcastReceivers* de Android de fondo sin interferir con la lectura.

---

## Flujo de Navegación Diseñado

El prototipo sigue una arquitectura de navegación lineal y segura:
`Login` ➔ `Mis Notas (Dashboard)` ➔ `Ver/Editar Nota`
*(Con rutas secundarias de escape hacia la creación de cuentas o recuperación local de credenciales).*


