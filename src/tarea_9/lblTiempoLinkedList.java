package tarea_9;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

/**
 * JLabel personalizado para mostrar tiempos de LinkedList
 */
public class lblTiempoLinkedList extends JLabel {
    
    public lblTiempoLinkedList() {
        super("Tiempo LinkedList: 0 ns");
        configurarEstilo();
    }
    
    private void configurarEstilo() {
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setForeground(new Color(0, 100, 0)); // Verde oscuro
    }
    
    // Método principal para actualizar el tiempo
    public void setTiempo(String tiempo) {
        setText("Tiempo LinkedList: " + tiempo);
    }
    
    // Método sobrecargado para aceptar nanosegundos
    public void setTiempo(long nanos) {
        setText("Tiempo LinkedList: " + formatTiempo(nanos));
    }
    
    private String formatTiempo(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.3f μs", nanos/1_000.0);
        } else if (nanos < 1_000_000_000) {
            return String.format("%.3f ms", nanos/1_000_000.0);
        }
        return String.format("%.3f s", nanos/1_000_000_000.0);
    }
    
    public void reset() {
        setText("Tiempo LinkedList: 0 ns");
    }

    void actualizarTiempo(String obtenerTiempoFormateado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}