package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.HashMap;

import sgomez.ejercicios.apuntaextra.R;

public class AddExtra2Activity extends AppCompatActivity {

    private RadioGroup pagoAsociado;
    private HashMap<String, Double> mapaPagoAsociado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra2);

        pagoAsociado = (RadioGroup) findViewById(R.id.radioGroupPagoAsociado);
        mapaPagoAsociado = MainActivity.getMemoryRepositories().getPagoAsociado();
        for (String key : mapaPagoAsociado.keySet()) {

        }

    }
}
}
