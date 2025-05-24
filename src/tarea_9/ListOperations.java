package tarea_9;

import javax.swing.JTextArea;
import java.util.List;

public class  ListOperations {
    
    public static void appendToList(List<String> list, String value, JTextArea textArea) {
        if (list != null && value != null) {
            list.add(value);
            if (textArea != null) {
                textArea.append(value + "\n");
            }
        }
    }
    
    public static void updateNumberList(List<Integer> numberList, String value, JTextArea textArea) {
        if (value != null) {
            try {
                int num = Integer.parseInt(value);
                if (numberList != null) {
                    numberList.add(num);
                }
                if (textArea != null) {
                    textArea.append(num + "\n");
                }
            } catch (NumberFormatException e) {
                // No es un número, se ignora
            }
        }
    }
    
    public static void updateHashDisplay(List<String> sourceList, JTextArea displayArea) {
        if (sourceList != null && displayArea != null) {
            displayArea.setText("");
            for (int i = 0; i < sourceList.size(); i++) {
                displayArea.append("Clave: " + i + " → Valor: " + sourceList.get(i) + "\n");
            }
        }
    }
}