package com.example.superhero;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class HistoricoArray implements Serializable {
    private ArrayList<String> Hist;

    public HistoricoArray(Context context) {
        this.Hist = histArray(context);
    }

    public void addHistArray(String itemhistorico)
    {
        Hist.add(itemhistorico);
    }

    public ArrayList<String> getArrayHist() {
        return Hist;
    }

    private ArrayList<String> histArray(Context context) {
        ArrayList<String> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(context.getFileStreamPath("historico"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            HistoricoArray historicoArray = (HistoricoArray) ois.readObject();
            list = historicoArray.getArrayHist();

            ois.close();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}

