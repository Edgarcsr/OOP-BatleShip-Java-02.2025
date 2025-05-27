### Flujo de cada clase y sus relaciones

#### **1. `ShipType` (Enum)**
- **Propósito**: Define los tipos de barcos y su tamaño.
- **Relaciones**:
  - Es utilizado por la clase `Ship` para determinar el tipo y tamaño del barco.
- **Flujo**:
  - Cuando se crea un barco, se asigna un `ShipType` para definir su tamaño.

---

#### **2. `CellStatus` (Enum)**
- **Propósito**: Representa el estado de una celda (`EMPTY`, `SHIP`, `MISS`, `HIT`).
- **Relaciones**:
  - Es utilizado por la clase `Cell` para gestionar su estado.
- **Flujo**:
  - El estado de la celda cambia según las acciones del juego (colocar un barco, recibir un disparo, etc.).

---

#### **3. `Coordinate`**
- **Propósito**: Representa una posición en el tablero mediante filas y columnas.
- **Relaciones**:
  - Es utilizado por `Ship` para definir las coordenadas que ocupa.
  - Es pasado como argumento en métodos de `Board` y `Cell` para identificar posiciones.
- **Flujo**:
  - Se utiliza para identificar y comparar posiciones en el tablero.

---

#### **4. `Ship`**
- **Propósito**: Representa un barco con un tipo, coordenadas ocupadas y coordenadas impactadas.
- **Relaciones**:
  - Contiene una lista de `Coordinate` para las posiciones que ocupa.
  - Es referenciado por `Cell` para indicar si una celda contiene un barco.
- **Flujo**:
  - Cuando un barco es colocado en el tablero, se asignan sus coordenadas.
  - Si una celda que contiene el barco es impactada, el barco registra el impacto en la coordenada correspondiente.
  - Determina si está hundido al comparar las coordenadas impactadas con las ocupadas.

---

#### **5. `Cell`**
- **Propósito**: Representa una celda del tablero, que puede contener un barco y tener un estado.
- **Relaciones**:
  - Puede contener una referencia a un `Ship`.
  - Utiliza `CellStatus` para gestionar su estado.
- **Flujo**:
  - Cuando se coloca un barco, la celda cambia su estado a `SHIP`.
  - Si recibe un disparo, llama al método del barco para registrar el impacto y actualiza su estado (`HIT` o `MISS`).

---

#### **6. `Board`**
- **Propósito**: Representa el tablero del jugador, compuesto por una matriz de celdas y una lista de barcos.
- **Relaciones**:
  - Contiene una matriz de `Cell` y una lista de `Ship`.
  - Utiliza `Coordinate` para identificar posiciones.
- **Flujo**:
  - Gestiona la colocación de barcos en celdas específicas.
  - Procesa disparos al identificar la celda correspondiente y delegar la acción.
  - Verifica si todos los barcos han sido hundidos.

---

#### **7. `Player`**
- **Propósito**: Representa a un jugador con un nombre y un tablero.
- **Relaciones**:
  - Posee un `Board` para gestionar su tablero.
  - Interactúa con otro `Player` al disparar a su tablero.
- **Flujo**:
  - Coloca sus barcos en su tablero.
  - Dispara a las coordenadas del tablero del oponente.
  - Verifica si ha perdido al comprobar si todos sus barcos están hundidos.

---

#### **8. `Game`**
- **Propósito**: Representa la lógica principal del juego, incluyendo jugadores y turnos.
- **Relaciones**:
  - Gestiona dos instancias de `Player`.
  - Coordina las interacciones entre los tableros de los jugadores.
- **Flujo**:
  - Configura el juego inicial (colocación de barcos).
  - Alterna turnos entre los jugadores.
  - Determina si el juego ha terminado y declara un ganador.

---

#### **9. `Main`**
- **Propósito**: Contiene el punto de entrada del programa.
- **Relaciones**:
  - Crea una instancia de `Game` y la inicia.
- **Flujo**:
  - Inicia el juego y gestiona la interacción con el usuario.

---

### Resumen del flujo general
1. **Inicio del juego**: `Main` crea una instancia de `Game` y la inicia.
2. **Configuración**: `Game` configura los tableros de los jugadores (`Player`) y coloca los barcos (`Ship`) en las celdas (`Cell`) del tablero (`Board`).
3. **Turnos**: Los jugadores alternan turnos disparando a las coordenadas del tablero del oponente.
4. **Impactos**: El tablero procesa el disparo, identifica la celda correspondiente y actualiza su estado. Si hay un barco, este registra el impacto.
5. **Fin del juego**: `Game` verifica si todos los barcos de un jugador están hundidos y declara un ganador.
