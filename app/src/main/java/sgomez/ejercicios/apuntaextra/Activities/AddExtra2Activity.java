package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Model.PagoAsociado;
import sgomez.ejercicios.apuntaextra.R;

public class AddExtra2Activity extends AppCompatActivity {

    private RadioGroup pagoAsociado;
    private ArrayList<PagoAsociado> pagos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra2);

        pagoAsociado = (RadioGroup) findViewById(R.id.radioGroupPagoAsociado);
        pagos = MainActivity.getMemoryRepositories().getPagosAsociados();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        for (PagoAsociado item : pagos) {
            RadioButton rb = new RadioButton(this);
            rb.setText(item.getNombre());
            rb.setLayoutParams(params);
            pagoAsociado.addView(rb);
        }

    }
}
