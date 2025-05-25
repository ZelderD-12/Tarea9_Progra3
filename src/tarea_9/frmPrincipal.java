/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_9;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import metodo.hashSet;

//Hola
public class frmPrincipal extends javax.swing.JFrame {

    private ArrayList<Integer> listaNumeros = new ArrayList<>();
    private hashSet<String> hashSet;
    private linkedList<String> linkedList;
    private tblHashSet modeloHash;
    private lstLinkedList<String> modeloLista;
    private lblTiempoLinkedList lblTiempoLinkedList;
    private lblTiempoHash lblTiempoHash;
    private int contadorClaves = 0;



    public frmPrincipal() {
        initComponents();
        getContentPane().setBackground(new Color(51, 255, 168));
        txaDatos.setBackground(Color.WHITE);
        txaDatos.setForeground(Color.BLACK);
        
         // Inicialización de estructuras personalizadas
        hashSet = new hashSet<>();
        linkedList = new linkedList<>();
        modeloHash = new tblHashSet();
        modeloLista = new lstLinkedList<>("Elementos LinkedList");
     
        this.txtHashSalida = txtHashSalida;
        this.txtSalida = txtSalida;
        
    }

    
    // Método auxiliar para actualizar ambas visualizaciones
private void actualizarVisualizaciones() {
    // Actualizar el área de datos originales
    StringBuilder contenido = new StringBuilder();
    for (Integer numero : listaNumeros) {
        contenido.append(numero).append("\n");
    }
    txaDatos.setText(contenido.toString());
    
    // Actualizar lista enlazada
    LinkedList<Integer> listaEnlazada = new LinkedList<>(listaNumeros);
    txtSalida.setText("");
    for (Integer numero : listaEnlazada) {
        txtSalida.append(numero + "\n");
    }
    
    // Actualizar tabla hash
    HashMap<Integer, Integer> tablaHash = new HashMap<>();
    int posicion = 0;
    for (Integer numero : listaEnlazada) {
        tablaHash.put(posicion, numero);
        posicion++;
    }
    
    txtHashSalida.setText("");
    for (Map.Entry<Integer, Integer> entrada : tablaHash.entrySet()) {
        txtHashSalida.append("Clave: " + entrada.getKey() + " → Valor: " + entrada.getValue() + "\n");
    }
}

        // Métodos para la Tabla Hash de busqueda
    private boolean buscarEnTablaHash(String texto, int numero) {
        if (texto == null || texto.isEmpty()) return false;
    
     String[] entradas = texto.split("\n");
        for (String entrada : entradas) {
         try {
             if (entrada.contains("Clave:") && entrada.contains("→ Valor:")) {
                    String claveStr = entrada.split("Clave:")[1].split("→")[0].trim();
                  int clave = Integer.parseInt(claveStr);
                 if (clave == numero) {
                        return true;
                   }
                }
            } catch (Exception ex) {
                continue;
         }
     }
     return false;
    }
        // Metodo para la Tabla Enlazada de Busqueda
    private boolean buscarEnTablaEnlazada(String texto, int numero) {
     if (texto == null || texto.isEmpty()) return false;
    
        String[] numeros = texto.split("[\\s,;]+");
     for (String numStr : numeros) {
         try {
             int num = Integer.parseInt(numStr.trim());
               if (num == numero) {
                   return true;
               }
         } catch (NumberFormatException ex) {
                continue;
          }
        }
        return false;
    }
    
    private String formatarTiempo(long nanos) {
     long milisegundos = nanos / 1_000_000;
        long segundos = milisegundos / 1_000;
        long minutos = segundos / 60;
    
        milisegundos = milisegundos % 1_000;
        segundos = segundos % 60;
    
        return String.format("%02d:%02d.%03d", minutos, segundos, milisegundos);
    }
    
    private void actualizarVisualizacionesCompletas() {
    // Actualizar txtSalida (Lista Enlazada)
    if (txtSalida != null && linkedList != null) {
        txtSalida.setText(""); // Limpiar contenido previo
        
        // Mostrar todos los elementos de la lista enlazada
        for (String elemento : linkedList.elements()) {
            txtSalida.append(elemento + "\n");
        }
    }
    
    // Actualizar txtHashSalida (Tabla Hash)
    if (txtHashSalida != null && linkedList != null) {
        txtHashSalida.setText(""); // Limpiar contenido previo
        Map<Integer, String> tablaHash = new HashMap<>();
        int posicion = 0;
        
        // Llenar la tabla hash
        for (String elemento : linkedList.toStandardList()) {
            tablaHash.put(posicion++, elemento);
            txtHashSalida.append("Clave: " + (posicion-1) + " → Valor: " + elemento + "\n");
        }
    }
    
    // Actualizar txaDatos (consola principal)
    if (txaDatos != null && linkedList != null) {
        txaDatos.setText("");
        for (String elemento : linkedList.elements()) {
            txaDatos.append(elemento + "\n");
        }
      }
    }
    
    private void procesarNuevoValor(String valor) {
            try {
        // 1. Verificar y agregar al HashSet
        if (hashSet != null) {
            if (!hashSet.add(valor)) {
                // Solo mostrar advertencia si es interacción directa (no carga inicial)
                if (JOptionPane.getRootFrame().isActive()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "El dato '" + valor + "' ya existe",
                        "Dato duplicado",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
                return;
            }

            // 2. Actualizar visualización en txtHashSalida
            if (txtHashSalida != null) {
                txtHashSalida.append("Clave: " + contadorClaves + " → Valor: " + valor + "\n");
            }
        }

        // 3. Incrementar contador (solo si fue valor nuevo)
        contadorClaves++;

        // 4. Agregar a LinkedList
        if (linkedList != null) {
            linkedList.add(valor);
        }

        // 5. Procesar números en txtSalida
        try {
            int numero = Integer.parseInt(valor);
            if (listaNumeros != null) {
                listaNumeros.add(numero);
            }
            if (txtSalida != null) {
                txtSalida.append(valor + "\n");
            }
        } catch (NumberFormatException e) {
            // No es número, continuar sin problemas
        }

        // 6. Actualizar modelo visual (JList, JTable, etc.)
        if (modeloLista != null) {
            modeloLista.addElement(valor);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Error al procesar '" + valor + "': " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
     private void procesarDatos() {
        // Pedir el número a insertar
        String input = JOptionPane.showInputDialog(null, "Ingrese el número a insertar:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) {
            return; // El usuario canceló o no ingresó nada
        }
        
        try {
            int numeroInsertar = Integer.parseInt(input.trim());
            
            // Procesar tabla hash
            long inicioHash = System.nanoTime();
            procesarTablaHash(numeroInsertar);
            long finHash = System.nanoTime();
            long tiempoHash = finHash - inicioHash;
            
            // Procesar tabla enlazada
            long inicioLista = System.nanoTime();
            procesarTablaEnlazada(numeroInsertar);
            long finLista = System.nanoTime();
            long tiempoLista = finLista - inicioLista;
            
            // Mostrar tiempos
            String tiempoHashStr = formatTiempo(tiempoHash);
            String tiempoListaStr = formatTiempo(tiempoLista);
            
            JOptionPane.showMessageDialog(null, 
                "Tiempos de inserción:\n" +
                "Tabla Hash: " + tiempoHashStr + "\n" +
                "Tabla Enlazada: " + tiempoListaStr,
                "Resultados de Tiempo",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
       private void procesarListaEnlazada(int numero) {
    // Obtener el texto actual del área de texto
    String textoActual = txtSalida.getText();
    String[] numerosStr = textoActual.split("\n");
    
    // Crear una lista enlazada
    LinkedList<Integer> lista = new LinkedList<>();
    
    // Procesar números existentes
    for (String numStr : numerosStr) {
        try {
            int num = Integer.parseInt(numStr.trim());
            lista.add(num);
        } catch (NumberFormatException ex) {
            // Ignorar líneas no numéricas
        }
    }
    
    // Insertar el nuevo número al inicio (puedes cambiarlo a addLast() si prefieres)
    lista.addFirst(numero);
    
    // Reconstruir el texto para el JTextArea
    StringBuilder nuevoTexto = new StringBuilder();
    for (Integer num : lista) {
        nuevoTexto.append(num).append("\n");
    }
    
    // Actualizar el área de texto
    txtSalida.setText(nuevoTexto.toString());
}
    
    private void procesarTablaHash(int numeroInsertar) {
        // Obtener texto del área
        String texto = txtHashSalida.getText();
        String[] lineas = texto.split("\n");
        
        // Crear tabla hash
        Hashtable<Integer, Integer> tablaHash = new Hashtable<>();
        
        // Procesar cada línea
        for (String linea : lineas) {
            if (linea.contains("→")) {
                String[] partes = linea.split("→");
                if (partes.length == 2) {
                    try {
                        // Extraer clave
                        String claveStr = partes[0].replace("Clave:", "").trim();
                        int clave = Integer.parseInt(claveStr);
                        
                        // Extraer valor
                        String valorStr = partes[1].replace("Valor:", "").trim();
                        int valor = Integer.parseInt(valorStr);
                        
                        tablaHash.put(clave, valor);
                    } catch (NumberFormatException ex) {
                        // Ignorar líneas mal formateadas
                    }
                }
            }
        }
        
        // Insertar nuevo elemento (usamos un hash simple para la clave)
        int claveNueva = Math.abs(numeroInsertar % 1000000); // Ejemplo de función hash simple
        tablaHash.put(claveNueva, numeroInsertar);
        
        // Actualizar el JTextArea
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : tablaHash.entrySet()) {
            sb.append("Clave: ").append(entry.getKey())
              .append(" → Valor: ").append(entry.getValue())
              .append("\n");
        }
        txtHashSalida.setText(sb.toString());
    }
    
    private void procesarTablaEnlazada(int numeroInsertar) {
        // Obtener texto del área
        String texto = txtSalida.getText();
        String[] numerosStr = texto.split("\n");
        
        // Crear lista enlazada
        LinkedList<Integer> lista = new LinkedList<>();
        
        // Procesar cada número
        for (String numStr : numerosStr) {
            try {
                int num = Integer.parseInt(numStr.trim());
                lista.add(num);
            } catch (NumberFormatException ex) {
                // Ignorar líneas no numéricas
            }
        }
        
        // Insertar nuevo elemento al inicio
        lista.addFirst(numeroInsertar);
        
        // Actualizar el JTextArea
        StringBuilder sb = new StringBuilder();
        for (Integer num : lista) {
            sb.append(num).append("\n");
        }
        txtSalida.setText(sb.toString());
    }
    
    private String formatTiempo(long nanos) {
        long milis = nanos / 1_000_000;
        long segundos = milis / 1000;
        long minutos = segundos / 60;
        
        milis = milis % 1000;
        segundos = segundos % 60;
        
        return String.format("%d min, %d seg, %d ms", minutos, segundos, milis);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDatos = new javax.swing.JTextArea();
        btnMostrar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHashSalida = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblmostrarh = new javax.swing.JLabel();
        lblagregarh = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblbuscarh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbleliminarh = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblmostrarl = new javax.swing.JLabel();
        lblagregarl = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblbuscarl = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbleliminarl = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Tabla Hash vs Lista Enlazada");

        txaDatos.setColumns(20);
        txaDatos.setRows(5);
        jScrollPane1.setViewportView(txaDatos);

        btnMostrar.setBackground(new java.awt.Color(0, 0, 153));
        btnMostrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnMostrar.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(0, 0, 153));
        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCargar.setBackground(new java.awt.Color(0, 0, 153));
        btnCargar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 0, 153));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 0, 153));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtHashSalida.setColumns(20);
        txtHashSalida.setRows(5);
        jScrollPane2.setViewportView(txtHashSalida);

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane3.setViewportView(txtSalida);

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Tabla Hash");

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Lista Enlazada");

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Tiempo de Mostrar:");

        lblmostrarh.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblmostrarh.setForeground(new java.awt.Color(0, 51, 255));
        lblmostrarh.setText("00:00:000");

        lblagregarh.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblagregarh.setForeground(new java.awt.Color(0, 51, 255));
        lblagregarh.setText("00:00:000");

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Tiempo de Agregar:");

        lblbuscarh.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblbuscarh.setForeground(new java.awt.Color(0, 51, 255));
        lblbuscarh.setText("00:00:000");

        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Tiempo de Buscar:");

        lbleliminarh.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lbleliminarh.setForeground(new java.awt.Color(0, 51, 255));
        lbleliminarh.setText("00:00:000");

        jLabel11.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Tiempo de Eliminar:");

        lblmostrarl.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblmostrarl.setForeground(new java.awt.Color(0, 51, 255));
        lblmostrarl.setText("00:00:000");

        lblagregarl.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblagregarl.setForeground(new java.awt.Color(0, 51, 255));
        lblagregarl.setText("00:00:000");

        jLabel14.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 153));
        jLabel14.setText("Tiempo de Agregar:");

        lblbuscarl.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lblbuscarl.setForeground(new java.awt.Color(0, 51, 255));
        lblbuscarl.setText("00:00:000");

        jLabel16.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 153));
        jLabel16.setText("Tiempo de Buscar:");

        lbleliminarl.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        lbleliminarl.setForeground(new java.awt.Color(0, 51, 255));
        lbleliminarl.setText("00:00:000");

        jLabel18.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setText("Tiempo de Eliminar:");

        jLabel19.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 153));
        jLabel19.setText("Tiempo de Mostrar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)
                                .addComponent(lblmostrarh))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(22, 22, 22)
                                .addComponent(lblagregarh))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblbuscarh))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(22, 22, 22)
                                    .addComponent(lbleliminarh))))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(22, 22, 22)
                                .addComponent(lblmostrarl))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(22, 22, 22)
                                .addComponent(lblagregarl))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblbuscarl))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(22, 22, 22)
                                    .addComponent(lbleliminarl))))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmostrarh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblagregarh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbuscarh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbleliminarh)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmostrarl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblagregarl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbuscarl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbleliminarl))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            listaNumeros.clear(); // Limpia la lista original

            // Use un StringBuilder grande para evitar relocalización constante
            StringBuilder contenido = new StringBuilder(50_000_000); // 50 MB aprox (ajustable)

            try (BufferedReader br = new BufferedReader(new FileReader(archivo), 64 * 1024)) { // 64 KB buffer
                String linea;

                while ((linea = br.readLine()) != null) {
                    // Split solo si hay coma
                    if (!linea.trim().isEmpty()) {
                        String[] numeros = linea.split(",");

                        for (String num : numeros) {
                            num = num.trim();
                            if (!num.isEmpty()) {
                                try {
                                    int numero = Integer.parseInt(num);
                                    listaNumeros.add(numero);
                                    contenido.append(numero).append("\n");
                                } catch (NumberFormatException e) {
                                }
                            }
                        }
                    }
                }

                txaDatos.setText(contenido.toString()); // Mostrar TODO (podría tardar unos segundos)
                System.out.println("Total de números cargados: " + listaNumeros.size());

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
    // Listas enlazadas
    // Pasar de ArrayList a LinkedList para la lista enlazada
    LinkedList<Integer> listaEnlazada = new LinkedList<>(listaNumeros);

    // Mostrar en JTextArea existente
    txtSalida.setText(""); // Limpia el área de texto
    

    for (Integer numero : listaEnlazada) {
        txtSalida.append(numero + "\n");
    }
    
    //Tabla HASH
    HashMap<Integer, Integer> tablaHash = new HashMap<>();
    int posicion = 0;
    for (Integer numero : listaEnlazada) {
        tablaHash.put(posicion, numero);
        posicion++;
    }

    // Mostrar tabla hash en el JTextArea
    txtHashSalida.setText(""); // Limpia el JTextArea
    for (Map.Entry<Integer, Integer> entrada : tablaHash.entrySet()) {
        txtHashSalida.append("Clave: " + entrada.getKey() + " → Valor: " + entrada.getValue() + "\n");
    }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    // Verificar que hay datos cargados
    if (listaNumeros.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No hay datos cargados para eliminar", 
            "Sin datos", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
  
    String input = JOptionPane.showInputDialog(this, 
        "Ingrese el número que desea eliminar:", 
        "Eliminar número", 
        JOptionPane.QUESTION_MESSAGE);
    
    // Verificar que el usuario no canceló y que ingresó algo
    if (input == null || input.trim().isEmpty()) {
        return;  
    }
    
    try {
        int numeroAEliminar = Integer.parseInt(input.trim());
        
        // Verificar si el número existe en la lista
        if (!listaNumeros.contains(numeroAEliminar)) {
            JOptionPane.showMessageDialog(this, 
                "El número " + numeroAEliminar + " no se encuentra en los datos", 
                "Número no encontrado", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Deshabilitar el botón para evitar múltiples operaciones
        btnEliminar.setEnabled(false);
        
         
        Thread eliminacionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    
                   // eliminar
                    int cantidadEliminada = 0;
                    Iterator<Integer> iterator = listaNumeros.iterator();
                    while (iterator.hasNext()) {
                        if (iterator.next().equals(numeroAEliminar)) {
                            iterator.remove();
                            cantidadEliminada++;
                        }
                    }
                    
                    // actualizar vista
                    actualizarVisualizaciones();
                    // Variables finales para el EventQueue
                    final int cantidadFinal = cantidadEliminada;
                  
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                           JOptionPane.showMessageDialog(frmPrincipal.this, 
                                "Número " + numeroAEliminar + " eliminado por completo.\n" +
                                "Los datos han sido actualizados en ambas tablas.", 
                                "Eliminación completada", 
                                JOptionPane.INFORMATION_MESSAGE);
                            
                            btnEliminar.setEnabled(true);
                        }
                    });
                    
                } catch (Exception e) {
                    // En caso de error, rehabilitar botón
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(frmPrincipal.this, 
                                "Error durante la eliminación: " + e.getMessage(), 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                            btnEliminar.setEnabled(true);
                        }
                    });
                }
            }
        });
        
         
        eliminacionThread.start();
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Por favor ingrese un número válido", 
            "Entrada inválida", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
           String numeroStr = JOptionPane.showInputDialog(this, "Ingrese el número a buscar:", "Búsqueda", JOptionPane.QUESTION_MESSAGE);
    lblbuscarh.setText("00:00:000");
    lblbuscarl.setText("00:00:000");
    if (numeroStr == null || numeroStr.trim().isEmpty()) {
        return; // El usuario canceló o no ingresó nada
    }
    
    try {
        int numeroABuscar = Integer.parseInt(numeroStr.trim());
        
        // Obtener los textos de las áreas de texto
        String textoHash = txtHashSalida.getText();
        String textoNormal = txtSalida.getText();
        
        // Crear hilos para cada tipo de búsqueda
        Thread hashThread = new Thread(() -> {
            System.out.println("[HASH] Iniciando búsqueda en tabla hash...");
            long inicio = System.nanoTime();
            boolean encontrado = buscarEnTablaHash(textoHash, numeroABuscar);
            long fin = System.nanoTime();
            String tiempoFormateado = formatarTiempo(fin - inicio);
            System.out.println("[HASH] Búsqueda completada en " + tiempoFormateado);
            lblbuscarh.setText(tiempoFormateado);
            System.out.println("[HASH] Resultado: " + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO"));
            
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, 
                    "Tabla Hash: " + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO") + 
                    "\nTiempo: " + tiempoFormateado,
                    "Resultado Búsqueda", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
        });
        
        Thread normalThread = new Thread(() -> {
            System.out.println("[ENLAZADA] Iniciando búsqueda en tabla enlazada...");
            long inicio = System.nanoTime();
            boolean encontrado = buscarEnTablaEnlazada(textoNormal, numeroABuscar);
            long fin = System.nanoTime();
            String tiempoFormateado = formatarTiempo(fin - inicio);
            System.out.println("[ENLAZADA] Búsqueda completada en " + tiempoFormateado);
            lblbuscarl.setText(tiempoFormateado);
            System.out.println("[ENLAZADA] Resultado: " + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO"));
            
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, 
                    "Tabla Enlazada: " + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO") + 
                    "\nTiempo: " + tiempoFormateado,
                    "Resultado Búsqueda", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
        });
        
        // Iniciar ambos hilos
        hashThread.start();
        normalThread.start();
        
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
             // Pedir el número a insertar mediante un InputDialog
    String input = JOptionPane.showInputDialog(this, "Ingrese el número a insertar:", "Agregar Elemento", JOptionPane.QUESTION_MESSAGE);
    
    // Validar si se ingresó un valor
    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No se ingresó ningún número", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        int numero = Integer.parseInt(input.trim());
        
        // Procesar Tabla Hash (txtHashSalida)
        long inicioHash = System.nanoTime();
        procesarTablaHash(numero);
        long finHash = System.nanoTime();
        long tiempoHash = finHash - inicioHash;
        
        // Procesar Lista Enlazada (txtSalida)
        long inicioLista = System.nanoTime();
        procesarListaEnlazada(numero);
        long finLista = System.nanoTime();
        long tiempoLista = finLista - inicioLista;
        
        // Mostrar tiempos de ejecución
        String tiempoHashStr = formatTiempo(tiempoHash);
        String tiempoListaStr = formatTiempo(tiempoLista);
        
        JOptionPane.showMessageDialog(this, 
            "Tiempos de inserción:\n" +
            "• Tabla Hash: " + tiempoHashStr + "\n" +
            "• Lista Enlazada: " + tiempoListaStr,
            "Tiempos de Ejecución", 
            JOptionPane.INFORMATION_MESSAGE);
            
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

     private void preguntarCargarArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblagregarh;
    private javax.swing.JLabel lblagregarl;
    private javax.swing.JLabel lblbuscarh;
    private javax.swing.JLabel lblbuscarl;
    private javax.swing.JLabel lbleliminarh;
    private javax.swing.JLabel lbleliminarl;
    private javax.swing.JLabel lblmostrarh;
    private javax.swing.JLabel lblmostrarl;
    private javax.swing.JTextArea txaDatos;
    private javax.swing.JTextArea txtHashSalida;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
