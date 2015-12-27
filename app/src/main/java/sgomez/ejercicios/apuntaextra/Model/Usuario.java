package sgomez.ejercicios.apuntaextra.Model;

import android.net.Uri;

/**
 * Created by sgomez on 27/12/2015.
 */
public class Usuario {
    private String ObjectId;
    private String Nombre;
    private String Correo;
    private String GoogleId;
    private Uri Foto;

    public Usuario() {
    }

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

    public String getGoogleId() {
        return GoogleId;
    }

    public void setGoogleId(String googleId) {
        GoogleId = googleId;
    }

    public Uri getFoto() {
        return Foto;
    }

    public void setFoto(Uri foto) {
        Foto = foto;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
