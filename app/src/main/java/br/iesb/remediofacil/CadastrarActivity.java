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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (!validateNome(name.getText().toString())){
                    Toast.makeText(CadastrarActivity.this, "Nome mínimo de 3 e máximo de 40 letras!", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                } else if (!validateEmail(email.getText().toString())) {
                    Toast.makeText(CadastrarActivity.this, "Email inválido!", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                } else if(!validateSenha(password.getText().toString())) {
                    Toast.makeText(CadastrarActivity.this, "Senha mínimo de 6 e máximo de 12!", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                } else {
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
            }
        });

    }

    private boolean validateNome(String nome) {
        if (nome!=null && nome.length()>2 && nome.length()<41){
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmail(String email) {
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (email.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean validateSenha(String senha) {
        if (senha!=null && senha.length()>5 && senha.length()<13){
            return true;
        } else {
            return false;
        }
    }

}
