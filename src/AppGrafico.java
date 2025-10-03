import java.util.Scanner;

public class AppGrafico {
    
    private static TreeVisualizerSwing.MorseBST morseBST = new TreeVisualizerSwing.MorseBST();
    private static Scanner scanner = new Scanner(System.in);
    
    // Tabela de códigos morse padrão para inserção automática
    private static final String[][] MORSE_TABLE = {
        {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
        {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
        {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
        {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
        {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"}, {"Z", "--.."},
        {"0", "-----"}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"}, {"4", "....-"},
        {"5", "....."}, {"6", "-...."}, {"7", "--..."}, {"8", "---.."}, {"9", "----."}
    };

    public static void main(String[] args) {
        System.out.println("=== ÁRVORE BINÁRIA DE CÓDIGO MORSE ===");
        System.out.println("Regras: Ponto (.) → Esquerda | Traço (-) → Direita");
        System.out.println("======================================\n");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Inserir caractere");
            System.out.println("2. Mostrar árvore (interface gráfica)");
            System.out.println("3. Buscar caractere por profundidade");
            System.out.println("4. Codificar palavra");
            System.out.println("5. Decodificar código morse");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    inserirCaractere();
                    break;
                case 2:
                    mostrarArvore();
                    break;
                case 3:
                    buscarCaractere();
                    break;
                case 4:
                    codificarPalavra();
                    break;
                case 5:
                    decodificarMorse();
                    break;
                case 6:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        
        scanner.close();
    }

    private static void inserirCaractere() {
        System.out.println("\n--- INSERÇÃO DE CARACTERE ---");
        System.out.println("1. Inserção automática (código padrão)");
        System.out.println("2. Inserção personalizada (código customizado)");
        System.out.print("Escolha uma opção: ");
        
        String opcao = scanner.nextLine().trim();
        
        if (opcao.equals("1")) {
            inserirAutomatico();
        } else if (opcao.equals("2")) {
            inserirPersonalizado();
        } else {
            System.out.println("Opção inválida!");
        }
    }
    
    private static void inserirAutomatico() {
        System.out.print("Digite o caractere a ser inserido (A-Z, 0-9): ");
        String input = scanner.nextLine().toUpperCase().trim();
        
        if (input.length() != 1) {
            System.out.println("Por favor, digite apenas um caractere!");
            return;
        }
        
        char caractere = input.charAt(0);
        String morseCode = getMorseCode(caractere);
        
        if (morseCode == null) {
            System.out.println("Caractere não encontrado na tabela padrão!");
            System.out.println("Use a inserção personalizada (opção 2) para códigos customizados.");
            return;
        }
        
        // Verificar se a posição já está ocupada
        if (posicaoOcupada(morseCode)) {
            TreeVisualizerSwing.Node existente = morseBST.search(morseCode);
            System.out.println("\nPOSIÇÃO OCUPADA!");
            System.out.println("Já existe o caractere '" + existente.letter + "' no código morse '" + morseCode + "'");
            System.out.println("Não é possível inserir '" + caractere + "' na mesma posição.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\n=== INSERÇÃO AUTOMÁTICA ===");
        System.out.println("Letra: " + caractere);
        System.out.println("Código Morse: " + morseCode + " (padrão)");
        System.out.println();
        
        morseBST.insert(caractere, morseCode);
        
        System.out.println("\nCaractere inserido com sucesso!");
        System.out.println("Abrindo visualização gráfica...");
        TreeVisualizerSwing.showTree(morseBST);
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void inserirPersonalizado() {
        System.out.print("Digite o caractere a ser inserido: ");
        String input = scanner.nextLine().toUpperCase().trim();
        
        if (input.length() != 1) {
            System.out.println("Por favor, digite apenas um caractere!");
            return;
        }
        
        char caractere = input.charAt(0);
        
        System.out.print("Digite o código morse personalizado (ex: .-_ ): ");
        String morseCode = scanner.nextLine().trim();
        
        if (morseCode.isEmpty()) {
            System.out.println("Por favor, digite um código morse válido!");
            return;
        }
        
        // Validar que o código morse contém apenas pontos, traços e underscores
        if (!morseCode.matches("[._-]+")) {
            System.out.println("Código morse deve conter apenas pontos (.), traços (-) e underscores (_)!");
            return;
        }
        
        // Verificar se a posição já está ocupada
        if (posicaoOcupada(morseCode)) {
            TreeVisualizerSwing.Node existente = morseBST.search(morseCode);
            System.out.println("\nPOSIÇÃO OCUPADA!");
            System.out.println("Já existe o caractere '" + existente.letter + "' no código morse '" + morseCode + "'");
            System.out.println("Escolha um código morse diferente.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\n=== INSERÇÃO PERSONALIZADA ===");
        System.out.println("Letra: " + caractere);
        System.out.println("Código Morse: " + morseCode + " (personalizado)");
        System.out.println();
        
        morseBST.insert(caractere, morseCode);
        
        System.out.println("\nCaractere inserido com sucesso!");
        System.out.println("Abrindo visualização gráfica...");
        TreeVisualizerSwing.showTree(morseBST);
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    // Método para obter código morse da tabela padrão
    private static String getMorseCode(char character) {
        for (String[] entry : MORSE_TABLE) {
            if (entry[0].charAt(0) == character) {
                return entry[1];
            }
        }
        return null;
    }
    
    // Método para verificar se uma posição na árvore já está ocupada
    private static boolean posicaoOcupada(String morseCode) {
        TreeVisualizerSwing.Node node = morseBST.search(morseCode);
        return node != null && node.letter != ' ';
    }
    

    
    private static void mostrarArvore() {
        if (morseBST.getRoot() == null) {
            System.out.println("\nÁRVORE VAZIA!");
            System.out.println("Insira alguns caracteres primeiro usando a opção 1.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\nAbrindo visualização gráfica da árvore...");
        TreeVisualizerSwing.showTree(morseBST);
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void buscarCaractere() {
        if (morseBST.getRoot() == null) {
            System.out.println("\nÁRVORE VAZIA!");
            System.out.println("Insira alguns caracteres primeiro usando a opção 1.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.print("Digite o código morse a ser buscado (ex: .- para A): ");
        String morseCode = scanner.nextLine().trim();
        
        if (morseCode.isEmpty()) {
            System.out.println("Por favor, digite um código morse válido!");
            return;
        }
        
        // Validar que o código morse contém apenas pontos e traços
        if (!morseCode.matches("[.-]+")) {
            System.out.println("Código morse deve conter apenas pontos (.) e traços (-)!");
            return;
        }
        
        System.out.println("\n=== BUSCA EM PROFUNDIDADE ===");
        System.out.println("Código Morse: " + morseCode);
        System.out.println();
        
        // Buscar na árvore (mostrará o caminho no console)
        TreeVisualizerSwing.Node resultado = morseBST.search(morseCode);
        
        if (resultado != null) {
            int profundidade = morseBST.getDepth(resultado.letter, morseCode);
            System.out.println("\nCÓDIGO ENCONTRADO!");
            System.out.println("Letra: " + resultado.letter);
            System.out.println("Profundidade do nó: " + profundidade);
            System.out.println("Definição: A profundidade é o número de arestas entre o nó e a raiz");
            
            // Mostrar visualização gráfica
            System.out.println("\nAbrindo visualização gráfica do caminho de busca...");
            TreeVisualizerSwing.showTree(morseBST);
        } else {
            System.out.println("\nCÓDIGO NÃO ENCONTRADO!");
            System.out.println("O código morse '" + morseCode + "' não foi inserido na árvore ainda.");
        }
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void codificarPalavra() {
        if (morseBST.getRoot() == null) {
            System.out.println("\nÁRVORE VAZIA!");
            System.out.println("Insira alguns caracteres primeiro usando a opção 1.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.print("Digite a palavra para codificar (ex: JK): ");
        String palavra = scanner.nextLine();
        
        if (palavra.trim().isEmpty()) {
            System.out.println("Por favor, digite uma palavra válida!");
            return;
        }
        
        System.out.println();
        
        // Usar codificação recursiva na árvore (não na tabela)
        String resultado = morseBST.encodeWordRecursive(palavra);
        
        System.out.println("\nRESULTADO DA CODIFICAÇÃO:");
        System.out.println("Palavra original: \"" + palavra + "\"");
        System.out.println("Código morse: \"" + resultado + "\"");
   
        System.out.println("• Só mostra códigos de letras JÁ INSERIDAS na árvore");
        System.out.println("• '?' indica letras não inseridas ainda");

        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void decodificarMorse() {
        if (morseBST.getRoot() == null) {
            System.out.println("\nÁRVORE VAZIA!");
            System.out.println("Insira alguns caracteres primeiro usando a opção 1.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("Digite o código morse para decodificar:");
        System.out.println("(Separe cada código por espaço, ex: .--- -.- )");
        System.out.print("Código: ");
        String codigoMorse = scanner.nextLine().trim();
        
        if (codigoMorse.isEmpty()) {
            System.out.println("Por favor, digite um código morse válido!");
            return;
        }
        
        System.out.println();
        
        // Usar decodificação recursiva na árvore (não na tabela)
        String resultado = morseBST.decodeMessageRecursive(codigoMorse);
        
        System.out.println("\nRESULTADO DA DECODIFICAÇÃO:");
        System.out.println("Código morse: \"" + codigoMorse + "\"");
        System.out.println("Palavra decodificada: \"" + resultado + "\"");
        
        System.out.println("• Só decodifica letras JÁ INSERIDAS na árvore");
        System.out.println("• '?' indica códigos não encontrados na árvore");
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}