package com.example.superhero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void efetuarBusca(View view)
    {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        String query = "Museu de Arte de São Paulo";
        intent.putExtra(SearchManager.QUERY, query);
        startActivity(intent);
    }
}
