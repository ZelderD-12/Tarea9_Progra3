package tarea_9;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP15C0008LA
 */
public class modeloHash extends AbstractTableModel {

    static void addRow(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private List<String> datos;
    private String nombreColumna;
    
    public modeloHash() {
        this.datos = new ArrayList<>();
        this.nombreColumna = "Elementos HashSet";
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }
    
    @Override
    public int getColumnCount() {
        return 1;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int column) {
        return nombreColumna;
    }
    
    public void agregarElemento(String elemento) {
        datos.add(elemento);
        fireTableRowsInserted(datos.size()-1, datos.size()-1);
    }
    
    public void limpiar() {
        int tamaño = datos.size();
        datos.clear();
        fireTableRowsDeleted(0, tamaño-1);
    }
    
    public boolean contiene(String elemento) {
        return datos.contains(elemento);
    }
}