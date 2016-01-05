package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.sql.Date;

import sgomez.ejercicios.apuntaextra.Model.Extra;
import sgomez.ejercicios.apuntaextra.R;


public class AddDataActivity extends AppCompatActivity {


    private final static int EXTRA1_REQUEST_CODE = 1;
    private final static int EXTRA2_REQUEST_CODE = 2;
    private final static int LOCAL_REQUEST_CODE = 4;

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
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "Local insertado correctamente", Toast.LENGTH_SHORT).show();
                break;
            case EXTRA1_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Extra extra = new Extra();
                    extra.setLocal(MainActivity.getLocalRepository().getLocal(data.getStringExtra("objectId")));
                    extra.setFecha(new Date(data.getIntExtra("año", 0), data.getIntExtra("mes", 0), data.getIntExtra("dia", 0)));
                    startActivityForResult(new Intent(this, AddExtra2Activity.class), EXTRA2_REQUEST_CODE);
                }
                break;
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
}

