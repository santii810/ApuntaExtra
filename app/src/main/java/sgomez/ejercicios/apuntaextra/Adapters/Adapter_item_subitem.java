package sgomez.ejercicios.apuntaextra.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.Camarero;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.Model.Entorno;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

/**
 * Created by dam209 on 03/12/2015.
 * Adapter con 2 textViews tomando el patron de item y subitem
 */
public class Adapter_item_subitem extends ArrayAdapter {

    private ArrayList datos;
    private Context context;

    public Adapter_item_subitem(Context context, ArrayList entradas) {
        super(context, R.layout.view_item_subitem, entradas);
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
        onEntrada((Entorno) datos.get(position), item);
        return item;
    }

    public void onEntrada(Entorno entrada, View view) {
        if (entrada instanceof Local || entrada instanceof Camarero || entrada instanceof Cocina) {
            TextView titulo = (TextView) view.findViewById(R.id.viewItem);
            titulo.setText((entrada).getNombre());
            TextView subTitulo = (TextView) view.findViewById(R.id.viewSubItem);
            subTitulo.setText(entrada.getDireccion());
        }
    }
}
