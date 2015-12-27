package sgomez.ejercicios.apuntaextra.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import sgomez.ejercicios.apuntaextra.Model.Usuario;
import sgomez.ejercicios.apuntaextra.R;


public class PerfilActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private TextView nombre;
    private TextView correo;
    private ImageView foto;
    private Usuario usuario;

    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        usuario = MainActivity.getUsuario();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        ((TextView) findViewById(R.id.textViewPerfilNombre)).setText(usuario.getNombre());
        ((TextView) findViewById(R.id.textViewPerfilCorreo)).setText(usuario.getCorreo());
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                        finish();
                    }
                });
    }

    public void buttonSignOutOnClick(View view) {
        signOut();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Conexion fallida", Toast.LENGTH_SHORT).show();
    }

    public void buttonRevokeAccessOnClick(View view) {
        revokeAccess();
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        finish();
                    }
                });
    }
}
