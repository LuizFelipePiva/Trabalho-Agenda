package com.example.fragmentosdataehora.Models;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class AgendaDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "AgendaDB";

    public AgendaDbHelper(Context context) {
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AgendaDbSchema.AgendaTbl.NOME + "(" +
                AgendaDbSchema.AgendaTbl.Cols.ID + "," +
                AgendaDbSchema.AgendaTbl.Cols.DIA + "," +
                AgendaDbSchema.AgendaTbl.Cols.MES + "," +
                AgendaDbSchema.AgendaTbl.Cols.ANO + "," +
                AgendaDbSchema.AgendaTbl.Cols.HORA + "," +
                AgendaDbSchema.AgendaTbl.Cols.MINUTO + "," +
                AgendaDbSchema.AgendaTbl.Cols.DESCRICAO +
                ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        // Política de upgrade é simplesmente descartar o conteúdo e começar novamente
        db.execSQL("DROP TABLE IF EXISTS " + AgendaDbSchema.AgendaTbl.NOME);
        onCreate(db);
    }


}
