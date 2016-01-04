package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;

/**
 * Created by sgomez on 03/01/2016.
 */
public class ParseCocinaRepositorio {
    private final String DBNAME = "Cocina";
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


    public ArrayList<Cocina> getCocinas() {
        ArrayList<Cocina> cocinas = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.orderByAscending(T_NOMBRE);
        try {
            List<ParseObject> result = query.find();
            for (ParseObject object : result) {
                cocinas.add(rellenar(object));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cocinas;
    }



    public Cocina getCocina(String objectId) {
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

    private Cocina rellenar(ParseObject result) {
        Cocina cocina = new Cocina();
        cocina.setObjectId(result.getObjectId());
        cocina.setNombre(result.getString(T_NOMBRE));
        cocina.setDireccion(result.getString(T_DIRECCION));
        cocina.setActivo(result.getBoolean(T_ACTIVO));
        return cocina;
    }
}