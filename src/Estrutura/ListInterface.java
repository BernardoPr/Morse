package Estrutura;
public interface ListInterface<T> {
    

    boolean add(T element);
    
    T get(int index);
    
    T remove(int index);
    
    boolean remove(T element);
    
    int size();
    
    boolean isEmpty();

    void clear();
    
    boolean contains(T element);
    
    int indexOf(T element);
}