package sgomez.ejercicios.apuntaextra.Activities.VisualizarDatos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.R;

public class ViewCocinaDataActivity extends AppCompatActivity {

    private Cocina cocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cocina_data);

        String objectId = getIntent().getStringExtra("objectId");
        cocina = MainActivity.getCocinaRepository().getCocina(objectId);

        ((EditText) findViewById(R.id.editTextCocinaName)).setText(cocina.getNombre());
        ((EditText) findViewById(R.id.editTextCocinaAddress)).setText(cocina.getDireccion());
        if (cocina.isActivo()) {
            ((TextView) findViewById(R.id.textViewCocinaStatus)).setText("Activo");
            ((TextView) findViewById(R.id.textViewCocinaStatus)).setTextColor(Color.GREEN);
        } else {
            ((TextView) findViewById(R.id.textViewCocinaStatus)).setText("Inactivo");
            ((TextView) findViewById(R.id.textViewCocinaStatus)).setTextColor(Color.RED);
        }
    }
}