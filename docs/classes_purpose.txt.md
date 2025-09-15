### Fluxo de cada classe e suas relações

*1. ShipType (Enum)*

* *Propósito:* Define os tipos de navios e seu tamanho.
* *Relações:*

  * É utilizado pela classe *Ship* para determinar o tipo e o tamanho do navio.
* *Fluxo:*

  * Quando um navio é criado, é atribuído um *ShipType* para definir seu tamanho.

---

*2. CellStatus (Enum)*

* *Propósito:* Representa o estado de uma célula (*EMPTY, **SHIP, **MISS, **HIT*).
* *Relações:*

  * É utilizado pela classe *Cell* para gerenciar seu estado.
* *Fluxo:*

  * O estado da célula muda conforme as ações do jogo (colocar um navio, receber um disparo, etc.).

---

*3. Coordinate*

* *Propósito:* Representa uma posição no tabuleiro através de linhas e colunas.
* *Relações:*

  * É utilizado por *Ship* para definir as coordenadas que ocupa.
  * É passado como argumento em métodos de *Board* e *Cell* para identificar posições.
* *Fluxo:*

  * Usado para identificar e comparar posições no tabuleiro.

---

*4. Ship*

* *Propósito:* Representa um navio com um tipo, coordenadas ocupadas e coordenadas atingidas.
* *Relações:*

  * Contém uma lista de *Coordinate* para as posições que ocupa.
  * É referenciado por *Cell* para indicar se uma célula contém um navio.
* *Fluxo:*

  * Quando o navio é colocado no tabuleiro, são atribuídas suas coordenadas.
  * Se uma célula que contém o navio for atingida, o navio registra o impacto na coordenada correspondente.
  * Determina se está afundado comparando as coordenadas atingidas com as ocupadas.

---

*5. Cell*

* *Propósito:* Representa uma célula do tabuleiro, que pode conter um navio e ter um estado.
* *Relações:*

  * Pode conter uma referência a um *Ship*.
  * Utiliza *CellStatus* para gerenciar seu estado.
* *Fluxo:*

  * Quando um navio é colocado, a célula muda seu estado para *SHIP*.
  * Se receber um disparo, chama o método do navio para registrar o impacto e atualiza seu estado (*HIT* ou *MISS*).

---

*6. Board*

* *Propósito:* Representa o tabuleiro do jogador, composto por uma matriz de células e uma lista de navios.
* *Relações:*

  * Contém uma matriz de *Cell* e uma lista de *Ship*.
  * Utiliza *Coordinate* para identificar posições.
* *Fluxo:*

  * Gerencia a colocação de navios em células específicas.
  * Processa disparos identificando a célula correspondente e delegando a ação.
  * Verifica se todos os navios foram afundados.

---

*7. Player*

* *Propósito:* Representa um jogador com um nome e um tabuleiro.
* *Relações:*

  * Possui um *Board* para gerenciar seu tabuleiro.
  * Interage com outro *Player* ao disparar contra seu tabuleiro.
* *Fluxo:*

  * Coloca seus navios em seu tabuleiro.
  * Dispara contra as coordenadas do tabuleiro do oponente.
  * Verifica se perdeu ao checar se todos os seus navios foram afundados.

---

*8. Game*

* *Propósito:* Representa a lógica principal do jogo, incluindo jogadores e turnos.
* *Relações:*

  * Gerencia duas instâncias de *Player*.
  * Coordena as interações entre os tabuleiros dos jogadores.
* *Fluxo:*

  * Configura o jogo inicial (colocação dos navios).
  * Alterna turnos entre os jogadores.
  * Determina se o jogo terminou e declara um vencedor.

---

*9. Main*

* *Propósito:* Contém o ponto de entrada do programa.
* *Relações:*

  * Cria uma instância de *Game* e a inicia.
* *Fluxo:*

  * Inicia o jogo e gerencia a interação com o usuário.

---

### Resumo do fluxo geral

1. *Início do jogo:* Main cria uma instância de Game e a inicia.
2. *Configuração:* Game configura os tabuleiros dos jogadores (Player) e coloca os navios (Ship) nas células (Cell) do tabuleiro (Board).
3. *Turnos:* Os jogadores alternam turnos disparando contra coordenadas do tabuleiro do oponente.
4. *Impactos:* O tabuleiro processa o disparo, identifica a célula correspondente e atualiza seu estado. Se houver um navio, este registra o impacto.
5. *Fim do jogo:* Game verifica se todos os navios de um jogador foram afundados e declara um vencedor.