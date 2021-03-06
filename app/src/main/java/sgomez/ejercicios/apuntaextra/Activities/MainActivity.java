package sgomez.ejercicios.apuntaextra.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.parse.Parse;

import sgomez.ejercicios.apuntaextra.Activities.AddDatos._AddDataActivity;
import sgomez.ejercicios.apuntaextra.Activities.VisualizarDatos._ViewDataActivity;
import sgomez.ejercicios.apuntaextra.Repositories.MemoryRepositories;
import sgomez.ejercicios.apuntaextra.Repositories.ParseCamareroRepository;
import sgomez.ejercicios.apuntaextra.Repositories.ParseCocinaRepositorio;
import sgomez.ejercicios.apuntaextra.Repositories.ParseExtraRepository;
import sgomez.ejercicios.apuntaextra.Repositories.ParseLocalRepository;
import sgomez.ejercicios.apuntaextra.Repositories.ParseUsuarioRepository;
import sgomez.ejercicios.apuntaextra.Model.Usuario;
import sgomez.ejercicios.apuntaextra.R;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private static GoogleApiClient mGoogleApiClient;

    private static Usuario usuario;

    //REPOSITORIOS
    private static ParseUsuarioRepository UsuarioRepository = new ParseUsuarioRepository();
    private static ParseLocalRepository LocalRepository = new ParseLocalRepository();
    private static ParseCamareroRepository CamareroRepository = new ParseCamareroRepository();
    private static ParseCocinaRepositorio CocinaRepository = new ParseCocinaRepositorio();
    private static ParseExtraRepository ExtraRepository = new ParseExtraRepository();
    private static MemoryRepositories memoryRepositories = new MemoryRepositories();


    private ProgressDialog mProgressDialog;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static ParseUsuarioRepository getUsuarioRepository() {
        return UsuarioRepository;
    }

    public static GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }
    // [END onActivityResult]

    public static ParseLocalRepository getLocalRepository() {
        return LocalRepository;
    }
    // [END handleSignInResult]


    public static MemoryRepositories getMemoryRepositories() {
        return memoryRepositories;
    }

    public static ParseCocinaRepositorio getCocinaRepository() {
        return CocinaRepository;
    }
    // [END signIn]

    public static ParseCamareroRepository getCamareroRepository() {
        return CamareroRepository;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this);
        } catch (Exception ignored) {
        }


        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        // [START customize_button]
        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        // [END customize_button]
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount account = result.getSignInAccount();
            usuario = new Usuario();
            usuario.setNombre(account.getDisplayName());
            usuario.setCorreo(account.getEmail());
            usuario.setGoogleId(account.getId());
            usuario.setFoto(account.getPhotoUrl());
            usuario.setObjectId(UsuarioRepository.addUsuario(usuario));

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Conexion a Google+ fallida", Toast.LENGTH_SHORT).show();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    /**
     * Comprueba, mediante el parametro asignado, si estas logeado.
     * En caso afirmativo muestra los botones ocultos y oculta el boton de sign in.
     * En caso negrativo hace lo contrario
     *
     * @param signedIn boleano que representa si esta o no autenticado
     */
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.buttonPerfil).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonAddData).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonViewData).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonResumen).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonPerfil).setVisibility(View.GONE);
            findViewById(R.id.buttonAddData).setVisibility(View.GONE);
            findViewById(R.id.buttonViewData).setVisibility(View.GONE);
            findViewById(R.id.buttonResumen).setVisibility(View.GONE);
        }
    }

    public void buttonPerfilOnClick(View view) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }

    public void buttonAddDataOnClick(View view) {
        startActivity(new Intent(this, _AddDataActivity.class));
    }

    public void buttonViewDataOnClick(View view) {
        startActivity(new Intent(this, _ViewDataActivity.class));
    }

    public static ParseExtraRepository getExtraRepository() {
        return ExtraRepository;
    }

    public void buttonResumenOnClick(View view) {
        startActivity(new Intent(this, ResumenActivity.class));
    }
}
