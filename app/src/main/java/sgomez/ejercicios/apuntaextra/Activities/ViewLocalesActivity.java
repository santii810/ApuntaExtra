package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Adapters.Adapter_item_subitem;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

public class ViewLocalesActivity extends AppCompatActivity {
    private ListView listViewLocales;
    private ArrayList<Local> locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locales);

        locales = new ArrayList<>();
        listViewLocales = ((ListView) findViewById(R.id.listViewViewLocales));
    }

    @Override
    protected void onResume() {
        super.onResume();

        locales = MainActivity.getLocalRepository().getLocales();
        listViewLocales.setAdapter(
                new Adapter_item_subitem(this, locales));


        listViewLocales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local local = locales.get(position);
                Intent intent = new Intent(getBaseContext(), ViewLocalDataActivity.class);
                intent.putExtra("ObjectId", local.getObjectId());
                startActivity(intent);
            }
        });
    }
}
