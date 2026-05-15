

Proyecto Final – Aplicación Android



Introducción



Este proyecto final corresponde al desarrollo de una aplicación móvil en Android, cuyo propósito es aplicar los conocimientos adquiridos durante el curso en un entorno práctico y realista.

La propuesta se centra en una aplicación sencilla de Notas y Recordatorios, diseñada para estudiantes y usuarios que requieren una herramienta ligera para organizar sus tareas diarias.

El documento busca presentar la idea de manera clara, organizada y profesional, siguiendo estándares de redacción académica.



Contexto y justificación



En el ámbito académico, es común que los estudiantes olviden actividades, plazos o compromisos importantes, aunque existen aplicaciones avanzadas en el mercado, muchas de ellas incluyen funciones complejas que no siempre son necesarias.

La propuesta de este proyecto responde a esa necesidad de ofrecer una aplicación intuitiva, ligera y enfocada en lo esencial, que permita crear notas rápidas y configurar recordatorios básicos.

De esta manera, se busca reforzar la práctica de programación en Kotlin y el uso de Android Studio, integrando además GitHub como herramienta de documentación y control de versiones.



Objetivos



\- General 

   Desarrollar una aplicación móvil en Android que permita gestionar notas y recordatorios de manera simple y eficiente.



\- Específicos

   Implementar funciones básicas de creación, edición y eliminación de notas.
   Configurar recordatorios con notificaciones locales.
   Diseñar una interfaz clara y amigable para el usuario.
   Documentar el proceso en GitHub, aplicando buenas prácticas de control de versiones.



Plataforma



\- Lenguaje: Kotlin

\- Entorno de desarrollo: Android Studio

\- Ejecución: Local, sin integración en la nube, para simplificar el alcance.

\- Compatibilidad: Emulador AVD y dispositivos físicos Android.



Flujo de Diseño y Navegación



1\. Pantalla de Login (principal)



    Campo de usuario.
    Campo de contraseña.
    Botón “Login”.
    Botón “Registro” (para nuevos usuarios).
    Si el usuario ingresa sus credenciales correctamente, pasa directo a la pantalla de notas.
    Si selecciona “Registro”, se abre la pantalla de creación de cuenta.



2\. Pantalla de Registro:



    Campos: usuario, contraseña, correo electrónico.
    Botón “Registrar”.
    Una vez creado el usuario, regresa automáticamente a la pantalla principal de Login para iniciar sesión.



3\. Pantalla de Notas (secundaria):



    Lista de notas existentes.
    Botón “+” para crear nueva nota.
    Opciones de editar y eliminar en cada nota.
    Si se selecciona “crear” o “editar”, se abre el formulario correspondiente.



4\. Pantalla de Formulario (crear/editar nota):



    Campos: título, contenido, fecha/hora de recordatorio.
    Botón “Guardar”.
    Al guardar, regresa automáticamente a la pantalla de Notas.
    Si se entra desde “editar”, el formulario carga la nota seleccionada.



5\. Notificaciones:



    Alerta emergente en la barra de estado cuando se cumple un recordatorio.





Funcionalidad



\- Crear nota.
\- Editar nota existente.
\- Eliminar nota.
\- Configurar recordatorio con notificación.





Conclusión



El proyecto final integra los aprendizajes de App Inventor, GitHub y Android Studio, consolidando teoría y práctica en un desarrollo alcanzable del desarrollo del curso.

La aplicación propuesta tiene un impacto directo en la vida académica de los estudiantes, al ofrecer una solución práctica para la organización personal.

Refuerza competencias en programación con Kotlin, diseño de interfaces gráficas y gestión de proyectos con control de versiones.











