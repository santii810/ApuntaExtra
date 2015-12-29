package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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


                break;
        }
    }
}
