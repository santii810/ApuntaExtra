package sgomez.ejercicios.apuntaextra.Repositories;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Camarero;

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

    public ArrayList<Camarero> getCamareros() {
        ArrayList<Camarero> camareros = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.orderByAscending(T_NOMBRE);
        try {
            List<ParseObject> result = query.find();
            for (ParseObject object : result) {
                camareros.add(rellenar(object));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return camareros;
    }


    public Camarero getCamarero(String objectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_ID, objectId);
        try {
            List<ParseObject> result = query.find();
//            if (result.size() > 0) return null;
            return rellenar(result.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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

    private Camarero rellenar(ParseObject result) {
        Camarero camarero = new Camarero();
        camarero.setObjectId(result.getObjectId());
        camarero.setNombre(result.getString(T_NOMBRE));
        camarero.setDireccion(result.getString(T_DIRECCION));
        camarero.setActivo(result.getBoolean(T_ACTIVO));
        return camarero;
    }
}
