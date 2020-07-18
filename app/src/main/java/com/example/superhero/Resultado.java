package com.example.superhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    private TextView Intelligence, strength, speed, durability, power, combat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intelligence =findViewById(R.id.textView4);
        strength =findViewById(R.id.textView12);
        speed =findViewById(R.id.textView11);
        durability =findViewById(R.id.textView15);
        power =findViewById(R.id.textView13);
        combat =findViewById(R.id.textView14);

        String Intel, streng, spee, dura, powe, comba;

        Bundle bundle = getIntent().getExtras();
        Intel = bundle.getString("intelligence");
        streng = bundle.getString("strength");
        spee = bundle.getString("speed");
        dura = bundle.getString("durability");
        powe = bundle.getString("power");
        comba = bundle.getString("combat");

        Intelligence.setText(Intel);
        strength.setText(streng);
        speed.setText(spee);
        durability.setText(dura);
        power.setText(powe);
        combat.setText(comba);
    }

    public void Voltar(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
