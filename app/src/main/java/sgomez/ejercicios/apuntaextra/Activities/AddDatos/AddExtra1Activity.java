package sgomez.ejercicios.apuntaextra.Activities.AddDatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Adapters.Adapter_simple_spinner;
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
        Adapter_simple_spinner adapter = new Adapter_simple_spinner(this, R.layout.view_simple_spinner, locales);
        spinnerLocales.setAdapter(adapter);
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

    public void buttonAddExtraAddLocalOnClick(View view) {
        Toast.makeText(AddExtra1Activity.this, "Not supported yet", Toast.LENGTH_SHORT).show();
    }
}
