package com.example.superhero;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

//criei essa classe só com arraylist pra poder por no armazenamento interno
public class HistoricoArray implements Serializable {
    private ArrayList<String> Hist;

    //construtror, qd for ativado vai chamar o histArray
    public HistoricoArray(Context context) {
        this.Hist = histArray(context);
    }

    public void addHistArray(String itemhistorico)
    {
        //adiciona o que eu acabei de digitar
        Hist.add(itemhistorico);
    }

    //retorna o array
    public ArrayList<String> getArrayHist() {
        return Hist;
    }

    //qd eu inicio o programa ele busca tudo o que tem

    private ArrayList<String> histArray(Context context) {
        //crio um arrayList (list) temporario
        ArrayList<String> list = new ArrayList<>();
        try {
            //pego o arquivo pra leitura, uso o context pra pegar o arquivo
            FileInputStream fis = new FileInputStream(context.getFileStreamPath("historico"));

            //vai ler o obejto e adicionar dentro do historicoArray
            ObjectInputStream ois = new ObjectInputStream(fis);
            HistoricoArray historicoArray = (HistoricoArray) ois.readObject();

            //vai ser tudo adicionado aqui dentro (list)
            list = historicoArray.getArrayHist();

            ois.close();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        //aqui eu retorno essa lista temporaria
        return list;
    }

}

