package tarea_9;

import javax.swing.AbstractListModel;
import java.util.LinkedList;

/**
 *
 * @author HP15C0008LA
 */
public class lstLinkedList<T> extends AbstractListModel<T> {
    private LinkedList<T> datos;
    private String nombreLista;

    public lstLinkedList() {
        this.datos = new LinkedList<>();
        this.nombreLista = "Elementos LinkedList";
    }

    public lstLinkedList(String nombre) {
        this();
        this.nombreLista = nombre;
    }

    @Override
    public int getSize() {
        return datos.size();
    }

    @Override
    public T getElementAt(int index) {
        if (index >= 0 && index < datos.size()) {
            return datos.get(index);
        }
        return null;
    }

    // Método para agregar elemento (compatible con DefaultListModel)
    public void addElement(T elemento) {
        agregarElemento(elemento);
    }

    // Método principal para agregar elementos
    public void agregarElemento(T elemento) {
        datos.add(elemento);
        fireIntervalAdded(this, datos.size()-1, datos.size()-1);
    }

    public void agregarElementoAlInicio(T elemento) {
        datos.addFirst(elemento);
        fireIntervalAdded(this, 0, 0);
    }

    public boolean eliminarElemento(T elemento) {
        int index = datos.indexOf(elemento);
        if (index != -1) {
            datos.remove(index);
            fireIntervalRemoved(this, index, index);
            return true;
        }
        return false;
    }

    public boolean removeElement(T elemento) {
        return eliminarElemento(elemento);
    }

    public void limpiar() {
        int size = datos.size();
        datos.clear();
        if (size > 0) {
            fireIntervalRemoved(this, 0, size-1);
        }
    }

    public boolean contiene(T elemento) {
        return datos.contains(elemento);
    }

    public T obtenerPrimero() {
        if (datos.isEmpty()) return null;
        return datos.getFirst();
    }

    public T obtenerUltimo() {
        if (datos.isEmpty()) return null;
        return datos.getLast();
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombre) {
        this.nombreLista = nombre;
        fireContentsChanged(this, 0, datos.size()-1);
    }

    public LinkedList<T> getListaInterna() {
        return new LinkedList<>(datos);
    }

    // Métodos adicionales compatibles con DefaultListModel
    public int size() {
        return datos.size();
    }

    public boolean isEmpty() {
        return datos.isEmpty();
    }

    public T elementAt(int index) {
        return getElementAt(index);
    }
}