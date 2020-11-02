package com.example.superhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText Usuario, Senha;
    private SharedPreferences sharedPreferences; //a gente cria aqui primeiro
    private SharedPreferences.Editor editor; // depois aqui
    public static final String ARQUIVO = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario=findViewById(R.id.editText);
        Senha=findViewById(R.id.editText2);
        sharedPreferences = getSharedPreferences(ARQUIVO, 0);
        //shared preference: arquivo dentro do app,
        // esse é o nome do arquivo e o zero significa que ele é privado, só eu posso usar
        editor = sharedPreferences.edit(); // ai a gente instancia ele

        if (sharedPreferences.contains("user")) {
            // aqui a gente verifica caso ja exista sharedPreferences armazenado
            abrir();
            }
    }

    private void abrir(){
        //metodo que abre a tela principal
        Intent intent = new Intent(Login.this,MainActivity.class);
        intent.putExtra("ola","Bem-Vindo");
        //passagem de valor entre activity
        startActivity(intent);
    }

    public void Login(View view) {
        //botao pra abrir
        String usuario = "a";
        String senha = "a";

        String user = Usuario.getText().toString();
        String password = Senha.getText().toString();
        if (user.equals(usuario) && password.equals(senha)) {
            //escreve dentro do arquivo sharedp(putString)
            editor.putString("user", user);
            editor.putString("password", password);
            editor.commit();
            abrir();
        }
    }


}
