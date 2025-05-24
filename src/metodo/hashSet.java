
package metodo;

import java.util.Arrays;
import java.util.Iterator;

public class hashSet<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] elementos;
    private int size;
    
    public hashSet() {
        elementos = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    public boolean add(T elemento) {
        if (contains(elemento)) {
            return false;
        }
        
        if (size >= elementos.length * LOAD_FACTOR) {
            aumentarCapacidad();
        }
        
        elementos[size++] = elemento;
        return true;
    }
    
    public boolean contains(T elemento) {
        for (int i = 0; i < size; i++) {
            if (elemento.equals(elementos[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(T elemento) {
        for (int i = 0; i < size; i++) {
            if (elemento.equals(elementos[i])) {
                // Mover el último elemento a esta posición
                elementos[i] = elementos[size-1];
                elementos[size-1] = null;
                size--;
                return true;
            }
        }
        return false;
    }
    
    public int size() {
        return size;
    }
    
    public void clear() {
        Arrays.fill(elementos, null);
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void aumentarCapacidad() {
        int nuevaCapacidad = elementos.length * 2;
        elementos = Arrays.copyOf(elementos, nuevaCapacidad);
    }
    
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementos, size));
    }
    
    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }
    
    private class HashSetIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T) elementos[currentIndex++];
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    // Método adicional para obtener todos los elementos como array
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementos, size, a.getClass());
        }
        System.arraycopy(elementos, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
}