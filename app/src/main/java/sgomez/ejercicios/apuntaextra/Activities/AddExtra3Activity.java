package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import sgomez.ejercicios.apuntaextra.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AddExtra3Activity extends AppCompatActivity {

    private String servicioHabitual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra3);

        servicioHabitual = getIntent().getStringExtra("servicioHabitual");

        switch (servicioHabitual) {
            case "Banquetes":
                updateIU(GONE, GONE, VISIBLE, GONE);
                break;
            case "Carta":
                updateIU(VISIBLE, GONE, VISIBLE, GONE);
                break;
            case "Catering":
                updateIU(GONE, GONE, VISIBLE, VISIBLE);
                break;
            case "Casa":
                updateIU(GONE, VISIBLE, VISIBLE, VISIBLE);
                break;
            default:
                setResult(RESULT_OK);
                finish();
        }
    }


    private void updateIU(int festividad, int cocina, int momentoDia, int direccion) {
        findViewById(R.id.spinnerFestividad).setVisibility(GONE);
        findViewById(R.id.spinnerCocina).setVisibility(cocina);
        findViewById(R.id.radioGroupMomentoDia).setVisibility(momentoDia);
        findViewById(R.id.buttonMapAddressSelect).setVisibility(direccion);
    }
}
