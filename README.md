# Programación 2 — UADE · 1Q 2026

Proyecto de cursada de **Programación 2** de la Universidad Argentina de la Empresa (UADE), primer cuatrimestre 2026.

El objetivo es implementar las principales **estructuras de datos** desde cero en Java, sin usar `java.util`, y aplicarlas en ejercicios prácticos interactivos.

---

## Estructura del proyecto

```
src/
├── application/        # Launcher principal y clase base Exercise
├── list/               # Lista (array y linked)
├── queue/              # Cola (array y linked)
├── stack/              # Pila (array y linked)
├── set/                # Conjunto (array y linked)
├── priorityqueue/      # Cola de prioridad (array y linked)
└── dictionary/         # Diccionario / mapa (array y linked)
```

Cada paquete sigue el mismo patrón:
- **Interfaz** (`SimpleXxx<E>`) — contrato de la estructura.
- **Implementación array** (`SimpleArrayXxx`) — respaldada por un arreglo interno.
- **Implementación linked** (`SimpleLinkedXxx`) — respaldada por nodos encadenados.
- **Ejercicio** (`XxxExercise`) — aplicación interactiva que usa la estructura.

---

## Cómo ejecutar

Requiere **Java 11+** y Eclipse (o cualquier IDE compatible con proyectos Eclipse Java).

1. Importar la carpeta como proyecto Eclipse existente.
2. Ejecutar `application.MainProgram` como Java Application.
3. Seleccionar un módulo en el menú y seguir las instrucciones por consola.

---

## Módulos disponibles

| Opción | Módulo | Descripción |
|--------|--------|-------------|
| 1 | Test Exercise | Sandbox de pruebas |
| 2 | List Exercise | Operaciones sobre listas (agregar, eliminar, buscar) |
| 3 | Queue Exercise | Operaciones sobre colas FIFO |
| 4 | Stack Exercise | Operaciones sobre pilas LIFO |
| 5 | Set Exercise | Operaciones sobre conjuntos (unión, intersección, diferencia) |
| 6 | Issue Tracker | Sistema de tickets con prioridad (Crítica / Alta / Media / Baja) |
| 7 | Inventory Management | Gestión de productos por SKU (alta, edición, eliminación, reporte) |

---

## Estructuras de datos implementadas

### SimpleList
`add`, `add(index)`, `remove(index)`, `remove(object)`, `get`, `set`, `contains`, `size`, `isEmpty`, `clear`

### SimpleQueue
`enqueue`, `dequeue`, `peek`, `size`, `isEmpty`, `clear`

### SimpleStack
`push`, `pop`, `peek`, `size`, `isEmpty`, `clear`

### SimpleSet
`add`, `remove`, `contains`, `size`, `isEmpty`, `clear`, `toArray`, `unionWith`, `intersectWith`, `differenceWith`

### SimplePriorityQueue
`enqueue(element, priority)`, `dequeue`, `peek`, `getHighestPriority`, `size`, `isEmpty`, `clear`

### SimpleDictionary
`put`, `remove`, `containsKey`, `get`, `keys`, `values`, `size`, `isEmpty`, `clear`
