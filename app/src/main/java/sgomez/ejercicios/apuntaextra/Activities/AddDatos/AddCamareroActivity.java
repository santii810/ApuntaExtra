package sgomez.ejercicios.apuntaextra.Activities.AddDatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Camarero;
import sgomez.ejercicios.apuntaextra.R;

public class AddCamareroActivity extends AppCompatActivity {
    private Camarero camarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camarero);
        camarero = new Camarero();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_camarero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCamareroMenuSave:
                if (((EditText) findViewById(R.id.editTextCamareroName)).getText().toString().equals("")) {
                    Toast.makeText(this, "Debes escribir un nombre de camarero", Toast.LENGTH_LONG).show();
                } else {
                    camarero.setNombre(((EditText) findViewById(R.id.editTextCamareroName)).getText().toString());
                    camarero.setDireccion(((EditText) findViewById(R.id.editTextCamareroAddress)).getText().toString());
                    camarero.setActivo(((ToggleButton) findViewById(R.id.toggleButtonCamarero)).isChecked());

                    if (MainActivity.getCamareroRepository().addCamarero(camarero)) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(this, "El nombre de camarero a insertar ya existe", Toast.LENGTH_LONG).show();
                    }

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
