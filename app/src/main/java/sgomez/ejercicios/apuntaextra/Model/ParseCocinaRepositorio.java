package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;

/**
 * Created by sgomez on 03/01/2016.
 */
public class ParseCocinaRepositorio {
    private final String DBNAME = "Cocinas";
    private final String T_ID = "objectId";
    private final String T_NOMBRE = "nombreCocina";
    private final String T_DIRECCION = "direccionCocina";
    private final String T_ACTIVO = "activo";
    private final String T_INSERCION = "insertadoPor";

    public boolean addCocina(Cocina cocina) {
        if (existeCocina(cocina.getNombre())) return false;
        else {
            ParseObject parseObject = new ParseObject(DBNAME);
            parseObject.put(T_NOMBRE, cocina.getNombre());
            parseObject.put(T_DIRECCION, cocina.getDireccion());
            parseObject.put(T_ACTIVO, cocina.isActivo());
            parseObject.put(T_INSERCION, MainActivity.getUsuario().getObjectId());
            parseObject.saveInBackground();
            return true;
        }
    }


    private boolean existeCocina(String nombreCocina) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_NOMBRE, nombreCocina);
        try {
            return query.find().size() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
