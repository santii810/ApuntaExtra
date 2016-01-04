package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Adapters.Adapter_item_subitem;
import sgomez.ejercicios.apuntaextra.Model.Cocina;
import sgomez.ejercicios.apuntaextra.R;

public class ViewCocinasActivity extends AppCompatActivity {
    private ListView listViewCocinas;
    private ArrayList<Cocina> cocinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cocinas);

        cocinas = new ArrayList<>();
        listViewCocinas = ((ListView) findViewById(R.id.listViewViewCocinas));
    }


    @Override
    protected void onResume() {
        super.onResume();
        cocinas = MainActivity.getCocinaRepository().getCocinas();
        listViewCocinas.setAdapter(new Adapter_item_subitem(this, cocinas));


        listViewCocinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cocina cocina = cocinas.get(position);
                Intent intent = new Intent(getBaseContext(), ViewCocinaDataActivity.class);
                intent.putExtra("ObjectId", cocina.getObjectId());
                startActivity(intent);
            }
        });
    }
}
