/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_9;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TuInterfaz extends JFrame {
    // Usamos LinkedHashSet para mantener orden de inserción
    private Set<String> hashSet = new LinkedHashSet<>();
    private JTextArea txtHashSalida;
   

    private void procesarNuevoValor(String valor, boolean mostrarAdvertencia) {
        try {
            // 1. Verificar duplicado
            if (!hashSet.add(valor)) {
                if (mostrarAdvertencia) {
                    JOptionPane.showMessageDialog(this, "Dato duplicado: " + valor, 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                return;
            }

            // 2. Actualizar txtHashSalida con clave secuencial
            if (txtHashSalida != null) {
                int clave = hashSet.size() - 1; // La posición actual es el nuevo índice
                txtHashSalida.append("Clave: " + clave + " → Valor: " + valor + "\n");
            }

            // ... resto del procesamiento (LinkedList, números, etc.)

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
}
