package sgomez.ejercicios.apuntaextra.Repositories;

import java.util.ArrayList;

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
}
