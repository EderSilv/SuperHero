package com.example.superhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoricoActivity extends AppCompatActivity {
    private RecyclerView Lista;
    Button btLocation;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        Lista = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Lista.setLayoutManager(layoutManager);
        Lista.setHasFixedSize(true);
        Lista.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        HistoricoAdapter historicoAdapter = new HistoricoAdapter(MainActivity.historicoArray.getArrayHist());
        Lista.setAdapter(historicoAdapter);

        //Utilizando variaveis
        btLocation = findViewById(R.id.bt_location);
        textView1= findViewById(R.id.text_view1);
        textView2= findViewById(R.id.text_view2);
        textView3= findViewById(R.id.text_view3);
        textView4= findViewById(R.id.text_view4);
        textView5= findViewById(R.id.text_view5);

        //Iniciando fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (ActivityCompat.checkSelfPermission(HistoricoActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                    getLocation();
                }
                else {
                    ActivityCompat.requestPermissions(HistoricoActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });

    }

    private void getLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                android.location.Location location = task.getResult();
                if (location != null)
                {
                    try {
                        //Iniciar geoCoder
                        Geocoder geocoder = new Geocoder(HistoricoActivity.this,
                                Locale.getDefault());

                        //Iniciar list

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );

                        //Set Latitude na TextView
                        textView1.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Latitude</b><br></font>"
                                        + addresses.get(0).getLatitude()
                        ));

                        //Set Longitude na TextView
                        textView2.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Longitude</b><br></font>"
                                        + addresses.get(0).getLongitude()
                        ));

                        //Set Country na TextView
                        textView3.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Country</b><br></font>"
                                        + addresses.get(0).getCountryName()
                        ));

                        //Set Local na TextView
                        textView4.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Locality</b><br></font>"
                                        + addresses.get(0).getLocality()
                        ));

                        //Set Address na TextView
                        textView5.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Address</b><br></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public void Voltar3(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

}
