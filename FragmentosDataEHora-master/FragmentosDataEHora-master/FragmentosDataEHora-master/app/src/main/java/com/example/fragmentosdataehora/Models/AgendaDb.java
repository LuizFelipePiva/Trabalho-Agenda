package com.example.fragmentosdataehora.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaDb {

    private Context mContext;
    private static Context mStaticContext;
    private SQLiteDatabase mDatabase;

    public AgendaDb(Context contexto){
        mContext = contexto.getApplicationContext();
        mStaticContext = mContext;
        mDatabase = new AgendaDbHelper(mContext).getWritableDatabase();
        //removeBanco();
    }

    public ContentValues getValoresConteudo(Compromissos c){

        int proxId = getProxId();
        c.setId(proxId);

        ContentValues valores = new ContentValues();
        valores.put(AgendaDbSchema.AgendaTbl.Cols.ID, proxId);
        valores.put(AgendaDbSchema.AgendaTbl.Cols.HORA, c.getHora());
        valores.put(AgendaDbSchema.AgendaTbl.Cols.MINUTO, c.getMinuto());
        valores.put(AgendaDbSchema.AgendaTbl.Cols.DIA, c.getDia());
        valores.put(AgendaDbSchema.AgendaTbl.Cols.MES, c.getMes());
        valores.put(AgendaDbSchema.AgendaTbl.Cols.ANO, c.getAno());
        valores.put(AgendaDbSchema.AgendaTbl.Cols.DESCRICAO, c.getDescricao());
        return valores;
    }

    public void addCompromisso(Compromissos c){

        ContentValues valores = getValoresConteudo(c);
        Log.i("db", valores.toString());
        mDatabase.insert(AgendaDbSchema.AgendaTbl.NOME, null, valores);

    }

    public int getProxId(){
        Cursor cursor = mDatabase.rawQuery("SELECT MAX(id) FROM Agenda", null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(0) + 1;
        } else {
            return 1;
        }
    }

    public Cursor getCompromissos(){

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Agenda", null);
        return cursor;
    }

    void removeBanco(){
        int delete;
        delete = mDatabase.delete(
                AgendaDbSchema.AgendaTbl.NOME,
                null, null);
    }

}