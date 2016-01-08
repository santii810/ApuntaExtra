package sgomez.ejercicios.apuntaextra.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import sgomez.ejercicios.apuntaextra.Model.Extra;
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
        View item = inflater.inflate(R.layout.view_extras, null);
        onEntrada(datos.get(position), item);
        return item;
    }


    public void onEntrada(Extra entrada, View view) {
        TextView local = (TextView) view.findViewById(R.id.textViewLocalViewExtras);
        local.setText(entrada.getLocal().getNombre());
        TextView dia = (TextView) view.findViewById(R.id.textViewDiaViewExtras);
        dia.setText(getDayOfTheWeek(entrada.getFecha()));
        TextView fecha = (TextView) view.findViewById(R.id.textViewFechaViewExtras);
        fecha.setText(formatearFecha(entrada.getFecha()));

    }

    private static String getDayOfTheWeek(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        cal.setTime(d);
        return dias[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    private static String formatearFecha(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(fecha);
    }
}
