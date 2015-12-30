package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import sgomez.ejercicios.apuntaextra.Adapters.Adapter_item_subitem;
import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;

public class ViewLocalesActivity extends AppCompatActivity {

    private ArrayList<Local> locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locales);

        locales = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        locales = MainActivity.getLocalRepository().getLocales();
        ((ListView) findViewById(R.id.listViewViewLocales)).setAdapter(
                new Adapter_item_subitem(this, R.layout.view_item_subitem, locales));
        super.onResume();
    }
}
