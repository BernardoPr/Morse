import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TreeVisualizerSwing extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;
    private static final int NODE_RADIUS = 25;
    
    private MorseBST bst;
    private List<String> lastPath;
    private TreePanel treePanel;
    
    // Classe Node para a árvore binária de busca
    static class Node {
        char letter;
        String morseCode;
        Node left;
        Node right;
        boolean isHighlighted; // Para destacar o caminho percorrido

        public Node(char letter, String morseCode) {
            this.letter = letter;
            this.morseCode = morseCode;
            this.left = null;
            this.right = null;
            this.isHighlighted = false;
        }
    }
    
    // Classe da árvore binária de busca
    static class MorseBST {
        private Node root;
        private List<String> insertionPath;

        public MorseBST() {
            this.root = null;
            this.insertionPath = new ArrayList<>();
        }

        public void insert(char letter, String morseCode) {
            System.out.println("Inserindo letra '" + letter + "' com código morse: " + morseCode);
            insertionPath.clear();
            root = insertRecursive(root, letter, morseCode, morseCode, "");
        }

        private Node insertRecursive(Node current, char letter, String morseCode, String remainingCode, String path) {
            // Adiciona o caminho atual à lista
            insertionPath.add(path);
            
            // Se chegamos ao fim do código morse, criamos o nó
            if (remainingCode.isEmpty()) {
                if (current == null) {
                    System.out.println("Caminho percorrido: " + path + " -> Criando nó para '" + letter + "'");
                    return new Node(letter, morseCode);
                } else {
                    System.out.println("Nó já existe no caminho: " + path);
                    return current;
                }
            }

            // Se o nó atual é null, criamos um nó intermediário
            if (current == null) {
                current = new Node(' ', ""); // Nó intermediário
            }

            // Pega o primeiro caractere do código morse restante
            char currentChar = remainingCode.charAt(0);
            String remaining = remainingCode.substring(1);

            if (currentChar == '.') {
                // Ponto vai para a esquerda
                System.out.println("Seguindo caminho esquerda (.) - " + path + ".");
                current.left = insertRecursive(current.left, letter, morseCode, remaining, path + ".");
            } else if (currentChar == '-') {
                // Traço vai para a direita  
                System.out.println("Seguindo caminho direita (-) - " + path + "-");
                current.right = insertRecursive(current.right, letter, morseCode, remaining, path + "-");
            }

            return current;
        }
        
        public List<String> getLastInsertionPath() {
            return new ArrayList<>(insertionPath);
        }
        
        public Node getRoot() {
            return root;
        }
        
        public void highlightPath(List<String> path) {
            clearHighlights(root);
            for (String pathStr : path) {
                highlightNodeAtPath(root, pathStr, "");
            }
        }
        
        private void clearHighlights(Node node) {
            if (node != null) {
                node.isHighlighted = false;
                clearHighlights(node.left);
                clearHighlights(node.right);
            }
        }
        
        private void highlightNodeAtPath(Node node, String targetPath, String currentPath) {
            if (node != null && currentPath.equals(targetPath)) {
                node.isHighlighted = true;
                return;
            }
            
            if (node != null && targetPath.startsWith(currentPath)) {
                if (targetPath.length() > currentPath.length()) {
                    char nextChar = targetPath.charAt(currentPath.length());
                    if (nextChar == '.') {
                        highlightNodeAtPath(node.left, targetPath, currentPath + ".");
                    } else if (nextChar == '-') {
                        highlightNodeAtPath(node.right, targetPath, currentPath + "-");
                    }
                }
            }
        }
        
        // Método para buscar um caractere na árvore seguindo o código morse
        public Node search(char character) {
            String morseCode = getMorseCodeForChar(character);
            if (morseCode == null) {
                return null;
            }
            
            System.out.println("Buscando caractere '" + character + "' com código morse: " + morseCode);
            insertionPath.clear(); // Reutilizar para mostrar o caminho de busca
            
            return searchRecursive(root, morseCode, "");
        }
        
        private Node searchRecursive(Node current, String remainingCode, String path) {
            // Adiciona o caminho atual à lista para visualização
            insertionPath.add(path);
            
            // Se chegamos ao fim do código morse, verificamos se encontramos o nó
            if (remainingCode.isEmpty()) {
                if (current != null && current.letter != ' ') {
                    System.out.println("Caminho percorrido: " + path + " -> Caractere encontrado: '" + current.letter + "'");
                    return current;
                } else {
                    System.out.println("Caminho percorrido: " + path + " -> Caractere não encontrado");
                    return null;
                }
            }
            
            // Se o nó atual é null, o caractere não existe na árvore
            if (current == null) {
                System.out.println("Caminho " + path + " não existe na árvore");
                return null;
            }
            
            // Pega o primeiro caractere do código morse restante
            char currentChar = remainingCode.charAt(0);
            String remaining = remainingCode.substring(1);
            
            if (currentChar == '.') {
                // Ponto vai para a esquerda
                System.out.println("Seguindo caminho esquerda (.) - " + path + ".");
                return searchRecursive(current.left, remaining, path + ".");
            } else if (currentChar == '-') {
                // Traço vai para a direita
                System.out.println("Seguindo caminho direita (-) - " + path + "-");
                return searchRecursive(current.right, remaining, path + "-");
            }
            
            return null;
        }
        
        // Método auxiliar para obter código morse de um caractere
        private String getMorseCodeForChar(char character) {
            String[][] morseTable = {
                {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
                {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
                {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
                {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
                {"Z", "--.."}, {"0", "-----"}, {"1", ".----"}, {"2", "..---"}, 
                {"3", "...--"}, {"4", "....-"}, {"5", "....."}, {"6", "-...."}, 
                {"7", "--..."}, {"8", "---.."}, {"9", "----."}
            };
            
            for (String[] entry : morseTable) {
                if (entry[0].charAt(0) == character) {
                    return entry[1];
                }
            }
            return null;
        }
        
        // Método para calcular a profundidade de um caractere
        public int getDepth(char character) {
            String morseCode = getMorseCodeForChar(character);
            if (morseCode == null) {
                return -1; // Caractere não suportado
            }
            
            return getDepthRecursive(root, morseCode, 0);
        }
        
        private int getDepthRecursive(Node current, String remainingCode, int currentDepth) {
            // Se chegamos ao fim do código morse
            if (remainingCode.isEmpty()) {
                if (current != null && current.letter != ' ') {
                    return currentDepth; // Encontrou o nó, retorna a profundidade
                } else {
                    return -1; // Nó não existe
                }
            }
            
            // Se o nó atual é null, o caractere não existe na árvore
            if (current == null) {
                return -1;
            }
            
            // Pega o primeiro caractere do código morse restante
            char currentChar = remainingCode.charAt(0);
            String remaining = remainingCode.substring(1);
            
            if (currentChar == '.') {
                // Ponto vai para a esquerda
                return getDepthRecursive(current.left, remaining, currentDepth + 1);
            } else if (currentChar == '-') {
                // Traço vai para a direita
                return getDepthRecursive(current.right, remaining, currentDepth + 1);
            }
            
            return -1;
        }
    }
    
    private class TreePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (bst != null && bst.getRoot() != null) {
                drawTree(g2d, bst.getRoot(), getWidth() / 2, 50, getWidth() / 4);
            } else {
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.setColor(Color.GRAY);
                String message = "Árvore vazia - Insira alguns caracteres";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(message)) / 2;
                int y = getHeight() / 2;
                g2d.drawString(message, x, y);
            }
        }
        
        private void drawTree(Graphics2D g2d, Node node, int x, int y, int xOffset) {
            if (node == null) return;
            
            // Definir cores baseadas no highlight
            Color lineColor = node.isHighlighted ? Color.BLACK : new Color(150, 150, 150, 180); // Cinza opaco para não visitados
            
            // Desenha as linhas primeiro (atrás dos nós)
            if (node.left != null) {
                g2d.setColor(lineColor);
                g2d.setStroke(new BasicStroke(node.isHighlighted ? 3 : 2));
                g2d.drawLine(x, y, x - xOffset, y + 100);
                drawTree(g2d, node.left, x - xOffset, y + 100, xOffset / 2);
            }
            
            if (node.right != null) {
                g2d.setColor(lineColor);
                g2d.setStroke(new BasicStroke(node.isHighlighted ? 3 : 2));
                g2d.drawLine(x, y, x + xOffset, y + 100);
                drawTree(g2d, node.right, x + xOffset, y + 100, xOffset / 2);
            }
            
            // Desenha o nó - cores diferenciadas para visitados e não visitados
            Color nodeColor;
            Color borderColor;
            Color textColor;
            
            if (node.isHighlighted) {
                // Nós no caminho percorrido - amarelo brilhante
                nodeColor = new Color(255, 255, 0, 220); // Amarelo com transparência
                borderColor = new Color(255, 0, 0, 255); // Vermelho sólido
                textColor = Color.BLACK;
            } else {
                // Nós não visitados - cinza opaco
                nodeColor = new Color(200, 200, 200, 120); // Cinza claro opaco
                borderColor = new Color(100, 100, 100, 180); // Cinza escuro opaco
                textColor = new Color(80, 80, 80, 200); // Texto cinza opaco
            }
            
            // Círculo do nó
            g2d.setColor(nodeColor);
            g2d.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(node.isHighlighted ? 4 : 2));
            g2d.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            
            // Texto do nó
            g2d.setColor(textColor);
            g2d.setFont(new Font("Arial", Font.BOLD, node.isHighlighted ? 18 : 14));
            FontMetrics fm = g2d.getFontMetrics();
            
            String text = (node.letter == ' ') ? "•" : String.valueOf(node.letter);
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            
            g2d.drawString(text, x - textWidth / 2, y + textHeight / 2 - 2);
            
            // Código morse abaixo do nó (se não for intermediário)
            if (node.letter != ' ' && !node.morseCode.isEmpty()) {
                g2d.setFont(new Font("Arial", Font.PLAIN, node.isHighlighted ? 13 : 10));
                g2d.setColor(node.isHighlighted ? new Color(0, 0, 255, 255) : new Color(100, 100, 150, 150));
                String morse = node.morseCode;
                int morseWidth = g2d.getFontMetrics().stringWidth(morse);
                g2d.drawString(morse, x - morseWidth / 2, y + NODE_RADIUS + 15);
            }
        }
    }

    public TreeVisualizerSwing(MorseBST bst) {
        this.bst = bst;
        this.lastPath = new ArrayList<>();
        
        setTitle("Visualizador de Árvore Morse - Caminho: " + (lastPath.isEmpty() ? "Nenhum" : String.join(" -> ", lastPath)));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        
        initializeComponents();
        
        // Mostrar o caminho da última inserção
        if (bst != null) {
            List<String> path = bst.getLastInsertionPath();
            if (!path.isEmpty()) {
                showInsertionPath(path);
            }
        }
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Panel principal da árvore
        treePanel = new TreePanel();
        treePanel.setBackground(Color.WHITE);
        add(treePanel, BorderLayout.CENTER);
        
        // Panel de controles
        JPanel controlPanel = new JPanel(new FlowLayout());
        
        JButton highlightPathButton = new JButton("Mostrar Último Caminho");
        highlightPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bst != null) {
                    List<String> path = bst.getLastInsertionPath();
                    showInsertionPath(path);
                }
            }
        });
        
        JButton clearHighlightButton = new JButton("Limpar Destaque");
        clearHighlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bst != null) {
                    bst.clearHighlights(bst.getRoot());
                    treePanel.repaint();
                }
            }
        });
        
        controlPanel.add(highlightPathButton);
        controlPanel.add(clearHighlightButton);
         
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private void showInsertionPath(List<String> path) {
        if (bst == null || path.isEmpty()) return;
        
        this.lastPath = new ArrayList<>(path);
        setTitle("Visualizador de Árvore Morse - Último Caminho: " + String.join(" -> ", path));
        
        // Destacar o caminho
        bst.highlightPath(path);
        treePanel.repaint();
        
        // Mostrar animação do caminho (opcional)
        Timer timer = new Timer(1000, null);
        timer.addActionListener(new ActionListener() {
            int currentStep = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep < path.size()) {
                    bst.clearHighlights(bst.getRoot());
                    for (int i = 0; i <= currentStep; i++) {
                        bst.highlightPath(List.of(path.get(i)));
                    }
                    treePanel.repaint();
                    currentStep++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    
    public static void showTree(MorseBST bst) {
        SwingUtilities.invokeLater(() -> {
            new TreeVisualizerSwing(bst).setVisible(true);
        });
    }
}