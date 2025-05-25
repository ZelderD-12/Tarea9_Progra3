package tarea_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HP15C0008LA
 */
public class listaNumeros {

    static void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void add(int numero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static String size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private ArrayList<Double> numeros;
    private double suma;
    private double promedio;
    private double minimo;
    private double maximo;

    public listaNumeros() {
        numeros = new ArrayList<>();
        suma = 0;
        promedio = 0;
        minimo = Double.MAX_VALUE;
        maximo = Double.MIN_VALUE;
    }

    // Método para agregar un número
    public void agregarNumero(double numero) {
        numeros.add(numero);
        actualizarEstadisticas(numero);
    }

    // Método para agregar varios números
    public void agregarNumeros(List<Double> nuevosNumeros) {
        numeros.addAll(nuevosNumeros);
        recalcularEstadisticas();
    }

    // Actualiza las estadísticas cuando se agrega un nuevo número
    private void actualizarEstadisticas(double numero) {
        suma += numero;
        promedio = suma / numeros.size();
        
        if (numero < minimo) {
            minimo = numero;
        }
        
        if (numero > maximo) {
            maximo = numero;
        }
    }

    // Recalcula todas las estadísticas desde cero
    private void recalcularEstadisticas() {
        if (numeros.isEmpty()) {
            suma = 0;
            promedio = 0;
            minimo = Double.MAX_VALUE;
            maximo = Double.MIN_VALUE;
            return;
        }

        suma = 0;
        minimo = Double.MAX_VALUE;
        maximo = Double.MIN_VALUE;

        for (double num : numeros) {
            suma += num;
            if (num < minimo) minimo = num;
            if (num > maximo) maximo = num;
        }

        promedio = suma / numeros.size();
    }

    // Métodos de acceso
    public double getSuma() {
        return suma;
    }

    public double getPromedio() {
        return promedio;
    }

    public double getMinimo() {
        return numeros.isEmpty() ? 0 : minimo;
    }

    public double getMaximo() {
        return numeros.isEmpty() ? 0 : maximo;
    }

    public int getCantidad() {
        return numeros.size();
    }

    // Obtiene una copia ordenada de los números
    public List<Double> getNumerosOrdenados(boolean ascendente) {
        List<Double> copia = new ArrayList<>(numeros);
        Collections.sort(copia);
        if (!ascendente) {
            Collections.reverse(copia);
        }
        return copia;
    }

    // Limpia toda la lista
    public void limpiar() {
        numeros.clear();
        suma = 0;
        promedio = 0;
        minimo = Double.MAX_VALUE;
        maximo = Double.MIN_VALUE;
    }

    // Busca un número en la lista
    public boolean contiene(double numero) {
        return numeros.contains(numero);
    }

    @Override
    public String toString() {
        return "ListaNumeros{" +
               "cantidad=" + numeros.size() +
               ", suma=" + suma +
               ", promedio=" + promedio +
               ", minimo=" + minimo +
               ", maximo=" + maximo +
               '}';
    }

    // Método para obtener la mediana
    public double getMediana() {
        if (numeros.isEmpty()) return 0;
        
        List<Double> copiaOrdenada = getNumerosOrdenados(true);
        int tamaño = copiaOrdenada.size();
        
        if (tamaño % 2 == 0) {
            return (copiaOrdenada.get(tamaño/2 - 1) + copiaOrdenada.get(tamaño/2)) / 2.0;
        } else {
            return copiaOrdenada.get(tamaño/2);
        }
    }

    // Método para obtener la desviación estándar
    public double getDesviacionEstandar() {
        if (numeros.isEmpty()) return 0;
        
        double media = getPromedio();
        double sumaCuadrados = 0;
        
        for (double num : numeros) {
            sumaCuadrados += Math.pow(num - media, 2);
        }
        
        return Math.sqrt(sumaCuadrados / numeros.size());
    }
}