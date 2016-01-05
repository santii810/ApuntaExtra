package sgomez.ejercicios.apuntaextra.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

public class AddExtra1Activity extends AppCompatActivity {

    private Spinner spinnerLocales;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra1);


        spinnerLocales = ((Spinner) findViewById(R.id.spinnerLocales));
        datePicker = (DatePicker) findViewById(R.id.addExtraDatePicker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Local> locales = MainActivity.getLocalRepository().getLocales();
        locales.add(0, new Local());
        locales.get(0).setNombre("Selecciona local");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locales);
        spinnerLocales.setAdapter(adapter);
    }

    public void buttonAddExtra1NextOnClick(View view) {
        if (((Local) spinnerLocales.getSelectedItem()).getNombre().equals("Selecciona local")) {
            Toast.makeText(AddExtra1Activity.this, "Debes seleccionar un local", Toast.LENGTH_LONG).show();
        } else {
            Intent backData = new Intent();
            backData.putExtra("objectId", ((Local) spinnerLocales.getSelectedItem()).getObjectId());
            backData.putExtra("año", datePicker.getYear() - 1900);
            backData.putExtra("mes", datePicker.getMonth());
            backData.putExtra("dia", datePicker.getDayOfMonth());
            setResult(Activity.RESULT_OK, backData);
            finish();
        }


    }

}
