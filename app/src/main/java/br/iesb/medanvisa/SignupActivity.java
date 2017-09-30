package br.iesb.medanvisa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private Button btnSignup;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inicializaComponentes();
        eventoClicks();

    }

    private void eventoClicks() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                criarUsuario(email, password);

            }
        });
    }

    private void criarUsuario(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso!");
                            startActivity(new Intent(SignupActivity.this, SearchMedicineActivity.class));
                            finish();
                        } else {
                            alert("Erro no cadastro!");
                        }
                    }
                });
    }


    private void alert(String msg) {
        Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    private void inicializaComponentes() {
        editEmail = (EditText) findViewById(R.id.signup_email);
        editName = (EditText) findViewById(R.id.signup_name);
        editPassword = (EditText) findViewById(R.id.signup_password);
        btnSignup = (Button) findViewById(R.id.signup_btn);
    }
}
