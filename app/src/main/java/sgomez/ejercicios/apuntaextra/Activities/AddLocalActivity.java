package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import sgomez.ejercicios.apuntaextra.Model.Local;
import sgomez.ejercicios.apuntaextra.R;


public class AddLocalActivity extends AppCompatActivity {

    private static final int GET_MAP_POSITION_REQUEST_CODE = 1;
    private Local local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);
        local = new Local();
    }

    public void buttonMapAdressSelectOnClick(View view) {
        startActivityForResult(new Intent(this, addMapPositionActivity.class), GET_MAP_POSITION_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_MAP_POSITION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                local.setLatitude(data.getDoubleExtra("latitude", 0));
                local.setLongitude(data.getDoubleExtra("longitude", 0));
            }
        }
    }
}
