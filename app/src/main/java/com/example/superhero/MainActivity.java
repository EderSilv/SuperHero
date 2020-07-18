package com.example.superhero;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText EdSuper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdSuper=findViewById(R.id.EdSuperH);
        if (getSupportLoaderManager().getLoader(0) != null)
        {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void efetuarBusca(View view)
    {
        String queryString = EdSuper.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null)
        {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null)
        {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0)
        {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        else
            {
            if (queryString.length() == 0)
            {

            } else
                {
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args)
    {
        String queryString = "";
        if (args != null)
        {
            queryString = args.getString("queryString");
        }
        return new CarregaSuperpoder(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try
        {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Obtem o JSONArray
            JSONArray arrayResults = jsonObject.getJSONArray("results");
            String intelligence = null;
            String strength = null;
            String speed = null;
            String durability = null;
            String power = null;
            String combat = null;
            int i = 0;
            while (i < arrayResults.length() && intelligence == null && strength == null && speed == null
                                             && durability == null && power == null && combat == null )
            {
                JSONObject JSONQualquer = arrayResults.getJSONObject(i);
                JSONObject JSONpowerstats = JSONQualquer.getJSONObject("powerstats");
                intelligence = JSONpowerstats.getString("intelligence");
                strength = JSONpowerstats.getString("strength");
                speed = JSONpowerstats.getString("speed");
                durability = JSONpowerstats.getString("durability");
                power = JSONpowerstats.getString("power");
                combat = JSONpowerstats.getString("combat");
            }

            //mostra o resultado qdo possivel.
            if (intelligence != null && strength != null && speed != null && durability != null && power != null && combat != null) {

                Intent intent = new Intent(this, Resultado.class);
                intent.putExtra("intelligence", intelligence);
                intent.putExtra("strength", strength);
                intent.putExtra("speed", speed);
                intent.putExtra("durability", durability);
                intent.putExtra("power", power);
                intent.putExtra("combat", combat);
                startActivity(intent);

            }
            else {
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void AbrirSite(View view) {
        Uri uri = Uri.parse("https://superheroapi.com/ids.html");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

}
