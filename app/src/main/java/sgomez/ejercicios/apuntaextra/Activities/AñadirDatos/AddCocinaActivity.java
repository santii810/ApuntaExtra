package sgomez.ejercicios.apuntaextra.Activities.AñadirDatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.R;

public class AddCocinaActivity extends AppCompatActivity {

    private Cocina cocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cocina);
        cocina = new Cocina();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_cocina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCocinaMenuSave:
                if (((EditText) findViewById(R.id.editTextCocinaName)).getText().toString().equals("")) {
                    Toast.makeText(this, "Debes escribir un nombre de cocina", Toast.LENGTH_LONG).show();
                } else {
                    cocina.setNombre(((EditText) findViewById(R.id.editTextCocinaName)).getText().toString());
                    cocina.setDireccion(((EditText) findViewById(R.id.editTextCocinaAddress)).getText().toString());
                    cocina.setActivo(((ToggleButton) findViewById(R.id.toggleButtonCocina)).isChecked());

                    if (MainActivity.getCocinaRepository().addCocina(cocina)) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(this, "El nombre de cocina a insertar ya existe", Toast.LENGTH_LONG).show();
                    }

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
