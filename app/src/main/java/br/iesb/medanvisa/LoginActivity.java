package br.iesb.medanvisa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button btnLogin;
    private Button btnLoginGoogle;
    private LoginButton btnLoginFacebook;
    private EditText editEmail;
    private EditText editPassword;

    private FirebaseAuth auth;
    private GoogleApiClient mGoogleApiClient;

    // facebook
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        inicializaFirebaseCallback();
        conectaGoogleApi();
        eventoClicks();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount signInAccount = result.getSignInAccount();
                firebaseLoginGoogle(signInAccount);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }

    private void conectaGoogleApi() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void inicializaFirebaseCallback() {
        auth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
    }

    private void eventoClicks() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                login(email, password);
            }
        });

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });

        btnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseLoginFacebook(loginResult.getAccessToken());
                Intent intent = new Intent(LoginActivity.this, SearchMedicineActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                alert("Operação cancelada");
            }

            @Override
            public void onError(FacebookException error) {
                alert("Erro ao logar com com facebook");
            }
        });

    }

    private void firebaseLoginFacebook(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, SearchMedicineActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, SearchMedicineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loginGoogle() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, 1);
    }

    private void firebaseLoginGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, SearchMedicineActivity.class);
                    startActivity(intent);
                } else {
                    alert("Falha na autenticação");
                }
            }
        });
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, SearchMedicineActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Email ou senha errados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void inicializaComponentes() {
        editEmail = (EditText) findViewById(R.id.login_email);
        editPassword = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.login_btn_email_pass);
        btnLoginGoogle = (Button) findViewById(R.id.login_btn_google);

        btnLoginFacebook = (LoginButton) findViewById(R.id.login_btn_facebook);
        btnLoginFacebook.setReadPermissions("email", "public_profile");
    }

    private void alert(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
    }
}
