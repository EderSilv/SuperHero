package com.example.superhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class Resultado extends AppCompatActivity {
    private TextView Intelligence, strength, speed, durability, power, combat, persona;
    private ImageView image;
    private Button botao;
    private String queryString;
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
        image =findViewById(R.id.imageSeila);
        botao = findViewById(R.id.button3);
        persona = findViewById(R.id.textView3);

        final String Intel, streng, spee, dura, powe, comba, imag, perso;

        Bundle bundle = getIntent().getExtras();
        final Personagem personagem = (Personagem) bundle.getSerializable("personagem");
        /*Intel = bundle.getString("intelligence");
        streng = bundle.getString("strength");
        spee = bundle.getString("speed");
        dura = bundle.getString("durability");
        powe = bundle.getString("power");
        comba = bundle.getString("combat");
        imag = bundle.getString("image");
        perso = bundle.getString("Levar");*/



        Picasso.get().load(personagem.getImage()).into(image);
        Intelligence.setText(personagem.getIntelligence());
        strength.setText(personagem.getStrength());
        speed.setText(personagem.getSpeed());
        durability.setText(personagem.getDurability());
        power.setText(personagem.getPower());
        combat.setText(personagem.getCombat());
        persona.setText(personagem.getNome());
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDir(personagem.getImage());
            }
        });
    }

    public void createDir(String img) {
        if (writeExternalGranted()) {
            final String fileName = "PersonageM";
            File direct =
                    new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .getAbsolutePath() + "/" + System.currentTimeMillis() );

            if (!direct.exists())
                direct.mkdir();

            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(img);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Personagens" + System.currentTimeMillis())
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + "/SuperHero/" + System.currentTimeMillis());

            assert dm != null;
            dm.enqueue(request);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                botao.performClick();
            }
        }
    }

    public boolean writeExternalGranted() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
            return true;
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }

/*    public void Pesquisar(View view) {
        Uri uri = Uri.parse("https://www.google.com/search?q" + personagem);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }*/

    public void Voltar(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
