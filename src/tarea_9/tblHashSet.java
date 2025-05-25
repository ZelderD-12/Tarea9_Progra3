package tarea_9;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP15C0008LA
 */
public class tblHashSet extends AbstractTableModel {
    private List<String> datos;
    private final String[] columnNames = {"Elementos HashSet"};
    
    public tblHashSet() {
        datos = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < datos.size()) {
            return datos.get(rowIndex);
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    // Método para agregar fila (equivalente a addRow)
    public void addRow(Object[] rowData) {
        if (rowData != null && rowData.length > 0) {
            String elemento = rowData[0].toString();
            agregarElemento(elemento);
        }
    }
    
    // Método para agregar elemento manteniendo comportamiento HashSet
    public void agregarElemento(String elemento) {
        if (!datos.contains(elemento)) {
            datos.add(elemento);
            fireTableRowsInserted(datos.size()-1, datos.size()-1);
        }
    }
    
    public void eliminarElemento(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < datos.size()) {
            datos.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    
    public void limpiar() {
        int size = datos.size();
        if (size > 0) {
            datos.clear();
            fireTableRowsDeleted(0, size-1);
        }
    }
    
    public boolean contiene(String elemento) {
        return datos.contains(elemento);
    }
    
    public List<String> getTodosElementos() {
        return new ArrayList<>(datos);
    }
    
    public int cantidadElementos() {
        return datos.size();
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}