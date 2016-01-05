package sgomez.ejercicios.apuntaextra.Repositories;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.PagoAsociado;

/**
 * Created by sgomez on 30/12/2015.
 */
public class MemoryRepositories {

    public ArrayList<String> getTiposServicioHabitual() {
        ArrayList<String> retorno = new ArrayList<>();
        retorno.add("Banquetes");
        retorno.add("Barra");
        retorno.add("Carta");
        retorno.add("Cafeteria");
        retorno.add("Catering");
        return retorno;
    }

    public ArrayList<PagoAsociado> getPagosAsociados() {
        ArrayList<PagoAsociado> pagos = new ArrayList<>();
        pagos.add(new PagoAsociado("Extra completa", 5.0));
        pagos.add(new PagoAsociado("Media extra", 2.5));
        pagos.add(new PagoAsociado("No", 0.0));

        return pagos;
    }

    public PagoAsociado getPagoAsociado(String pagoAsociado) {
        for (PagoAsociado item : this.getPagosAsociados()) {
            if (item.getNombre().equals(pagoAsociado)) return item;
        }
        return null;
    }

    public ArrayList<String> getFestividad() {
        ArrayList<String> festividades = new ArrayList<>();
        festividades.add("Boda");
        festividades.add("Comunion");
        festividades.add("Carta");
        festividades.add("Bautizo");
        festividades.add("Boda de plata");
        festividades.add("Boda de oro");
        festividades.add("Fiesta");
        return festividades;
    }
}
