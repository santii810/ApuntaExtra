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
    }

}
