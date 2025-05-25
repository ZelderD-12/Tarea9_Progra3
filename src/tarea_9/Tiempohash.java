package tarea_9;

/**
 * Clase para medición precisa de tiempos de ejecución
 */
public class Tiempohash {
    private long inicio;
    private long fin;
    
    public Tiempohash() {
        // Constructor vacío
    }
    
    public void iniciarMedicion() {
        inicio = System.nanoTime();
    }
    
    public void finalizarMedicion() {
        fin = System.nanoTime();
    }
    
    public long obtenerTiempoNanos() {
        return fin - inicio;
    }
    
    public String obtenerTiempoFormateado() {
        long tiempo = obtenerTiempoNanos();
        
        if (tiempo < 1000) {
            return tiempo + " ns";
        } else if (tiempo < 1_000_000) {
            return String.format("%.2f μs", tiempo / 1000.0);
        } else if (tiempo < 1_000_000_000) {
            return String.format("%.2f ms", tiempo / 1_000_000.0);
        } else {
            return String.format("%.4f s", tiempo / 1_000_000_000.0);
        }
    }
}