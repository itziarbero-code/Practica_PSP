package Practica3_corr.vista;

import Practica3_corr.controlador.ListaCliente;
import Practica3_corr.controlador.ControlErrores;
import practica3.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Panel de la interfaz gráfica para dar de alta un cliente nuevo.
 * Muestra un formulario con cuatro campos (número, nombre, puntos y fecha),
 * valida lo que escribe el usuario y, si todo es correcto, añade el cliente
 * a la lista compartida.
 */
public class PanelNuevo extends javax.swing.JPanel {

    private ListaCliente clientes;
    private JTextField txtNumero;
    private JTextField txtNombre;
    private JTextField txtPuntos;
    private JTextField txtFecha;

    private JLabel lblError;

    public PanelNuevo(ListaCliente clientes) {
        this.clientes = clientes;

        // Método generado por el editor visual de NetBeans (no tocar)
        initComponents();

        // BorderLayout divide el panel en zonas (NORTH, CENTER, SOUTH...).
        setLayout(new BorderLayout(10, 10));

        // Título centrado en la parte superior
        JLabel titulo = new JLabel("NUEVO CLIENTE", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        // GridLayout(4, 2, 10, 15): cuadrícula de 4 filas y 2 columnas,
        // con 10 px de separación horizontal y 15 px vertical.
        // Cada fila lleva una etiqueta a la izquierda y su campo a la derecha.
        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 15));

        formulario.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        formulario.add(txtNumero);

        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Puntos:"));
        txtPuntos = new JTextField();
        formulario.add(txtPuntos);

        formulario.add(new JLabel("Fecha de nacimiento (AAAA-MM-DD):"));
        txtFecha = new JTextField();
        formulario.add(txtFecha);

        JButton btnGuardar = new JButton("Guardar cliente");

        // Empieza con un espacio en blanco para que la etiqueta reserve su altura
        // aunque todavía no haya ningún mensaje. Texto rojo por defecto.
        lblError = new JLabel(" ", JLabel.CENTER);
        lblError.setForeground(Color.RED);

        // Panel inferior: el botón ocupa el centro y el mensaje queda debajo
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(btnGuardar, BorderLayout.CENTER);
        inferior.add(lblError, BorderLayout.SOUTH);

        // Se colocan las tres partes en el panel principal
        add(titulo, BorderLayout.NORTH);
        add(formulario, BorderLayout.CENTER);
        add(inferior, BorderLayout.SOUTH);

        // Al pulsar el botón se ejecuta guardarCliente().
        // "e -> ..." es una expresión lambda: una forma corta de escribir el listener.
        btnGuardar.addActionListener(e -> guardarCliente());
    }

    /**
     * Valida los datos del formulario y, si son correctos, crea el cliente
     * y lo añade a la lista. Si algún dato falla, muestra el error y se detiene.
     */
    private void guardarCliente() {

        String mensaje;

        // Se valida campo por campo, en orden. ControlErrores devuelve null si
        // el dato es correcto o un mensaje de error si no lo es.
        // En cuanto hay un error se muestra y se sale (return), de modo
        // que el usuario ve un solo error cada vez.
        mensaje = ControlErrores.validarNumero(txtNumero.getText());

        if (mensaje != null) {
            mostrarError(mensaje);
            return;
        }

        mensaje = ControlErrores.validarNombre(txtNombre.getText());

        if (mensaje != null) {
            mostrarError(mensaje);
            return;
        }

        mensaje = ControlErrores.validarPuntos(txtPuntos.getText());

        if (mensaje != null) {
            mostrarError(mensaje);
            return;
        }

        mensaje = ControlErrores.validarFecha(txtFecha.getText());

        if (mensaje != null) {
            mostrarError(mensaje);
            return;
        }

        // Llegados aquí, los cuatro datos son válidos, así que se pueden
        // convertir sin riesgo de que las conversiones lancen excepciones.
        int numero = Integer.parseInt(txtNumero.getText());
        // trim() elimina espacios sobrantes al principio y al final del nombre
        String nombre = txtNombre.getText().trim();
        float puntos = Float.parseFloat(txtPuntos.getText());
        LocalDate fecha = LocalDate.parse(txtFecha.getText());

        // Comprueba que no exista ya otro cliente con el mismo número
        // (el número funciona como identificador, no puede repetirse)
        for (Cliente c : clientes) {

            if (c.getNumero() == numero) {
                mostrarError("Ya existe un cliente con ese número");
                return;
            }
        }

        // Crea el cliente con los datos ya validados
        Cliente cliente = new Cliente(
                numero,
                nombre,
                puntos,
                fecha
        );

        // Lo añade a la lista (y pasa a ser el cliente actual)
        clientes.anadirCliente(cliente);

        // Mensaje de confirmación en verde
        lblError.setForeground(new Color(0, 128, 0));
        lblError.setText("Cliente añadido correctamente");

        // Vacía el formulario para poder introducir otro cliente
        txtNumero.setText("");
        txtNombre.setText("");
        txtPuntos.setText("");
        txtFecha.setText("");
    }

    /**
     * Muestra un mensaje de error en rojo en la etiqueta inferior.
     * Se vuelve a poner el color rojo porque tras un guardado correcto
     * la etiqueta se quedó en verde.
     *
     * @param mensaje texto del error que se mostrará al usuario
     */
    private void mostrarError(String mensaje) {

        lblError.setForeground(Color.RED);
        lblError.setText(mensaje);
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
