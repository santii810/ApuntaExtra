package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;

/**
 * Created by sgomez on 03/01/2016.
 */
public class ParseCamareroRepository {
    private final String DBNAME = "Camareros";
    private final String T_ID = "objectId";
    private final String T_NOMBRE = "nombreCamarero";
    private final String T_DIRECCION = "direccionCamarero";
    private final String T_ACTIVO = "activo";
    private final String T_INSERCION = "insertadoPor";

    public boolean addCamarero(Camarero camarero) {
        if (existeCamarero(camarero.getNombre())) return false;
        else {
            ParseObject parseObject = new ParseObject(DBNAME);
            parseObject.put(T_NOMBRE, camarero.getNombre());
            parseObject.put(T_DIRECCION, camarero.getDireccion());
            parseObject.put(T_ACTIVO, camarero.isActivo());
            parseObject.put(T_INSERCION, MainActivity.getUsuario().getObjectId());
            parseObject.saveInBackground();
            return true;
        }
    }


    private boolean existeCamarero(String nombreCamarero) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_NOMBRE, nombreCamarero);
        try {
            return query.find().size() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
