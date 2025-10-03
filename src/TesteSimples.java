public class TesteSimples {
    // Tabela morse igual à do projeto
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
        System.out.println("=== TESTE DE CODIFICAÇÃO ===");
        
        // Teste 1: Codificar JK
        String palavra = "JK";
        System.out.println("Entrada: " + palavra);
        System.out.print("Código Morse: ");
        
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            String morse = getMorseCode(letra);
            System.out.print(morse);
            if (i < palavra.length() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        System.out.println("\n=== TESTE DE DECODIFICAÇÃO ===");
        
        // Teste 2: Decodificar .--- -.-
        String codigo = ".--- -.-";
        System.out.println("Entrada: " + codigo);
        System.out.print("Palavra: ");
        
        String[] codigos = codigo.split(" ");
        for (String c : codigos) {
            char letra = getCharFromMorse(c);
            System.out.print(letra);
        }
        System.out.println();
    }
    
    private static String getMorseCode(char character) {
        for (String[] entry : MORSE_TABLE) {
            if (entry[0].charAt(0) == character) {
                return entry[1];
            }
        }
        return "?";
    }
    
    private static char getCharFromMorse(String morseCode) {
        for (String[] entry : MORSE_TABLE) {
            if (entry[1].equals(morseCode)) {
                return entry[0].charAt(0);
            }
        }
        return '?';
    }
}