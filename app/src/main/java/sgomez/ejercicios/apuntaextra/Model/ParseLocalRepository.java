package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

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
    private final String T_DESCRIPCION = "Descripcion";
    private final String T_SERVICIO_HABITUAL = "ServicioHabitual";

    public boolean addLocal(Local local) {
        if (existeLocal(local.getNombre()))
            return false;
        else {
            ParseObject parseObject = new ParseObject(DBNAME);
            parseObject.put(T_NOMBRE, local.getNombre());
            parseObject.put(T_DIRECCION, local.getDireccion());
            parseObject.put(T_DESCRIPCION, local.getDescripcion());
            parseObject.put(T_LATITUDE, local.getLatitude());
            parseObject.put(T_LONGITUDE, local.getLongitude());
            parseObject.put(T_SERVICIO_HABITUAL, local.getServicioHabitual());
            parseObject.put(T_INSERCION, MainActivity.getUsuario().getObjectId());
            parseObject.saveInBackground();
            return true;
        }
    }

    public ArrayList<Local> getLocales() {
        ArrayList<Local> locales = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.orderByAscending(T_NOMBRE);
        try {
            List<ParseObject> result = query.find();
            for (ParseObject object : result) {
                locales.add(rellenaLocal(object));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locales;
    }

    public Local getLocal(String objectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_ID, objectId);
        try {
            List<ParseObject> result = query.find();
//            if (result.size() > 0) return null;
            return rellenaLocal(result.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Local rellenaLocal(ParseObject result) {
        Local local = new Local();
        local.setObjectId(result.getObjectId());
        local.setNombre(result.getString(T_NOMBRE));
        local.setDireccion(result.getString(T_DIRECCION));
        local.setLatitude(result.getDouble(T_LATITUDE));
        local.setLongitude(result.getDouble(T_LONGITUDE));
        local.setDescripcion(result.getString(T_DESCRIPCION));
        local.setServicioHabitual(result.getString(T_SERVICIO_HABITUAL));
        return local;
    }


    private boolean existeLocal(String nombreLocal) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_NOMBRE, nombreLocal);
        try {
            return query.find().size() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
