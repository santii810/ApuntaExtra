package sgomez.ejercicios.apuntaextra.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

/**
 * Created by sgomez on 30/12/2015.
 */
public class Adapter_simple_spinner extends BaseAdapter {

    private ArrayList entradas;
    private int idView;
    private Context contexto;


    public Adapter_simple_spinner(Context contexto, int idView, ArrayList entradas) {
        this.entradas = entradas;
        this.contexto = contexto;
        this.idView = idView;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int position) {
        return entradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(idView, null);
            onEntrada(entradas.get(position), convertView);
        }
        return convertView;
    }

    public void onEntrada(Object entrada, View view) {
        if (entrada instanceof String) {
            ((TextView) view.findViewById(R.id.textViewSimpleSpinnerView)).setText((String) entrada);
        }
        if (entrada instanceof Local) {
            ((TextView) view.findViewById(R.id.textViewSimpleSpinnerView)).setText(((Local) entrada).getNombre());
        }
        if (entrada instanceof Cocina) {
            ((TextView) view.findViewById(R.id.textViewSimpleSpinnerView)).setText(((Cocina) entrada).getNombre());
        }
    }
}
