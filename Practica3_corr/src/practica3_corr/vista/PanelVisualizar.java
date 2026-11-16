package Practica3_corr.vista;

import Practica3_corr.controlador.ListaCliente;
import practica3.modelo.Cliente;

import javax.swing.*;
import java.awt.*;

/*
 Panel de la interfaz gráfica para consultar y borrar clientes.
  Muestra los datos del cliente actual de la {@link ListaCliente} compartida
  y ofrece botones para moverse por la lista (primero, anterior, siguiente,
  último) y para borrar el cliente que se está viendo.
 */
public class PanelVisualizar extends javax.swing.JPanel {

    private ListaCliente clientes;

    /* Etiqueta que muestra el número del cliente actual. */
    private JLabel lblNumero;

    /* Etiqueta que muestra el nombre del cliente actual. */
    private JLabel lblNombre;

    /* Etiqueta que muestra los puntos del cliente actual. */
    private JLabel lblPuntos;

    /* Etiqueta que muestra la fecha de nacimiento del cliente actual. */
    private JLabel lblFecha;

    /*
     Etiqueta de estado situada bajo los botones. Muestra en rojo el aviso
     "No hay clientes registrados" cuando la lista está vacía.
     */
    private JLabel lblError;

    private JButton btnPrimero;

    private JButton btnAnterior;

    private JButton btnSiguiente;

    private JButton btnUltimo;

    private JButton btnBorrar;

    /*
     Construye el panel, crea todos sus componentes, conecta los botones
     con sus acciones y muestra el cliente actual (si lo hay).
     */
    public PanelVisualizar(ListaCliente clientes) {
        this.clientes = clientes;

        initComponents();

        // Sustituye el layout generado por un BorderLayout con 10 px de separación
        setLayout(new BorderLayout(10, 10));

        // Título centrado en la zona superior
        JLabel titulo = new JLabel("VISUALIZAR CLIENTES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        // Cuadrícula de 4 filas x 2 columnas: texto fijo a la izquierda,
        JPanel datos = new JPanel(new GridLayout(4, 2, 10, 15));

        datos.add(new JLabel("Número:"));
        lblNumero = new JLabel();
        datos.add(lblNumero);

        datos.add(new JLabel("Nombre:"));
        lblNombre = new JLabel();
        datos.add(lblNombre);

        datos.add(new JLabel("Puntos:"));
        lblPuntos = new JLabel();
        datos.add(lblPuntos);

        datos.add(new JLabel("Fecha de nacimiento:"));
        lblFecha = new JLabel();
        datos.add(lblFecha);

        // FlowLayout coloca los botones en fila, centrados
        JPanel botones = new JPanel(new FlowLayout());

        btnPrimero = new JButton("Primero");
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnUltimo = new JButton("Último");
        btnBorrar = new JButton("Borrar");

        botones.add(btnPrimero);
        botones.add(btnAnterior);
        botones.add(btnSiguiente);
        botones.add(btnUltimo);
        botones.add(btnBorrar);

        // Se inicializa con un espacio para que la etiqueta reserve su altura
        lblError = new JLabel(" ", JLabel.CENTER);
        lblError.setForeground(Color.RED);

        // Zona inferior: botones arriba y etiqueta de estado debajo
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(botones, BorderLayout.CENTER);
        inferior.add(lblError, BorderLayout.SOUTH);

        add(titulo, BorderLayout.NORTH);
        add(datos, BorderLayout.CENTER);
        add(inferior, BorderLayout.SOUTH);

        // Cada botón de navegación mueve el cursor de la lista y refresca la pantalla
        // (las lambdas "e -> {...}" son la forma corta de escribir un listener)
        btnPrimero.addActionListener(e -> {
            clientes.irPrimero();
            actualizar();
        });

        btnAnterior.addActionListener(e -> {
            clientes.retroceder();
            actualizar();
        });

        btnSiguiente.addActionListener(e -> {
            clientes.avanzar();
            actualizar();
        });

        btnUltimo.addActionListener(e -> {
            clientes.irUltimo();
            actualizar();
        });

        // Borrar solo actúa si hay clientes; después se refresca para mostrar
        // el cliente que queda como actual (o el aviso de lista vacía)
        btnBorrar.addActionListener(e -> {

            if (!clientes.esVacia()) {
                clientes.borrarCliente();
                actualizar();
            }
        });

        // Muestra el cliente actual nada más crear el panel
        actualizar();
    }

    /*
    Refresca las etiquetas con los datos del cliente actual de la lista.
     Si la lista está vacía, deja los datos en blanco y muestra el aviso
    "No hay clientes registrados". En caso contrario, rellena los datos
     y limpia el aviso.
     */
    private void actualizar() {

        // obtenerActual() devuelve null si la lista está vacía
        Cliente cliente = clientes.obtenerActual();

        if (cliente == null) {

            lblNumero.setText("");
            lblNombre.setText("");
            lblPuntos.setText("");
            lblFecha.setText("");

            lblError.setText("No hay clientes registrados");

        } else {

            // Los números y la fecha se convierten a texto para poder mostrarlos
            lblNumero.setText(String.valueOf(cliente.getNumero()));
            lblNombre.setText(cliente.getNombre());
            lblPuntos.setText(String.valueOf(cliente.getPuntos()));
            lblFecha.setText(cliente.getFechaNacimiento().toString());

            lblError.setText(" ");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
