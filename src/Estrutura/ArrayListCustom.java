package Estrutura;

/**
 * Implementação de ArrayList personalizada para substituir java.util.ArrayList
 * Estrutura de dados baseada em array dinâmico
 */
public class ArrayListCustom<T> implements ListInterface<T> {
    
    private static final int CAPACIDADE_INICIAL = 10;
    private static final double FATOR_CRESCIMENTO = 1.5;
    
    private T[] elementos;
    private int tamanho;
    
    /**
     * Construtor padrão com capacidade inicial
     */
    @SuppressWarnings("unchecked")
    public ArrayListCustom() {
        this.elementos = (T[]) new Object[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }
    
    /**
     * Construtor com capacidade específica
     * @param capacidadeInicial capacidade inicial do array
     */
    @SuppressWarnings("unchecked")
    public ArrayListCustom(int capacidadeInicial) {
        if (capacidadeInicial < 0) {
            throw new IllegalArgumentException("Capacidade inicial não pode ser negativa");
        }
        this.elementos = (T[]) new Object[capacidadeInicial];
        this.tamanho = 0;
    }
    
    /**
     * Construtor de cópia
     * @param outraLista lista a ser copiada
     */
    public ArrayListCustom(ListInterface<T> outraLista) {
        this(outraLista.size() + 5); // Um pouco mais de espaço
        for (int i = 0; i < outraLista.size(); i++) {
            add(outraLista.get(i));
        }
    }
    
    @Override
    public boolean add(T element) {
        garantirCapacidade();
        elementos[tamanho] = element;
        tamanho++;
        return true;
    }
    
    @Override
    public T get(int index) {
        validarIndice(index);
        return elementos[index];
    }
    
    @Override
    public T remove(int index) {
        validarIndice(index);
        T elementoRemovido = elementos[index];
        
        // Mover elementos para a esquerda
        for (int i = index; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        
        tamanho--;
        elementos[tamanho] = null; // Evitar vazamento de memória
        
        return elementoRemovido;
    }
    
    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }
    
    @Override
    public int size() {
        return tamanho;
    }
    
    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < tamanho; i++) {
            elementos[i] = null;
        }
        tamanho = 0;
    }
    
    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }
    
    @Override
    public int indexOf(T element) {
        for (int i = 0; i < tamanho; i++) {
            if (element == null ? elementos[i] == null : element.equals(elementos[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Garante que há capacidade suficiente no array
     */
    @SuppressWarnings("unchecked")
    private void garantirCapacidade() {
        if (tamanho >= elementos.length) {
            int novaCapacidade = (int) (elementos.length * FATOR_CRESCIMENTO);
            if (novaCapacidade <= elementos.length) {
                novaCapacidade = elementos.length + 1;
            }
            
            T[] novoArray = (T[]) new Object[novaCapacidade];
            
            // Copiar elementos existentes
            for (int i = 0; i < tamanho; i++) {
                novoArray[i] = elementos[i];
            }
            
            elementos = novoArray;
        }
    }
    
    /**
     * Valida se o índice está dentro dos limites
     * @param index índice a ser validado
     * @throws IndexOutOfBoundsException se o índice for inválido
     */
    private void validarIndice(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice: " + index + ", Tamanho: " + tamanho);
        }
    }
    
    /**
     * Retorna a capacidade atual do array interno
     * @return capacidade atual
     */
    public int getCapacidade() {
        return elementos.length;
    }
    
    /**
     * Converte a lista para um array
     * @return array contendo todos os elementos da lista
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = elementos[i];
        }
        return array;
    }
    
    /**
     * Método auxiliar para converter lista de strings em string para join
     * @param separador separador entre elementos
     * @return string com elementos separados pelo separador
     */
    public String join(String separador) {
        if (isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(elementos[i].toString());
            if (i < tamanho - 1) {
                sb.append(separador);
            }
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < tamanho; i++) {
            sb.append(elementos[i]);
            if (i < tamanho - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
}