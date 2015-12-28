package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sgomez.ejercicios.apuntaextra.R;


public class AddLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);
    }

    public void buttonMapAdressSelectOnClick(View view) {
        startActivity(new Intent(this, addMapPositionActivity.class));
    }
}
