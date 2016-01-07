package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sgomez.ejercicios.apuntaextra.Adapters.Adapter_view_extra;
import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;

public class ViewExtrasActivity extends AppCompatActivity {

    private ListView listViewExtras;
    private ArrayList<Extra> extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extra);

        extras = new ArrayList<>();
        listViewExtras = (ListView) findViewById(R.id.listViewViewExtras);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extras = MainActivity.getExtraRepository().getExtras();
        listViewExtras.setAdapter(new Adapter_view_extra(this, extras));
        listViewExtras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Extra extra = extras.get(position);
                Intent intent = new Intent(getBaseContext(), ViewExtraDataActivity.class);
                intent.putExtra("objectId", extra.getObjectId());
                startActivity(intent);
            }
        });

    }
}
