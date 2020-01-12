# PruebaTecnicaSdos
1. Introducción
El objetivo de la prueba es evaluar el nivel técnico y destreza frente al diseño e
implementación de una solución en movilidad.
2. Prueba
La aplicación hará uso de una lista de usuarios. Estos usuarios tendrán datos personales
básicos, código, etc. y lo más importante:
• Tipo de usuario: administrador o técnico.
• Habilidades de usuario: tipo de tareas que el usuario puede llevar a cabo a partir
de una lista de tareas.
La lista de usuarios y tareas podría ser codificada en base de datos local. Por ejemplo:
Tipo de tareas:
1. Reponedor de productos.
2. Cobrar.
3. Envolver.
4. Etcétera.
Rubén García, administrador.
Rafael Martín, técnico, tareas tipo 2.
Sarah López, técnico, las tareas 1 y 3.
2.1. Login
Login básico, ID de usuario y contraseña.
Una vez que un usuario inicia sesión se redireccionará a la pantalla de administrador o
técnico en función del tipo de usuario.
2.2. Administrador
El administrador puede dar de alta tareas a realizar por los técnicos:
• Descripción de la tarea (texto)
• Duración de la tarea.
• Tipo de tarea.
Después de crear una tarea, puede crear más tareas o cerrar la sesión.
Cuando se crea una nueva tarea se le asigna a un técnico que tenga la habilidad requerida,
y se le asignará al técnico con menos carga para ese día. Es decir, si varios técnicos pueden
hacer la tarea 2, se le asignará al que tenga menos horas en tareas para ese día.
2.3. Técnica
La pantalla debe mostrar las tareas asignadas para ese técnico.
El técnico debe ser capaz de marcar la tarea como completada y dicha tarea debe ser de
alguna manera diferenciada de las tareas pendientes.
2.4. Web Service
Visible por todos los usuarios de cualquier rol. Se debe realizar una petición al servicio web
que se facilita a continuación y mostrar los resultados en un listado (será necesario
mostrar al menos 4 atributos de cada elemento de la lista).
Tras la primera petición, los resultados se deben almacenar para poder ser consultados
más tarde sin conexión a Internet.
2.4.1. Datos web Service
• Tipo: GET
• Formato respuesta: JSON
• Endpoint: https://data.ct.gov/resource/hma6-9xbg.json
• Parámetros: category=Fruit, item=Peaches
