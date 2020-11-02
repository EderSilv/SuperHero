package com.example.superhero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Personagem.db";
    private static final String ID_CO = "id";
    private static final String NOME_CO = "nome";
    private static final String INTELLIGENCE_CO = "intelligence";
    private static final String STRENGTH_CO ="strength";
    private static final String SPEED_CO ="speed";
    private static final String DURABILITY_CO ="durability";
    private static final String POWER_CO ="power";
    private static final String COMBAT_CO = "combat";
    private static final String IMAGE_CO ="image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table personagens" +
                        "(id integer primary key, nome text, intelligence text, strength text, speed text, durability text, power text, combat text, image text)"
        );


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personagens");
        onCreate(db);
    }

    public void insertPersonagem (Personagem personagens) {
        try{

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_CO, personagens.getId());
            contentValues.put(NOME_CO, personagens.getNome());
            contentValues.put(INTELLIGENCE_CO, personagens.getIntelligence());
            contentValues.put(STRENGTH_CO, personagens.getStrength());
            contentValues.put(SPEED_CO, personagens.getSpeed());
            contentValues.put(DURABILITY_CO, personagens.getDurability());
            contentValues.put(POWER_CO, personagens.getPower());
            contentValues.put(COMBAT_CO, personagens.getCombat());
            contentValues.put(IMAGE_CO, personagens.getImage());
            db.insert("personagens", null, contentValues);
//            Log.i("AQUI", "INSERIU");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Personagem> getPersonagemlist(String name) {
        ArrayList<Personagem> lista = new ArrayList<>() ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from personagens where nome like '" + name + "%'", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Personagem personagens = new Personagem();
            personagens.setId(Integer.parseInt(res.getString(res.getColumnIndex(ID_CO))));
            personagens.setNome(res.getString(res.getColumnIndex(NOME_CO)));
            personagens.setIntelligence(res.getString(res.getColumnIndex(INTELLIGENCE_CO)));
            personagens.setStrength(res.getString(res.getColumnIndex(STRENGTH_CO)));
            personagens.setSpeed(res.getString(res.getColumnIndex(SPEED_CO)));
            personagens.setDurability(res.getString(res.getColumnIndex(DURABILITY_CO)));
            personagens.setPower(res.getString(res.getColumnIndex(POWER_CO)));
            personagens.setCombat(res.getString(res.getColumnIndex(COMBAT_CO)));
            personagens.setImage(res.getString(res.getColumnIndex(IMAGE_CO)));

            lista.add(personagens);
            res.moveToNext();
        }

        return lista;
    }

    /*public ArrayList<Integer> getIds() {
        ArrayList<Integer> array_list = new ArrayList<>();
        Cursor res = null;
        try {
         SQLiteDatabase db = this.getReadableDatabase();
         res = db.rawQuery("select id from personagens", null);
         res.moveToFirst();

         while (!res.isAfterLast()) {
             array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(ID_CO))));
             res.moveToNext();
         }
     }
     catch (Exception e){

     }
     finally {
       //  res.close();
     }


        return array_list;
    }*/

}
