package sgomez.ejercicios.apuntaextra.Activities.AddDatos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Date;

import sgomez.ejercicios.apuntaextra.Activities.MainActivity;
import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;


public class _AddDataActivity extends AppCompatActivity {


    private final static int EXTRA1_REQUEST_CODE = 1;
    private final static int EXTRA2_REQUEST_CODE = 2;
    private final static int EXTRA3_REQUEST_CODE = 3;
    private final static int LOCAL_REQUEST_CODE = 4;
    private Extra extra;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void buttonAddLocalOnClick(View view) {
        startActivityForResult(new Intent(this, AddLocalActivity.class), LOCAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCAL_REQUEST_CODE:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "Local insertado correctamente", Toast.LENGTH_SHORT).show();
                break;
            case EXTRA1_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    extra = new Extra();
                    extra.setLocal(MainActivity.getLocalRepository().getLocal(data.getStringExtra("objectId")));
                    extra.setFecha(new Date(data.getIntExtra("año", 0), data.getIntExtra("mes", 0), data.getIntExtra("dia", 0)));
                    startActivityForResult(new Intent(this, AddExtra2Activity.class), EXTRA2_REQUEST_CODE);
                }
                break;
            case EXTRA2_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    extra.setCobrado(data.getIntExtra("cobrado", 0));
                    extra.setPropina(data.getDoubleExtra("propina", 0));
                    extra.setTiempo(data.getDoubleExtra("tiempo", 0));
                    extra.setPagoAsociado(MainActivity.getMemoryRepositories().getPagoAsociado(data.getStringExtra("pagoAsociado")));
                    extra.setNotas(data.getStringExtra("notas"));

                    Intent intent = new Intent(this, AddExtra3Activity.class);
                    intent.putExtra("servicioHabitual", extra.getLocal().getServicioHabitual());
                    startActivityForResult(intent, EXTRA3_REQUEST_CODE);
                }
                break;
            case EXTRA3_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    try {
                        extra.setFestividad(data.getStringExtra("festividad"));
                    } catch (Exception ignored) {
                    }
                    try {
                        extra.setCocina(MainActivity.getCocinaRepository().getCocina(data.getStringExtra("cocina")));
                    } catch (Exception ignored) {
                    }
                    try {
                        extra.setMomentoDia(data.getStringExtra("momentoDia"));
                    } catch (Exception ignored) {
                    }
                    try {
                        extra.setLatitude(data.getDoubleExtra("latitude", 0));
                        extra.setLatitude(data.getDoubleExtra("longitude", 0));
                    } catch (Exception ignored) {
                    }
                }
                if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                    MainActivity.getExtraRepository().addExtra(extra);
                }
        }
    }

    public void buttonAddCamareroOnClick(View view) {
        startActivity(new Intent(this, AddCamareroActivity.class));
    }

    public void buttonAddCocinaOnClick(View view) {
        startActivity(new Intent(this, AddCocinaActivity.class));
    }

    public void buttonAddExtraOnClick(View view) {
        startActivityForResult(new Intent(this, AddExtra1Activity.class), EXTRA1_REQUEST_CODE);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "_AddData Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sgomez.ejercicios.apuntaextra.Activities.AddDatos/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "_AddData Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sgomez.ejercicios.apuntaextra.Activities.AddDatos/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

