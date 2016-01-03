package sgomez.ejercicios.apuntaextra.Model;

/**
 * Created by sgomez on 28/12/2015.
 */
public class Entorno {
    private String ObjectId;
    private String Nombre;
    private boolean Activo;

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }
}
