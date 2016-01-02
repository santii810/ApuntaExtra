package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sgomez.ejercicios.apuntaextra.Adapters.Adapter_simple_spinner;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;


public class AddLocalActivity extends AppCompatActivity {

    private static final int GET_MAP_POSITION_REQUEST_CODE = 1;
    private Local local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);
        local = new Local();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Adapter_simple_spinner adapter = new Adapter_simple_spinner(this, R.layout.view_simple_spinner, MainActivity.getMemoryRepositories().getTiposServicioHabitual());
        Spinner spinner = ((Spinner) findViewById(R.id.spinnerhabitualService));
        spinner.setAdapter(adapter);

    }

    public void buttonMapAdressSelectOnClick(View view) {
        startActivityForResult(new Intent(this, AddMapPositionActivity.class), GET_MAP_POSITION_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_MAP_POSITION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                local.setLatitude(data.getDoubleExtra("latitude", 0));
                local.setLongitude(data.getDoubleExtra("longitude", 0));
            }
        }
    }

    public void buttonAddLocalOnClick(View view) {
        if (((EditText) findViewById(R.id.editTextLocalName)).getText().toString().equals("")) {
            Toast.makeText(this, "Debes escribir un nombre de local", Toast.LENGTH_LONG).show();
        } else {
            local.setNombre(((EditText) findViewById(R.id.editTextLocalName)).getText().toString());
            local.setDireccion(((EditText) findViewById(R.id.editTextLocalAddress)).getText().toString());
            local.setDescripcion(((EditText) findViewById(R.id.editTextDecription)).getText().toString());
            local.setTrabajoHabitual(((Spinner) findViewById(R.id.spinnerhabitualService)).getSelectedItem().toString());
            if (MainActivity.getLocalRepository().addLocal(local)) {
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "El nombre de local a insertar ya existe", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_local, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addLocalMenuSave:
                if (((EditText) findViewById(R.id.editTextLocalName)).getText().toString().equals("")) {
                    Toast.makeText(this, "Debes escribir un nombre de local", Toast.LENGTH_LONG).show();
                } else {
                    local.setNombre(((EditText) findViewById(R.id.editTextLocalName)).getText().toString());
                    local.setDireccion(((EditText) findViewById(R.id.editTextLocalAddress)).getText().toString());
                    local.setDescripcion(((EditText) findViewById(R.id.editTextDecription)).getText().toString());
                    local.setTrabajoHabitual(((Spinner) findViewById(R.id.spinnerhabitualService)).getSelectedItem().toString());
                    if (MainActivity.getLocalRepository().addLocal(local)) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(this, "El nombre de local a insertar ya existe", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
