package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;


public class AddDataActivity extends AppCompatActivity {


    private final static int LOCAL_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
    }

    public void buttonAddLocalOnClick(View view) {
        startActivityForResult(new Intent(this, AddLocalActivity.class), LOCAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCAL_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Local local = new Local();
                    local.setDireccion(data.getStringExtra("direccion"));
                    local.setNombre(data.getStringExtra("nombre"));
                    local.setDescripcion(data.getStringExtra("descripcion"));
                    local.setLatitude(data.getDoubleExtra("latitude", 0));
                    local.setLongitude(data.getDoubleExtra("longitude", 0));
                    local.setTrabajoHabitual(data.getStringExtra("trabajoHabitual"));
                    MainActivity.getLocalRepository().addLocal(local);
                    break;
                }
        }
    }
}
