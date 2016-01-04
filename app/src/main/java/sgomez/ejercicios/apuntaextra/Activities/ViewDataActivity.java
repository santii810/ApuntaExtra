package sgomez.ejercicios.apuntaextra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sgomez.ejercicios.apuntaextra.R;

public class ViewDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
    }

    public void buttonViewLocalesOnClick(View view) {
        startActivity(new Intent(this, ViewLocalesActivity.class));
    }

    public void buttonViewCamarerosOnClick(View view) {
        startActivity(new Intent(this, ViewCamarerosActivity.class));
    }

    public void buttonViewCocinasOnClick(View view) {
        startActivity(new Intent(this, ViewCocinasActivity.class));
    }
}
