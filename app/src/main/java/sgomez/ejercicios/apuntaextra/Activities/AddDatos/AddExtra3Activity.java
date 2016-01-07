package sgomez.ejercicios.apuntaextra.Activities.AddDatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Adapters.Adapter_simple_spinner;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AddExtra3Activity extends AppCompatActivity {

    private String servicioHabitual;
    private static final int GET_MAP_POSITION_REQUEST_CODE = 1;
    private Intent backData;


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
        backData = new Intent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //FESTIVIDADES
        ArrayList<String> festividades = MainActivity.getMemoryRepositories().getFestividad();
        festividades.add("Seleccionar festividad");
        Adapter_simple_spinner adapterFestividad = new Adapter_simple_spinner(this, R.layout.view_simple_spinner, festividades);
        ((Spinner) findViewById(R.id.spinnerFestividad)).setAdapter(adapterFestividad);

        //COCINAS
        ArrayList<Cocina> cocinas = MainActivity.getCocinaRepository().getCocinas();
        cocinas.add(0, new Cocina());
        cocinas.get(0).setNombre("Seleccionar cocina");
        Adapter_simple_spinner adapterCocina = new Adapter_simple_spinner(this, R.layout.view_simple_spinner, cocinas);
        ((Spinner) findViewById(R.id.spinnerCocina)).setAdapter(adapterCocina);
    }

    public void buttonMapAdressSelectOnClick(View view) {
        startActivityForResult(new Intent(this, AddMapPositionActivity.class), GET_MAP_POSITION_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_MAP_POSITION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                backData.putExtra("latitude", (data.getDoubleExtra("latitude", 0)));
                backData.putExtra("longitude", (data.getDoubleExtra("longitude", 0)));
            }
        }
    }



    private void updateIU(int festividad, int cocina, int momentoDia, int direccion) {
        findViewById(R.id.spinnerFestividad).setVisibility(festividad);
        findViewById(R.id.spinnerCocina).setVisibility(cocina);
        findViewById(R.id.radioGroupMomentoDia).setVisibility(momentoDia);
        findViewById(R.id.buttonMapAddressSelect).setVisibility(direccion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_extra1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addExtra3MenuNext:
                next();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void next() {

        String festividad = ((Spinner) findViewById(R.id.spinnerFestividad)).getSelectedItem().toString();
        if (!festividad.equals("Seleccionar festividad"))
            backData.putExtra("festividad", festividad);
        Cocina cocina = (Cocina) ((Spinner) findViewById(R.id.spinnerCocina)).getSelectedItem();
        if (!cocina.getNombre().equals("Seleccionar cocina"))
            backData.putExtra("cocina", cocina.getObjectId());
        if (findViewById(R.id.radioButtonNoche).isSelected()) {
            backData.putExtra("momentoDia", "Noche");
        } else {
            backData.putExtra("momentoDia", "Dia");
        }
        setResult(RESULT_OK);
        finish();
    }
}
