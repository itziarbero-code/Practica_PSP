package practica3.modelo;

import java.time.LocalDate;

public class Cliente {
    private int numero;
    private String nombre;
    private float puntos;
    private LocalDate fechaNacimiento;

    public Cliente(int numero, String nombre, float puntos, LocalDate fechaNacimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.puntos = puntos;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor vacío: crea un cliente sin datos.
     * Los atributos quedan con sus valores por defecto
     * (0, null, 0.0 y null) y se pueden rellenar después con los setters.
     */
    public Cliente() {
    }

    /** @return el número identificador del cliente */
    public int getNumero() {
        return numero;
    }

    /** @param numero nuevo número identificador del cliente */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /** @return el nombre del cliente */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre nuevo nombre del cliente */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return los puntos acumulados del cliente */
    public float getPuntos() {
        return puntos;
    }

    /** @param puntos nueva cantidad de puntos del cliente */
    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    /** @return la fecha de nacimiento del cliente */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /** @param fechaNacimiento nueva fecha de nacimiento del cliente */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Devuelve una representación en texto del cliente,
     * con cada dato en una línea distinta (\n es un salto de línea).
     * Es útil para mostrar el cliente por consola con System.out.println.
     */
    @Override
    public String toString() {
        return "Número: " + numero
                + "\nNombre: " + nombre
                + "\nPuntos: " + puntos
                + "\nFecha de nacimiento: " + fechaNacimiento;
    }

}