package Estrutura;

/**
 * Interface List personalizada para substituir java.util.List
 * Implementação básica necessária para o projeto morse
 */
public interface ListInterface<T> {
    
    /**
     * Adiciona um elemento ao final da lista
     * @param element elemento a ser adicionado
     * @return true se o elemento foi adicionado com sucesso
     */
    boolean add(T element);
    
    /**
     * Obtém o elemento na posição especificada
     * @param index índice do elemento
     * @return elemento na posição especificada
     * @throws IndexOutOfBoundsException se o índice for inválido
     */
    T get(int index);
    
    /**
     * Remove o elemento na posição especificada
     * @param index índice do elemento a ser removido
     * @return elemento removido
     * @throws IndexOutOfBoundsException se o índice for inválido
     */
    T remove(int index);
    
    /**
     * Remove a primeira ocorrência do elemento especificado
     * @param element elemento a ser removido
     * @return true se o elemento foi removido
     */
    boolean remove(T element);
    
    /**
     * Retorna o número de elementos na lista
     * @return tamanho da lista
     */
    int size();
    
    /**
     * Verifica se a lista está vazia
     * @return true se a lista estiver vazia
     */
    boolean isEmpty();
    
    /**
     * Remove todos os elementos da lista
     */
    void clear();
    
    /**
     * Verifica se a lista contém o elemento especificado
     * @param element elemento a ser verificado
     * @return true se a lista contém o elemento
     */
    boolean contains(T element);
    
    /**
     * Retorna o índice da primeira ocorrência do elemento especificado
     * @param element elemento a ser procurado
     * @return índice do elemento ou -1 se não encontrado
     */
    int indexOf(T element);
}