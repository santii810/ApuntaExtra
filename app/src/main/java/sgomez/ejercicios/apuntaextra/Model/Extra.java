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
    private int cobrado;
    private double propina;
    private double tiempo;
    private PagoAsociado pagoAsociado;
    private String notas;


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

    public int getCobrado() {
        return cobrado;
    }

    public void setCobrado(int cobrado) {
        this.cobrado = cobrado;
    }

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public PagoAsociado getPagoAsociado() {
        return pagoAsociado;
    }

    public void setPagoAsociado(PagoAsociado pagoAsociado) {
        this.pagoAsociado = pagoAsociado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
