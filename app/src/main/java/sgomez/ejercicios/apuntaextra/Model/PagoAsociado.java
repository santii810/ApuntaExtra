package sgomez.ejercicios.apuntaextra.Model;

/**
 * Created by sgomez on 05/01/2016.
 */
public class PagoAsociado {
    private String nombre;
    private double cantidad;

    public PagoAsociado(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;

    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }
}
