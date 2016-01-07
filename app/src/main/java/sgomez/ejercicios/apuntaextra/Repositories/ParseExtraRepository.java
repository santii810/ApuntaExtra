package sgomez.ejercicios.apuntaextra.Repositories;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Extra;

/**
 * Created by dam209 on 07/01/2016.
 * Repositorio de extras
 */
public class ParseExtraRepository {
    private final String DBNAME = "Extras";
    private final String T_ID = "objectId";
    private final String T_FECHA = "fecha";
    private final String T_LOCAL = "localId";
    private final String T_USUARIO = "userId";
    private final String T_COBRADO = "cobrado";
    private final String T_PROPINA = "propina";
    private final String T_TIEMPO = "tiempo";
    private final String T_PAGO_ASOCIADO = "pagoAsociado";
    private final String T_NOTAS = "notas";

    public String addExtra(Extra extra) {
        ParseObject parseObject = new ParseObject(DBNAME);

        try {
            parseObject.put(T_LOCAL, extra.getLocal().getObjectId());
            parseObject.put(T_USUARIO, extra.getUsuario().getObjectId());
            parseObject.put(T_COBRADO, extra.getCobrado());
            parseObject.put(T_PAGO_ASOCIADO, extra.getPagoAsociado().getCantidad());
            parseObject.put(T_PROPINA, extra.getPropina());
            parseObject.put(T_TIEMPO, extra.getTiempo());
            parseObject.put(T_NOTAS, extra.getNotas());


            parseObject.save();
        } catch (Exception ignored) {

        }
        return parseObject.getObjectId();

    }


    public ArrayList<Extra> getExtras() {
        ArrayList<Extra> extras = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.orderByAscending(T_FECHA);
        try {
            List<ParseObject> result = query.find();
            for (ParseObject object : result) {
                extras.add(rellenar(object));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return extras;
    }

    public Extra getExtra(String objectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DBNAME);
        query.whereEqualTo(T_ID, objectId);
        try {
            List<ParseObject> result = query.find();
            return rellenar(result.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Extra rellenar(ParseObject result) {
        Extra extra = new Extra();
        extra.setObjectId(result.getObjectId());
        extra.setFecha(result.getDate(T_FECHA));
        extra.setLocal(MainActivity.getLocalRepository().getLocal(result.getString(T_LOCAL)));
        extra.setUsuario(MainActivity.getUsuario());
        extra.setCobrado(result.getInt(T_COBRADO));
        extra.setPropina(result.getDouble(T_PROPINA));
        extra.setTiempo(result.getDouble(T_TIEMPO));
        extra.setNotas(result.getString(T_NOTAS));
        return extra;
    }


}
