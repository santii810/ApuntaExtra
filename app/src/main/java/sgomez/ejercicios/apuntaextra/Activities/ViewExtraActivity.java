package sgomez.ejercicios.apuntaextra.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;

public class ViewExtraActivity extends AppCompatActivity {

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


    }
}
