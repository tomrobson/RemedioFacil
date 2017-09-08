package br.iesb.remediofacil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarActivity extends AppCompatActivity {

    private static final String TAG = "Teste:";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email, password, name;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.cadastrar_email);
        password = (EditText) findViewById(R.id.cadastrar_senha);
        name = (EditText) findViewById(R.id.cadastrar_nome);
        buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(CadastrarActivity.this, "Aguarde", "Processando...", true);

                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(CadastrarActivity.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CadastrarActivity.this, LoginActivity.class));
                                } else {
                                    Log.e("Error : ", task.getException().toString());
                                    Toast.makeText(CadastrarActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

    }


}
