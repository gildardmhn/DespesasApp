package com.codinginflow.despesas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroActivity extends AppCompatActivity {
    private static final String TAG = "CadastroActivity";

    @BindView(R.id.input_nome)
    EditText nomeText;
    @BindView(R.id.input_email)
    EditText emailText;
    @BindView(R.id.input_telefone)
    EditText telefoneText;
    @BindView(R.id.input_senha1)
    EditText senha1Text;
    @BindView(R.id.input_senha2)
    EditText senha2Text;
    @BindView(R.id.btn_cadastro)
    Button cadastroButton;
    @BindView(R.id.link_login)
    TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);

        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastro();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void cadastro() {
        Log.d(TAG, "Cadastro");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        cadastroButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CadastroActivity.this,
                R.style.ThemeOverlay_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Realizando Cadastro...");
        progressDialog.show();

        String name = nomeText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = telefoneText.getText().toString();
        String password = senha1Text.getText().toString();
        String reEnterPassword = senha2Text.getText().toString();

        // TODO: Implement your own cadastro logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        cadastroButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        cadastroButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nomeText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = telefoneText.getText().toString();
        String password = senha1Text.getText().toString();
        String reEnterPassword = senha2Text.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nomeText.setError("at least 3 characters");
            valid = false;
        } else {
            nomeText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            telefoneText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            telefoneText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            senha1Text.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            senha1Text.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            senha2Text.setError("Password Do not match");
            valid = false;
        } else {
            senha2Text.setError(null);
        }

        return valid;
    }
}