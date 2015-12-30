package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseObject;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;

/**
 * Created by sgomez on 29/12/2015.
 */
public class ParseLocalRepository {
    private final String DBNAME = "Locales";
    private final String T_ID = "objectId";
    private final String T_NOMBRE = "nombreLocal";
    private final String T_DIRECCION = "direccionLocal";
    private final String T_LATITUDE = "latitude";
    private final String T_LONGITUDE = "longitude";
    private final String T_INSERCION = "insertadoPor";

    public void addLocal(Local local) {
        ParseObject parseObject = new ParseObject(DBNAME);
        parseObject.put(T_NOMBRE, local.getNombre());
        parseObject.put(T_DIRECCION, local.getDireccion());
        parseObject.put(T_LATITUDE, local.getLatitude());
        parseObject.put(T_LONGITUDE, local.getLongitude());
        parseObject.put(T_INSERCION, MainActivity.getUsuario().getObjectId());

        parseObject.saveInBackground();
    }
}
