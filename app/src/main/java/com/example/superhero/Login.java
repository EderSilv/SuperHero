package com.example.superhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText Usuario, Senha;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String ARQUIVO = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario=findViewById(R.id.editText);
        Senha=findViewById(R.id.editText2);
        sharedPreferences = getSharedPreferences(ARQUIVO, 0);
        editor = sharedPreferences.edit();
        if (sharedPreferences.contains("user")) {
            abrir();
            }
    }

    private void abrir() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Ola","Bem-Vindo");
        startActivity(intent);
    }

    public void Login(View view) {
        String usuario = "a";
        String senha = "a";

        String user = Usuario.getText().toString();
        String password = Senha.getText().toString();
        if (user.equals(usuario) && password.equals(senha)) {
            editor.putString("user", user);
            editor.putString("password", password);
            editor.commit();
            abrir();
        }
    }


}
