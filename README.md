# Censo2021
Aplicación móvil para Android desarrollada en el marco del bootcamp Android Kotlin que lleve a cabo en el año 2021. 
Esta aplicación está destinada a llevar un registro y gestionar la información de las personas censadas en el marco de un censo nacional. La misma cuenta con las siguientes características:
- Al registrar las personas se toman los datos personales, la ocupación y el ingreso económico mensual.
- La información de las personas se almacena en una base de datos local en el teléfono.
- En la pantalla principal se muestran todas la personas censadas donde para ver la información de alguna en particular se pulsa sobre el recuadro del nombre de esa persona.
- Como para tener algún reporte para fines estadísticos la lista de personas de la pantalla principal se puede filtrar de las siguientes maneras:
  - Lista de todas las personas, mostrando toda su información, ordenadas de forma ascendente según su apellido.
  - Lista de todas las personas mayores de 18 años que están desocupadas.
  - Lista de todas las personas que están por debajo de la línea de pobreza. Se considera pobre a una persona cuyos ingresos son inferiores a cinco mil pesos mensuales.
- También con fines estadísticos, sobre la lista de personas de la pantalla principal se indica la cantidad de mujeres y cantidad de hombres que se encuentrar en esa lista.

Este sistema se desarrollo utilizando las siguientes tecnologías y herramientas:
- Herramientas de desarrollo: Android Studio
- Lenguaje utilizado: Kotlin
- Arquitectura: MVVM
- Motor de base de datos: SQLite
- UnitTest: Robolectric
- InstrumentedTest: Espresso
