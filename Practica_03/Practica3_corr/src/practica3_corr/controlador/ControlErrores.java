package Practica3_corr.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Clase de utilidad para validar los datos que introduce el usuario.
 * Todos los métodos son estáticos, así que se usan sin crear objetos:
 * ControlErrores.validarNumero("5").
 *
 * Convención común a todos los métodos:
 *  - Devuelven null si el dato es correcto.
 *  - Devuelven un String con el mensaje de error si el dato es incorrecto.
 * Quien los llame solo tiene que comprobar si el resultado es null o no.
 */
public class ControlErrores {

    /**
     * Valida el número identificador del cliente.
     * Debe ser un entero mayor que cero.
     *
     * @param numero texto introducido por el usuario
     * @return null si es válido; mensaje de error en caso contrario
     */
    public static String validarNumero(String numero) {
        try {
            int n = Integer.parseInt(numero);

            if (n <= 0) {
                return "El número debe ser mayor que cero";
            }

        } catch (NumberFormatException e) {
            return "El número debe ser un entero";
        }

        return null;
    }

    /**
     * Valida el nombre del cliente.
     * No puede estar vacío ni contener solo espacios.
     *
     * @param nombre texto introducido por el usuario
     * @return null si es válido; mensaje de error en caso contrario
     */
    public static String validarNombre(String nombre) {
        // trim() elimina los espacios del principio y del final,
        // así un nombre formado solo por espacios también se considera vacío
        if (nombre.trim().isEmpty()) {
            return "El nombre no puede estar vacío";
        }

        return null;
    }

    /**
     * Valida los puntos del cliente.
     * Deben ser un número (puede tener decimales) entre 0 y 1000, ambos incluidos.
     *
     * @param puntos texto introducido por el usuario
     * @return null si es válido; mensaje de error en caso contrario
     */
    public static String validarPuntos(String puntos) {
        try {
            float p = Float.parseFloat(puntos);

            // Float.isNaN detecta el valor especial "Not a Number" (que parseFloat sí acepta
            if (Float.isNaN(p) || p < 0 || p > 1000) {
                return "Los puntos deben estar entre 0 y 1000";
            }

        } catch (NumberFormatException e) {
            return "Los puntos deben ser numéricos";
        }

        return null;
    }

    /**
     * Valida la fecha de nacimiento.
     * Debe tener formato AAAA-MM-DD y el año debe estar entre 1900 y 2015.
     *
     * @param fecha texto introducido por el usuario
     * @return null si es válida; mensaje de error en caso contrario
     */
    public static String validarFecha(String fecha) {
        try {
            LocalDate f = LocalDate.parse(fecha);

            if (f.getYear() < 1900 || f.getYear() > 2015) {
                return "El año debe estar entre 1900 y 2015";
            }

        } catch (DateTimeParseException e) {
            return "Fecha incorrecta. Formato: AAAA-MM-DD";
        }

        return null;
    }
}