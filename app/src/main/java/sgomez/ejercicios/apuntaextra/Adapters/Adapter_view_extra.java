package sgomez.ejercicios.apuntaextra.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sgomez.ejercicios.apuntaextra.Model.Camarero;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.Model.Entorno;
import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

/**
 * Created by dam209 on 07/01/2016.
 * Adapter para view_extra
 */
public class Adapter_view_extra extends ArrayAdapter {


    private ArrayList<Extra> datos;
    private Context context;

    public Adapter_view_extra(Context context, ArrayList<Extra> entradas) {
        super(context, R.layout.view_extras, entradas);
        this.context = context;
        this.datos = entradas;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.view_item_subitem, null);
        onEntrada(datos.get(position), item);
        return item;
    }


    public void onEntrada(Extra entrada, View view) {
        TextView local = (TextView) view.findViewById(R.id.textViewLocalViewExtras);
        local.setText((entrada).getLocal().getNombre());
        TextView dia = (TextView) view.findViewById(R.id.textViewDiaViewExtras);
        dia.setText(getDayOfTheWeek((Date) entrada.getFecha()));
        TextView fecha = (TextView) view.findViewById(R.id.textViewFechaViewExtras);
        fecha.setText(entrada.getFecha().toString());

    }

    public static int getDayOfTheWeek(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

}
