package sgomez.ejercicios.apuntaextra.Activities.VisualizarDatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;

public class ViewExtraDataActivity extends AppCompatActivity {

    private Extra extra;

    private EditText etFecha;
    private TextView tvMomentoDia;
    private EditText etLocal;
    private EditText etCobrado;
    private EditText etPropina;
    private EditText etTiempo;
    private EditText etPagoAsociado;
    private EditText etNotas;
    private EditText etFestividad;
    private EditText etCocina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extra_data);

        extra = MainActivity.getExtraRepository().getExtra(getIntent().getStringExtra("objectId"));
        etFecha = (EditText) findViewById(R.id.editTextViewExtraFecha);
        tvMomentoDia = (TextView) findViewById(R.id.textViewViewExtraMomentoDia);
        etLocal = (EditText) findViewById(R.id.editTextViewExtraLocalName);
        etCobrado = (EditText) findViewById(R.id.editTextViewCobrado);
        etPropina = (EditText) findViewById(R.id.editTextViewPropina);
        etTiempo = (EditText) findViewById(R.id.editTextViewTiempo);
        etPagoAsociado = (EditText) findViewById(R.id.editTextViewPagoAsoc);
        etNotas = (EditText) findViewById(R.id.editTextViewNotas);
        etFestividad = (EditText) findViewById(R.id.editTextViewFestividad);
        etCocina = (EditText) findViewById(R.id.editTextViewCocina);
    }

    @Override
    protected void onResume() {
        super.onResume();

        etFecha.setText(formatearFecha(extra.getFecha()));
        etLocal.setText(extra.getLocal().getNombre());
        etPagoAsociado.setText(extra.getPagoAsociado().getNombre() + " ("
                + extra.getPagoAsociado().getCantidad() + ")");
        etCobrado.setText((Integer.toString(extra.getCobrado())));

        if (extra.getMomentoDia().equals(""))
            tvMomentoDia.setVisibility(View.GONE);
        else if (extra.getMomentoDia().equals("Dia")) {
            tvMomentoDia.setText("Dia");
        } else if (extra.getMomentoDia().equals("Noche")) {
            tvMomentoDia.setText("Noche");
        }
        if (extra.getTiempo() != 0) {
            etTiempo.setText(Double.toString(extra.getTiempo()));
        } else {
            findViewById(R.id.linearLayoutTiempo).setVisibility(View.GONE);
        }
        if (extra.getPropina() != 0) {
            etPropina.setText(Double.toString(extra.getPropina()));
        } else {
            findViewById(R.id.linearLayoutPropina).setVisibility(View.GONE);
        }

        if (extra.getNotas() != null) {
            etNotas.setText(extra.getNotas());
        } else {
            findViewById(R.id.linearLayoutNotas).setVisibility(View.GONE);
        }
        if (extra.getFestividad() != null) {
            etFestividad.setText(extra.getFestividad());
        } else {
            findViewById(R.id.linearLayoutFestividad).setVisibility(View.GONE);
        }
        if (extra.getCocina() != null) {
            etCocina.setText(extra.getCocina().getNombre());
        } else {
            findViewById(R.id.linearLayoutCocina).setVisibility(View.GONE);
        }
    }

    private static String formatearFecha(Date fecha) {
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        String[] dias = {"Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"};
        cal.setTime(fecha);
        return dias[cal.get(Calendar.DAY_OF_WEEK) - 1] + " " + formateador.format(fecha);
    }
}
