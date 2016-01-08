package sgomez.ejercicios.apuntaextra.Activities.AddDatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
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
        getMenuInflater().inflate(R.menu.activity_add_extra_next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addExtraMenuNext:
                next();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void next() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupPagoAsociado);
        EditText editTextCobrado = (EditText) findViewById(R.id.editTextExtraCobrado);


        if (rg.getCheckedRadioButtonId() != -1 && !editTextCobrado.getText().toString().equals("")) {
            Intent backData = new Intent();
            backData.putExtra("cobrado", Integer.parseInt(editTextCobrado.getText().toString()));
            try {
                backData.putExtra("propina", Double.parseDouble(((EditText) findViewById(R.id.editTextExtraPropina)).getText().toString()));
            } catch (Exception ignored) {
            }
            try {
                backData.putExtra("tiempo", Double.parseDouble(((EditText) findViewById(R.id.editTextExtraTiempo)).getText().toString()));
            } catch (Exception ignored) {
            }


            RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            backData.putExtra("pagoAsociado", rb.getText().toString());

            setResult(Activity.RESULT_OK, backData);
            finish();
        } else {
            Toast.makeText(AddExtra2Activity.this, "Debes completar los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
}
