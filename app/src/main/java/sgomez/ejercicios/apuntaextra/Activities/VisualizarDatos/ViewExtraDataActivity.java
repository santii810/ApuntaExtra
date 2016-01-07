package sgomez.ejercicios.apuntaextra.Activities.VisualizarDatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;

public class ViewExtraDataActivity extends AppCompatActivity {

    private Extra extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extra_data);

        extra = MainActivity.getExtraRepository().getExtra(getIntent().getStringExtra("objectId"));
    }
}
