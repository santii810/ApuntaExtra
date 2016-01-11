package sgomez.ejercicios.apuntaextra.Model;

/**
 * Created by sgomez on 28/12/2015.
 * Clase de locales
 */
public class Local extends Entorno {
    private String Descripcion;
    private double latitude;
    private double longitude;
    private String ServicioHabitual;

    public Local() {    }

    public Local(String objectId, String nombre) {
        super(objectId,nombre);
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getServicioHabitual() {
        return ServicioHabitual;
    }

    public void setServicioHabitual(String servicioHabitual) {
        ServicioHabitual = servicioHabitual;
    }
}
