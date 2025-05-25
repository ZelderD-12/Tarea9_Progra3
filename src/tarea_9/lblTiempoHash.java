package tarea_9;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * JLabel personalizado para mostrar tiempos de operaciones HashSet
 * @author HP15C0008LA
 */
public class lblTiempoHash extends JLabel {
    
    private static final Color COLOR_TEXTO = new Color(0, 100, 0); // Verde oscuro
    private static final Font FUENTE = new Font("Segoe UI", Font.BOLD, 12);
    
    public lblTiempoHash() {
        super("Tiempo HashSet: 0 ns");
        configurarApariencia();
    }
    
    private void configurarApariencia() {
        setForeground(COLOR_TEXTO);
        setFont(FUENTE);
        setHorizontalAlignment(JLabel.LEFT);
    }
    
    /**
     * Actualiza el tiempo mostrado
     * @param tiempoFormateado El tiempo ya formateado como String (ej: "15.23 ms")
     */
    public void actualizarTiempo(String tiempoFormateado) {
        setText("Tiempo HashSet: " + tiempoFormateado);
    }
    
    /**
     * Actualiza el tiempo directamente desde nanosegundos
     * @param nanosegundos Tiempo en nanosegundos
     */
    public void actualizarTiempo(long nanosegundos) {
        String formato = formatearTiempo(nanosegundos);
        actualizarTiempo(formato);
    }
    
    /**
     * Reinicia el contador a cero
     */
    public void reiniciar() {
        setText("Tiempo HashSet: 0 ns");
    }
    
    /**
     * Establece el color del texto del tiempo
     * @param color Color a aplicar
     */
    public void setColorTexto(Color color) {
        setForeground(color);
    }
    
    /**
     * Obtiene solo el valor del tiempo sin el prefijo
     * @return El tiempo formateado sin el texto "Tiempo HashSet: "
     */
    public String getTiempoFormateado() {
        String textoCompleto = getText();
        return textoCompleto.replace("Tiempo HashSet: ", "");
    }
    
    private String formatearTiempo(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.3f μs", nanos / 1_000.0);
        } else if (nanos < 1_000_000_000) {
            return String.format("%.3f ms", nanos / 1_000_000.0);
        }
        return String.format("%.4f s", nanos / 1_000_000_000.0);
    }
    
    /**
     * Método sobreescrito para mantener el formato consistente
     * @param text Texto a mostrar (se le agregará automáticamente el prefijo)
     */
    @Override
    public void setText(String text) {
        if (!text.startsWith("Tiempo HashSet: ")) {
            super.setText("Tiempo HashSet: " + text);
        } else {
            super.setText(text);
        }
    }
}