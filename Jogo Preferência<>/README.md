# 🎮 Jogo Preferência <> — Projeto Integrador

O **Preferência** é um jogo de perguntas e respostas em modo console, desenvolvido em **Java**, inspirado no estilo *"Would You Rather"* e *"The Higher Lower Game"*. O objetivo do jogador é analisar duas opções e adivinhar qual delas possui maior preferência/popularidade com base em votações de público.

---

## 📌 Sumário
- [Funcionalidades](#-funcionalidades)
- [Conceitos de Programação Aplicados](#-conceitos-de-programação-aplicados)
- [Estrutura do Código](#-estrutura-do-código)
- [Como Executar](#-como-executar)
- [Integrantes do Grupo](#-integrantes-do-grupo)

---

## ✨ Funcionalidades
- **Menu Interativo**: Navegação formatada por bordas dinâmicas no console.
- **Cores ANSI**: Interface customizada no terminal com a classe `ConsoleColors`.
- **Sorteio e Aleatoriedade**: Perguntas embaralhadas a cada nova partida via `Collections.shuffle()`.
- **Barra Visual de Porcentagem**: Renderização visual do percentual de votos usando barras gráficas.
- **Validação de Entradas**: Tratamento centralizado contra exceções e erros de digitação de números.
- **Configuração de Partida**: Permite selecionar de 10 a 50 perguntas por rodada.

---

## 🛠️ Conceitos de Programação Aplicados
- **Orientação a Objetos (POO)**: Encapsulamento da classe `Perguntas` como contêiner de dados.
- **Estruturas de Dados**: Uso de `ArrayList` para manipulação dinâmica das perguntas.
- **Lógica e Controle de Fluxo**: Loops (`while`, `for`, `do-while`) e condicionais (`switch`, `if-else`).
- **Tratamento de Exceções**: Bloco `try-catch` para garantir a robustez da leitura pelo `Scanner`.

---

## 📂 Estrutura do Código
- `Perguntas.java`: Modelo de dados para armazenar as opções, percentuais e resposta correta.
- `ProjetoIntegrador.java`: Concentra o fluxo do jogo, navegação de menus, validações e renderização visual no terminal.

---

## 🚀 Como Executar

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SEU-USUARIO/projeto-preferencia.git](https://github.com/SEU-USUARIO/projeto-preferencia.git)
