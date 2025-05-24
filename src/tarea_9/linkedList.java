package tarea_9;

import java.util.ArrayList;
import java.util.List;

public class linkedList<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public linkedList() {
        cabeza = null;
        tamaño = 0;
    }

    public boolean add(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
        return true;
    }

    public boolean contains(T elemento) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public int size() {
        return tamaño;
    }

    public void clear() {
        cabeza = null;
        tamaño = 0;
    }

    // Método para iterar como una lista normal
    public List<T> toStandardList() {
        List<T> lista = new ArrayList<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    // Método para usar en foreach
    public Iterable<T> elements() {
        return this::iterator;
    }

    private java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}