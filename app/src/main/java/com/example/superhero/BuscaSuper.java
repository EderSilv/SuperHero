package com.example.superhero;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class BuscaSuper {
    private static final String LOG_TAG = BuscaSuper.class.getSimpleName();
    // private static String poder_URL = "https://superheroapi.com/api/";
  //  private static final String QUERY_PARAM = "";
  //  private static final String token = "3125838550828839";

  //  https://www.superheroapi.com/api.php/3125838550828839/search/batman

    static String buscapoder(String queryString)
    {
        String poder_URL = "https://superheroapi.com/api/3125838550828839/search/"+queryString;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
    try
    {
        /*Uri builtURI = Uri.parse(poder_URL).buildUpon()
                .appendQueryParameter(token)
                .appendQueryParameter(QUERY_PARAM, "search")
                .appendQueryParameter(QUERY_PARAM, queryString)
                .build();*/
        //Log.d("asddas", builtURI.toString());
        URL requestURL = new URL(poder_URL);
        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null)
        {
            // Adiciona a linha a string.
            builder.append(linha);
            builder.append("\n");
        }
        if (builder.length() == 0) {
            // se o stream estiver vazio não faz nada
            return null;
        }
        bookJSONString = builder.toString();
    } catch (ProtocolException e) {
        e.printStackTrace();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    finally {
        // fecha a conexão e o buffer.
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        Log.d(LOG_TAG, bookJSONString);
        return bookJSONString;
    }
}
