package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import sgomez.ejercicios.apuntaextra.Model.Usuario;
import sgomez.ejercicios.apuntaextra.R;


public class PerfilActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView correo;
    private ImageView foto;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        usuario = MainActivity.getUsuario();
        
        ((TextView) findViewById(R.id.textViewPerfilNombre)).setText(usuario.getNombre());
        ((TextView) findViewById(R.id.textViewPerfilCorreo)).setText(usuario.getCorreo());


    }
}
