# Morse - Árvore Binária de Código Morse

## Descrição

Implementação de uma Árvore Binária de Busca com base nas regras do código morse: ponto para a esquerda e traço para a direita.

## Funcionalidades Implementadas ✅

- 🌳 **Inserção de caracteres** seguindo heurística do código morse (ponto à esquerda, traço à direita)
- 🎨 **Visualização gráfica** da árvore com interface Swing
- 🎯 **Caminho percorrido** destacado visualmente durante inserção
- 🟡 **Nós visitados** em amarelo, **não visitados** em cinza opaco
- 🔵 **Código morse** exibido em azul abaixo de cada letra
- ✨ **Animação** do percurso de inserção
- 📋 **Menu interativo** no console

## Como Executar

```bash
# Compilar o projeto
javac -d bin src/*.java

# Executar o programa
java -cp bin AppGrafico
```

## Menu Principal

1. **Inserir caractere** - Adiciona uma letra (A-Z, 0-9) na árvore
2. **Mostrar árvore** - Abre interface gráfica com visualização
3. **Codificar palavra** 
4. **Decodificar código morse**
5. **Sair** - Encerra o programa

## Requisitos de Implementação (TDE 2)

- ✅ Inserção dos caracteres conforme heurística do código morse (1,5 pts)
- 🔄 Busca em profundidade (1,5 pts) - *Em desenvolvimento*
- 🔄 Decodificação de sequências (2,0 pts) - *Em desenvolvimento*  
- 🔄 Codificação de sequências (2,0 pts) - *Em desenvolvimento*
- ✅ Interface gráfica para impressão da árvore (1,5 pts)
- ✅ Operações implementadas com recursão (1,5 pts)

## Estrutura do Projeto

```
src/
├── AppGrafico.java           # Aplicação principal com menu
└── TreeVisualizerSwing.java  # Visualizador gráfico da árvore
```

## Tecnologias

- **Java** - Linguagem principal
- **Swing** - Interface gráfica
- **Git** - Controle de versão

## Autor

Desenvolvido como parte do TDE 2 - Listas Não-Lineares
