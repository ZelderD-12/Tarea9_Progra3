/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_9;

public class ListaEnlazada {
    private Nodo cabeza;

    private static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Insertar al final de la lista
    public void insertar(int numero) {
        Nodo nuevoNodo = new Nodo(numero);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    // Eliminar un número de la lista
    public boolean eliminar(int numero) {
        if (cabeza == null) return false;

        // Caso especial: eliminar la cabeza
        if (cabeza.dato == numero) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == numero) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false; // No se encontró el número
    }

    // Método para mostrar contenido (opcional, para depuración)
    public String mostrarContenido() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato).append(" -> ");
            actual = actual.siguiente;
        }
        sb.append("null");
        return sb.toString();
    }
}