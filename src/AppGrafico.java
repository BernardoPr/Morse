import java.util.Scanner;

public class AppGrafico {
    
    private static TreeVisualizerSwing.MorseBST morseBST = new TreeVisualizerSwing.MorseBST();
    private static Scanner scanner = new Scanner(System.in);
    
    // Mapa com o código morse de cada letra
    private static final String[][] MORSE_TABLE = {
        {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
        {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
        {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
        {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
        {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
        {"Z", "--.."}, {"0", "-----"}, {"1", ".----"}, {"2", "..---"}, 
        {"3", "...--"}, {"4", "....-"}, {"5", "....."}, {"6", "-...."}, 
        {"7", "--..."}, {"8", "---.."}, {"9", "----."}
    };

    public static void main(String[] args) {
        System.out.println("=== ÁRVORE BINÁRIA DE CÓDIGO MORSE ===");
        System.out.println("Regras: Ponto (.) → Esquerda | Traço (-) → Direita");
        System.out.println("Com visualização gráfica do caminho percorrido!");
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
        System.out.print("Digite o caractere a ser inserido (A-Z, 0-9): ");
        String input = scanner.nextLine().toUpperCase();
        
        if (input.length() != 1) {
            System.out.println("Por favor, digite apenas um caractere!");
            return;
        }
        
        char caractere = input.charAt(0);
        String morseCode = getMorseCode(caractere);
        
        if (morseCode == null) {
            System.out.println("Caractere não suportado! Use apenas A-Z ou 0-9.");
            return;
        }
        
        System.out.println("\n=== INSERÇÃO ===");
        System.out.println("Letra: " + caractere);
        System.out.println("Código Morse: " + morseCode);
        System.out.println();
        
        // Inserir na árvore (mostrará o caminho no console)
        morseBST.insert(caractere, morseCode);
        
        System.out.println("\nCaractere inserido com sucesso!");
        
        // Automaticamente mostrar a árvore gráfica após inserção
        System.out.println("Abrindo visualização gráfica...");
        TreeVisualizerSwing.showTree(morseBST);
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static String getMorseCode(char character) {
        for (String[] entry : MORSE_TABLE) {
            if (entry[0].charAt(0) == character) {
                return entry[1];
            }
        }
        return null;
    }
    
    private static void mostrarArvore() {
        if (morseBST.getRoot() == null) {
            System.out.println("\n⚠️  ÁRVORE VAZIA!");
            System.out.println("Insira alguns caracteres primeiro usando a opção 1.");
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("\n🌳 Abrindo visualização gráfica da árvore...");
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
        
        System.out.print("Digite o caractere a ser buscado (A-Z, 0-9): ");
        String input = scanner.nextLine().toUpperCase();
        
        if (input.length() != 1) {
            System.out.println("Por favor, digite apenas um caractere!");
            return;
        }
        
        char caractere = input.charAt(0);
        String morseCode = getMorseCode(caractere);
        
        if (morseCode == null) {
            System.out.println("Caractere não suportado! Use apenas A-Z ou 0-9.");
            return;
        }
        
        System.out.println("\n=== BUSCA EM PROFUNDIDADE ===");
        System.out.println("Letra: " + caractere);
        System.out.println("Código Morse: " + morseCode);
        System.out.println();
        
        // Buscar na árvore (mostrará o caminho no console)
        TreeVisualizerSwing.Node resultado = morseBST.search(caractere);
        
        if (resultado != null) {
            int profundidade = morseBST.getDepth(caractere);
            System.out.println("\n✅ CARACTERE ENCONTRADO!");
            System.out.println("📏 Profundidade do nó: " + profundidade);
            System.out.println("📍 Definição: A profundidade é o número de arestas entre o nó e a raiz");
            
            // Mostrar visualização gráfica
            System.out.println("\nAbrindo visualização gráfica do caminho de busca...");
            TreeVisualizerSwing.showTree(morseBST);
        } else {
            System.out.println("\n❌ CARACTERE NÃO ENCONTRADO!");
            System.out.println("O caractere '" + caractere + "' não foi inserido na árvore ainda.");
        }
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void codificarPalavra() {
        System.out.print("Digite a palavra para codificar (ex: JK): ");
        String palavra = scanner.nextLine().toUpperCase();
        
        if (palavra.trim().isEmpty()) {
            System.out.println("Por favor, digite uma palavra válida!");
            return;
        }
        
        System.out.println("\n=== CODIFICAÇÃO ===");
        System.out.println("Entrada: " + palavra);
        System.out.print("Código Morse: ");
        
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            String morse = getMorseCode(letra);
            
            if (morse != null) {
                resultado.append(morse);
                if (i < palavra.length() - 1) {
                    resultado.append(" ");
                }
                System.out.print(morse);
                if (i < palavra.length() - 1) {
                    System.out.print(" ");
                }
            } else {
                System.out.print("?");
                resultado.append("?");
            }
        }
        
        System.out.println();
        System.out.println("Resultado completo: " + resultado.toString());
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void decodificarMorse() {
        System.out.println("Digite o código morse para decodificar:");
        System.out.println("(Separe cada código por espaço, ex: .--- -.- )");
        System.out.print("Código: ");
        String codigoMorse = scanner.nextLine().trim();
        
        if (codigoMorse.isEmpty()) {
            System.out.println("Por favor, digite um código morse válido!");
            return;
        }
        
        System.out.println("\n=== DECODIFICAÇÃO ===");
        System.out.println("Entrada: " + codigoMorse);
        System.out.print("Palavra: ");
        
        StringBuilder resultado = new StringBuilder();
        String[] codigos = codigoMorse.split(" ");
        
        for (String codigo : codigos) {
            if (!codigo.trim().isEmpty()) {
                char letra = getCharFromMorse(codigo.trim());
                System.out.print(letra);
                resultado.append(letra);
            }
        }
        
        System.out.println();
        System.out.println("Resultado completo: " + resultado.toString());
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    // Método auxiliar para obter caractere a partir do código morse
    private static char getCharFromMorse(String morseCode) {
        for (String[] entry : MORSE_TABLE) {
            if (entry[1].equals(morseCode)) {
                return entry[0].charAt(0);
            }
        }
        return '?'; // Se não encontrar
    }
}