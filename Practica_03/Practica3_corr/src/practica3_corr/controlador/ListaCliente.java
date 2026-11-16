package Practica3_corr.controlador;

import java.util.ArrayList;
import java.util.Comparator;
import practica3.modelo.Cliente;

public class ListaCliente extends ArrayList<Cliente> {
    private int actual;

    public ListaCliente() {
        actual = 0;
    }

    /*
     Indica si la lista no tiene clientes.
     @return true si está vacía; false en caso contrario
     */
    public boolean esVacia() {
        return this.isEmpty();
    }

    /*
     Mueve el cursor al siguiente cliente.
     Si ya está en el último o la lista está vacía, no hace nada.
     */
    public void avanzar() {
        if (!isEmpty() && actual < size() - 1) {
            actual++;
        }
    }

    
    // Mueve el cursor al cliente anterior.
    
    public void retroceder() {
        if (!isEmpty() && actual > 0) {
            actual--;
        }
    }

    
    // Coloca el cursor en el primer cliente de la lista.
    public void irPrimero() {
        if (!isEmpty()) {
            actual = 0;
        }
    }

    
    // Coloca el cursor en el último cliente de la lista.
    public void irUltimo() {
        if (!isEmpty()) {
            actual = size() - 1;
        }
    }

    /*
     Indica si el cliente actual es el primero.
     @return true si hay clientes y el cursor está en la posición 0
     */
    public boolean esPrimero() {
        return !isEmpty() && actual == 0;
    }

    /*
     Indica si el cliente actual es el último.
     @return true si hay clientes y el cursor está en la última posición
     */
    public boolean esUltimo() {
        return !isEmpty() && actual == size() - 1;
    }

    
    // Añade un cliente al final de la lista y lo deja como cliente actual.
    public void anadirCliente(Cliente cliente) {
        add(cliente);
        // El cursor pasa al elemento recién añadido (el último)
        actual = size() - 1;
    }

    public void borrarCliente() {
        if (!isEmpty()) {
            remove(actual);

            // Si se borró el último elemento, el índice se queda fuera de rango:
            // se retrocede al nuevo último
            if (actual >= size() && !isEmpty()) {
                actual = size() - 1;
            }

            // Si la lista se ha quedado vacía, el cursor vuelve a 0
            if (isEmpty()) {
                actual = 0;
            }
        }
    }

    public Cliente obtenerActual() {
        if (isEmpty()) {
            return null;
        }

        return get(actual);
    }


    // Ordena la lista de menor a mayor número de cliente
    public void ordenarPorNumero() {
        // Comparator.comparingInt crea un criterio de orden usando el número de cada cliente.
        sort(Comparator.comparingInt(Cliente::getNumero));
        actual = 0;
    }

    // Devuelve la posición (índice) del cliente actual.
    public int getActual() {
        return actual;
    }

}