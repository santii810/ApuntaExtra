package sgomez.ejercicios.apuntaextra.Model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by sgomez on 27/12/2015.
 */
public class ParseUsuarioRepository {
    private final String DBNAME = "Usuarios";
    private final String T_ID = "objectId";
    private final String T_NOMBRE = "NombreUsuario";
    private final String T_CORRREO = "Correo";
    private final String T_GOOGLEID = "GoogleId";

    public ParseUsuarioRepository() {
    }


    public String addUsuario(Usuario usuario) {
        if (!existeUsuario(usuario.getGoogleId())) {
            ParseObject parseObject = new ParseObject(DBNAME);
            parseObject.put(T_NOMBRE, usuario.getNombre());
            parseObject.put(T_CORRREO, usuario.getCorreo());
            parseObject.put(T_GOOGLEID, usuario.getGoogleId());
            try {
                parseObject.save();
                return parseObject.getObjectId();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return getObjectId(usuario.getGoogleId());
    }


    private boolean existeUsuario(String googleId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_GOOGLEID, googleId);
        try {
            return query.find().size() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getObjectId(String googleId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_GOOGLEID, googleId);
        try {
            List<ParseObject> result = query.find();
            if (result.size() > 0) {
                return result.get(0).getObjectId();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
