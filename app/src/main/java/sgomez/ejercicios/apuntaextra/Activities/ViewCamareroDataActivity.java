package sgomez.ejercicios.apuntaextra.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import sgomez.ejercicios.apuntaextra.Model.Camarero;
import sgomez.ejercicios.apuntaextra.R;

public class ViewCamareroDataActivity extends AppCompatActivity {

    private Camarero camarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_camarero_data);

        String objectId = getIntent().getStringExtra("objectId");
        camarero = MainActivity.getCamareroRepository().getCamarero(objectId);

        ((EditText) findViewById(R.id.editTextCamareroName)).setText(camarero.getNombre());
        ((EditText) findViewById(R.id.editTextCamareroAddress)).setText(camarero.getDireccion());
        if (camarero.isActivo()) {
            ((TextView) findViewById(R.id.textViewCamareroStatus)).setText("Activo");
            ((TextView) findViewById(R.id.textViewCamareroStatus)).setTextColor(Color.GREEN);
        } else {
            ((TextView) findViewById(R.id.textViewCamareroStatus)).setText("Inactivo");
            ((TextView) findViewById(R.id.textViewCamareroStatus)).setTextColor(Color.RED);
        }
    }
}
