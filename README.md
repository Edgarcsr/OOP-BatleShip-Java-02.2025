# 🚢 Batalha Naval Oceânica - Java# 🚢 _Batalha Naval em Java_

<p align="center">Bem-vindo à **Batalha Naval**, o clássico jogo de estratégia em versão Java! Um pouco simplificado, mas igualmente emocionante. Desafie o computador, jogue com outro jogador ou coloque o computador para competir contra si mesmo. Teste sua pontaria e afunde todos os navios inimigos antes que eles acabem com os seus.

<img src="https://img.shields.io/badge/Projeto-Batalha%20Naval%20Oceânica-0066CC?style=for-the-badge&logo=java&logoColor=white" />

<img src="https://img.shields.io/badge/Linguagem-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />Batalha Naval é um jogo de turnos onde dois jogadores posicionam seus navios em um tabuleiro oculto. O objetivo: adivinhar as posições dos navios rivais e afundá-los antes de ficar sem tentativas.

<img src="https://img.shields.io/badge/Paradigma-POO-4CAF50?style=for-the-badge&logo=objectoriented&logoColor=white" />Inclui diferentes níveis de dificuldade, reinício de partidas e uma interface amigável por console. _Em breve: interface avançada com Lanterna!_

<img src="https://img.shields.io/badge/Status-Completo-00C851?style=for-the-badge&logo=checkmarx&logoColor=white" />

</p>---

<p align="center">## 🎮 *Padrão...*

  <img src="https://img.shields.io/badge/Versão-2.0%20Oceânica-1E88E5?style=flat-square" />

<img src="https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=java" />Este projeto implementa o padrão _Game Loop_, fundamental no desenvolvimento de videogames. O loop principal gerencia a entrada do usuário, atualiza o estado do jogo e renderiza a interface a cada turno, garantindo um fluxo ordenado e eficiente. Essa abordagem facilita a expansão futura do jogo, como a incorporação de animações, sons ou interfaces gráficas.

  <img src="https://img.shields.io/badge/Licença-MIT-green?style=flat-square" />

</p>---

---## 🚀 _Como começar?_

## 🌊 **Sobre o Projeto**### 1. _Requisitos_

Bem-vindo à **Batalha Naval Oceânica**, o clássico jogo de estratégia naval em versão Java com tema oceânico! Um projeto desenvolvido com foco em **Programação Orientada a Objetos**, apresentando uma interface temática onde as ondas do mar (~) marcam territórios inexplorados.- **Java 8** ou superior

- (Opcional) **IntelliJ IDEA** ou outro IDE compatível

Desafie o computador, jogue com outro jogador ou coloque a IA para competir contra si mesma. Teste sua pontaria e afunde todos os navios inimigos antes que eles acabem com os seus!

### 2. _Instalação e execução_

### ⚓ **Características Especiais**

- 🌊 **Tema Oceânico**: Ondas (~) representam águas inexploradas1. **Clone o repositório:**

- ⚡ **Poder Especial**: O Carrier pode disparar 3 torpedos consecutivos ```bash

- 🎯 **Seleção Estratégica**: Escolha qual navio da sua frota usará para atacar git clone <URL-do-repositório>

- 🤖 **IA Inteligente**: CPU prioriza usar navios com poderes especiais ```

---2. **Compile o projeto:**

```bash

## 🚢 **Frota Naval**   javac -d out src/**/*.java

```

| Nº | Classe do Navio | Tamanho | Símbolo | Poder Especial |

|----|-----------------|---------|---------|----------------|3. **Execute o jogo:**

| 1 | Carrier | 5 | C | ⚡ 3 Torpedos | ```bash

| 2 | Battleship | 4 | B | - | java -cp out Main

| 3 | Cruiser | 3 | R | - | ```

| 4 | Submarine | 3 | S | - |

| 5 | Destroyer | 2 | D | - |👨‍💻 _Créditos_

Desenvolvido com paixão por Tian. Contribuições e sugestões são bem-vindas!

---

Pronto para a batalha? Clone, compile e jogue! 🚢💥

## 🎮 **Padrões de Projeto**

Este projeto implementa o padrão **Game Loop**, fundamental no desenvolvimento de videogames. O loop principal gerencia:

- 🎯 Entrada do usuário
- 🔄 Atualização do estado do jogo
- 🖥️ Renderização da interface a cada turno

Essa abordagem facilita a expansão futura do jogo, como a incorporação de animações, sons ou interfaces gráficas avançadas.

---

## 🚀 **Como Executar**

### 📋 **Requisitos**

- **Java 8** ou superior
- Terminal/Prompt de comando
- (Opcional) **IntelliJ IDEA** ou outro IDE compatível

### ⚡ **Instalação e Execução**

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/Edgarcsr/OOP-BatleShip-Java-02.2025.git
   ```

2. **Navegue até o diretório:**

   ```bash
   cd OOP-BatleShip-Java-02.2025
   ```

3. **Compile o projeto:**

   ```bash
   javac -d out src/**/*.java
   ```

4. **Execute o jogo:**
   ```bash
   java -cp out Main
   ```

### 🎯 **Níveis de Dificuldade**

- ⛵ **Marinheiro** (Fácil): Águas calmas para iniciantes
- 🚢 **Capitão** (Médio): Mares agitados para experientes
- ⚓ **Almirante** (Difícil): Tempestades para veteranos

---

## 🎲 **Modos de Jogo**

- 👥 **Jogador vs Jogador**: Batalha entre dois almirantes
- 🤖 **Jogador vs CPU**: Desafie a inteligência artificial
- ⚡ **CPU vs CPU**: Observe duas IAs batalhar

---

## 🧭 **Como Jogar**

1. **Escolha a dificuldade** e modo de jogo
2. **Selecione um navio** da sua frota para atacar
3. **Mire as coordenadas** (A-J, 0-9) para disparar torpedos
4. **Use estrategicamente** o poder especial do Carrier (3 torpedos)
5. **Afunde todos os navios** inimigos antes que seus torpedos acabem!

### 🗺️ **Legenda do Tabuleiro**

- `~` - Ondas do oceano (água inexplorada)
- `O` - Torpedo na água (erro)
- `X` - Navio atingido
- `C/B/R/S/D` - Navios visíveis (modo revelação)

---

## 🏗️ **Arquitetura do Projeto**

```
src/
├── Main.java                 # Ponto de entrada
├── enums/                    # Enumerações
│   ├── CellStatus.java
│   ├── Difficulty.java
│   ├── GameMode.java
│   ├── Orientation.java
│   └── ShipType.java
├── game/                     # Lógica principal
│   ├── Game.java
│   └── Menu.java
├── model/                    # Modelos de dados
│   ├── Board.java
│   ├── Cell.java
│   ├── Coordinate.java
│   ├── CpuPlayer.java
│   ├── HumanPlayer.java
│   ├── Player.java
│   ├── PlayerListener.java
│   └── Ship.java
└── utils/                    # Utilitários
    ├── BoatsGenerator.java
    ├── CoordinatesValidator.java
    ├── DifficultValidator.java
    └── RandomCellGenerator.java
```

---

## 👨‍💻 **Equipe de Desenvolvimento**

<table align="center">
  <tr>
    <td align="center">
      <img src="https://img.shields.io/badge/Desenvolvedor-Edgar%20C%20S%20Ribeiro-0066CC?style=for-the-badge&logo=github&logoColor=white" /><br>
      <strong>RA: 081230039</strong>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://img.shields.io/badge/Desenvolvedor-Nicholas%20Birochi-0066CC?style=for-the-badge&logo=github&logoColor=white" /><br>
      <strong>RA: 081230038</strong>
    </td>
  </tr>
</table>

---

## 🎯 **Funcionalidades Implementadas**

- ✅ Sistema de jogo completo com Game Loop
- ✅ Três níveis de dificuldade com tema náutico
- ✅ Múltiplos modos de jogo (PvP, PvE, EvE)
- ✅ Poder especial do Carrier (3 torpedos)
- ✅ Seleção estratégica de navios
- ✅ IA com estratégia para poderes especiais
- ✅ Interface temática oceânica
- ✅ Sistema de validação de coordenadas
- ✅ Geração automática de navios

---

## 🌊 **Versão Oceânica**

Esta é uma versão especial com identidade visual única:

- 🌊 Ondas (~) ao invés de espaços vazios
- ⚓ Terminologia náutica (Almirantes, Torpedos, Frota)
- 🚢 Interface temática com emojis marítimos
- ⚡ Poderes especiais para estratégia avançada

---

<p align="center">
  <img src="https://img.shields.io/badge/Feito%20com-☕%20Java%20e%20❤️-red?style=for-the-badge" />
</p>

<p align="center">
  <strong>🚢 Pronto para a batalha? Clone, compile e navegue! 🌊</strong>
</p>
