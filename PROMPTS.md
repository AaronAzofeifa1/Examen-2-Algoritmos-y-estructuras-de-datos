# Registro de prompts

* Integrantes: Aarón Azofeifa Bermúdez, Santiago Acuña Fernández
* Herramienta(s) de IA utilizada(s): ChatGPT

## Prompt 1

F1. Directorio de paradas



Respuesta: Un árbol n-ario de tres niveles.



1\. ¿Qué me piden? Agregar paradas a una zona. Listar las paradas de una zona. Sumar los abordajes diarios de una zona y retornar el total. Sumar los abordajes diarios de todas las zonas para retornar el total de toda la ciudad.

2\. Restricciones: La jerarquía es fija (3 niveles).

3\. Opciones: Árbol n-ario cumple con todas las acciones que pide el feature.

4\. Complejidad temporal: O(tamaño del subárbol) para sumar un subárbol.

5\. Alternativa descartada: Descartamos la lista plana porque haría más costoso agregar un elemento (una parada a una zona).



F2. Reporte de pasajeros



Estructura que se usará: Arreglo ordenado.



Complejidad temporal: O(log n)



Alternativa descartada: Descartamos el BST simple porque su complejidad varía dependiendo de las entradas y se necesita que el tiempo de ejecución esté garantizado.



F3. Planificador de viajes



Estructura que se usará: Grafo dirigido y con pesos y el algoritmo de Dijkstra para encontrar el camino más corto.



Complejidad temporal: O(V + E)



Alternativa descartada: Descartamos Bellman-Ford porque utiliza pesos negativos.



Requerimientos:



F1



Funcional: el app debe de poder:



\- Agregar una parada a una zona.

\- Listar las paradas de una zona.

\- Obtener el total de abordajes diarios de una zona.

\- Obtener el total de abordajes diarios de la ciudad.



No funcional:



\- El nombre de la ciudad es Puerto Celeste.



F2



Funcional:



\- Listar las paradas en orden descendente.

\- En caso de empate, lista por código de la parada.

\- Paradas deben tener un código de A a Z.

\- Reporte de abordajes diarios corre en un servidor compartido.



No funcional:



\- Ciudad puede tener 1200 paradas o más.



F3



Funcional:



\- El pasajero debe de poder elegir una parada de salida y un destino.

\- La app debe decir qué buses tomar (y si debe caminar para llegar a la parada) lo antes posible.

\- El app debe decir cuál bus tomar.

\- El app debe decir cuánto va a tardar el usuario en llegar a la parada.

\- El bus solo tienen una dirección (solo circula por su carril).

\- El usuario puede caminar en ambos sentidos.



No funcional:



\- Cada zona tiene un nombre.

\- Son 4 buses.







Desarrolle la solución con un enfoque de TDD (Test-Driven

Development) No sugiera mejoras ni cambios a los requerimientos.



Para la implementación use Java pero con estas restricciones:

Permitido: arreglos (por ejemplo int\[] o Parada\[]) y String.

Prohibido en Java: cualquier colección de java.util (ArrayList, LinkedList, HashMap,

HashSet, TreeMap, TreeSet, PriorityQueue, Stack, ArrayDeque, entre otras), así

como Arrays.sort, Collections.sort, List.sort y sorted() de Streams.

También están prohibidas bibliotecas que calculen caminos más cortos o que implementen grafos.



Implementa pruebas unitarias que permitan garantizar que los requerimientos propuestos se puedan comprobar con la solución propuesta; debe de abarcar pruebas unitarias basadas en el tipo de validación o resultado esperado, no solo falso/positivo. La biblioteca de implementación de las pruebas unitarias es a libre criterio, se recomienda utilizar JUnit para Java.



Finalmente genera un archivo README en donde se deberá de incluir las siguientes secciones:

o         Nombre del Examen-Proyecto

o         Nombre de los autores.

o         Requerimientos del sistema, se debe de incluir todos los requerimientos, se permite excluir requerimientos, más no se pueden modificar los ya propuestos en la parte 1.

o         Instrucciones de instalación y ejecución.

▪️          Se debe de incluir una descripción de la jerarquía de carpetas del proyecto y que hace cada modulo/clase.

o         Requerimientos desarrollados por los estudiantes.

o         Diagramas de flujo, componentes, y Diagrama correspondiente al grafo que emplea el sistema.

Pruebas unitarias con JUnit 5 (org.junit.jupiter.api), no JUnit 4. Java 17. Los nombres de las clases de prueba terminan en Test.

Los diagramas debe de ser en mermaid

