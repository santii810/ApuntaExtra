package sgomez.ejercicios.apuntaextra.Model;

import java.util.Date;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;

/**
 * Created by sgomez on 05/01/2016.
 */
public class Extra {
    private Date fecha;
    private String ObjectId;
    private Local local;
    private Usuario usuario;

    public Extra() {
        this.usuario = MainActivity.getUsuario();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
