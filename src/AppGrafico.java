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
            System.out.println("3. Codificar palavra");
            System.out.println("4. Decodificar código morse");
            System.out.println("5. Sair");
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
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case 4:
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case 5:
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
}