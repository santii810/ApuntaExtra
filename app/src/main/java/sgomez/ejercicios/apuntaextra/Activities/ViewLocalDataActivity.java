package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

public class ViewLocalDataActivity extends AppCompatActivity {

    private Local local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_local_data);

        String objectId = getIntent().getStringExtra("ObjectId");
        local = MainActivity.getLocalRepository().getLocal(objectId);

        ((EditText) findViewById(R.id.editTextLocalName)).setText(local.getNombre());
        ((EditText) findViewById(R.id.editTextLocalAddress)).setText(local.getDireccion());
        ((EditText) findViewById(R.id.editTextDecription)).setText(local.getDescripcion());
        ((TextView) findViewById(R.id.textViewServicioHabitual)).setText(local.getTrabajoHabitual());
    }
}
