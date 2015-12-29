package sgomez.ejercicios.apuntaextra.Model;

/**
 * Created by sgomez on 28/12/2015.
 */
public class Local extends Entorno {
    private String Direccion;
    private double latitude;
    private double longitude;

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
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
}
