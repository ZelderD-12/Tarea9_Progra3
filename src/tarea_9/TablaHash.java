/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_9;

import java.util.LinkedList;

public class TablaHash {
    private LinkedList<Integer>[] tabla;
    private int capacidad;

    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    // Función hash simple (módulo)
    private int hash(int clave) {
        return clave % capacidad;
    }

    // Insertar un número en la tabla hash
    public void insertar(int numero) {
        int indice = hash(numero);
        tabla[indice].add(numero);
    }

    // Eliminar un número de la tabla hash
    public boolean eliminar(int numero) {
        int indice = hash(numero);
        return tabla[indice].remove((Integer) numero); // Retorna true si lo encontró y eliminó
    }

    // Método para mostrar contenido (opcional, para depuración)
    public String mostrarContenido() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacidad; i++) {
            sb.append("Casilla ").append(i).append(": ").append(tabla[i]).append("\n");
        }
        return sb.toString();
    }
}