package tarea_9;

import javax.swing.AbstractListModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP15C0008LA
 */
public class modeloLista<T> extends AbstractListModel<T> {
    private List<T> elementos;
    private String nombreLista;

    public modeloLista() {
        this.elementos = new ArrayList<>();
        this.nombreLista = "Elementos LinkedList";
    }

    public modeloLista(String nombre) {
        this();
        this.nombreLista = nombre;
    }

    @Override
    public int getSize() {
        return elementos.size();
    }

    @Override
    public T getElementAt(int index) {
        return elementos.get(index);
    }

    public void addElement(T elemento) {
        elementos.add(elemento);
        fireIntervalAdded(this, elementos.size()-1, elementos.size()-1);
    }

    public boolean removeElement(T elemento) {
        int index = elementos.indexOf(elemento);
        if (index != -1) {
            elementos.remove(index);
            fireIntervalRemoved(this, index, index);
            return true;
        }
        return false;
    }

    public void clear() {
        int size = elementos.size();
        elementos.clear();
        if (size > 0) {
            fireIntervalRemoved(this, 0, size-1);
        }
    }

    public boolean contains(T elemento) {
        return elementos.contains(elemento);
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombre) {
        this.nombreLista = nombre;
    }
}
