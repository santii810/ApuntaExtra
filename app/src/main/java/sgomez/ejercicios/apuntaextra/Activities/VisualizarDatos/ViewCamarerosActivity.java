package sgomez.ejercicios.apuntaextra.Activities.VisualizarDatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Adapters.Adapter_item_subitem;
import sgomez.ejercicios.apuntaextra.Model.Camarero;
import sgomez.ejercicios.apuntaextra.R;

public class ViewCamarerosActivity extends AppCompatActivity {

    private ListView listViewCamareros;
    private ArrayList<Camarero> camareros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_camareros);

        camareros = new ArrayList<>();
        listViewCamareros = ((ListView) findViewById(R.id.listViewViewCamareros));
    }


    @Override
    protected void onResume() {
        super.onResume();
        camareros = MainActivity.getCamareroRepository().getCamareros();


        listViewCamareros.setAdapter(new Adapter_item_subitem(this, camareros));
        listViewCamareros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Camarero camarero = camareros.get(position);
                Intent intent = new Intent(getBaseContext(), ViewCamareroDataActivity.class);
                intent.putExtra("objectId", camarero.getObjectId());
                startActivity(intent);
            }
        });
    }
}
