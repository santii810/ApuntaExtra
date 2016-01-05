package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.PagoAsociado;
import sgomez.ejercicios.apuntaextra.R;

public class AddExtra2Activity extends AppCompatActivity {

    private RadioGroup pagoAsociado;
    private ArrayList<PagoAsociado> pagos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra2);

        pagoAsociado = (RadioGroup) findViewById(R.id.radioGroupPagoAsociado);
        pagos = MainActivity.getMemoryRepositories().getPagosAsociados();

//Establezco parametros del layout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        float density = getResources().getDisplayMetrics().density;
        params.setMargins((int) (15 * density), (int) (15 * density), (int) (15 * density), (int) (15 * density));
//Creo un radioButton por cada uno de los pagos asociados
        for (PagoAsociado item : pagos) {
            RadioButton rb = new RadioButton(this);
            rb.setText(item.getNombre());
            rb.setLayoutParams(params);


            pagoAsociado.addView(rb);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_extra1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addExtra1MenuNext:
                next();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void next() {
        
    }
}
