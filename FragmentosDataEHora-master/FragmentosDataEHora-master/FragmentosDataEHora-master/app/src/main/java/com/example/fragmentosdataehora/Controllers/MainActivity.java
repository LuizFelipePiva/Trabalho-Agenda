package com.example.fragmentosdataehora.Controllers;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentosdataehora.Models.AgendaDb;
import com.example.fragmentosdataehora.Models.Compromissos;
import com.example.fragmentosdataehora.R;
import com.example.fragmentosdataehora.View.FragmentoDatePicker;
import com.example.fragmentosdataehora.View.FragmentoTimePicker;
import android.widget.LinearLayout;
import java.util.ArrayList;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/*
GitHub: https://github.com/udofritzke/FragmentosDataEHora
 */


public class MainActivity extends AppCompatActivity implements FragmentoTimePicker.TimePickerListener, FragmentoDatePicker.DatePickerListener {
    private ArrayList<Compromissos> c = new ArrayList();
    private int diaSelecionado;
    private int mesSelecionado;
    private int anoSelecionado;
    private AgendaDb agendaDb;
    private LinearLayout exibirCompromissos;
    private boolean outroDia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exibirCompromissos = findViewById(R.id.exibirCompromissos);
        agendaDb = new AgendaDb(this);
        Cursor cursor = agendaDb.getCompromissos();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            int dia = cursor.getInt(cursor.getColumnIndexOrThrow("dia"));
            int mes = cursor.getInt(cursor.getColumnIndexOrThrow("mes"));
            int ano = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));
            int hora = cursor.getInt(cursor.getColumnIndexOrThrow("hora"));
            int minuto = cursor.getInt(cursor.getColumnIndexOrThrow("minuto"));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));

            Compromissos compromisso = new Compromissos();
            compromisso.setId(id);
            compromisso.setDia(dia);
            compromisso.setMes(mes);
            compromisso.setAno(ano);
            compromisso.setHora(hora);
            compromisso.setMinuto(minuto);
            compromisso.setDescricao(desc);
            c.add(compromisso);


            Log.d("Cursor2", "ID: " + id + ", DIA: " + dia + ", MES: " + mes);
        }
        c.add(new Compromissos());

    }

    @Override
    public void onTimeSelected(int hora, int minuto) {
        c.get(c.size() - 1).setHora(hora);
        c.get(c.size() - 1).setMinuto(minuto);
        Log.d("Compromisso", "Hora definida: " + hora + ":" + minuto);
    }
    @Override
    public void onDateSelected(int ano, int mes, int dia) {
        anoSelecionado = ano;
        mesSelecionado = mes+1;
        diaSelecionado = dia;
        Log.d("Compromisso", "Data definida: " + diaSelecionado + "/" + mesSelecionado + "/" + anoSelecionado);

        if(outroDia){
            Log.d("BtnOutraData", "Dia: " + diaSelecionado + "Mes: " + mesSelecionado + "Ano: " + anoSelecionado);
            buscaCompromisso(diaSelecionado, mesSelecionado, anoSelecionado);
        }
    }
    public void salvarRespostas(View v){
        EditText TxtDescricao = findViewById(R.id.TxtDescricao);
        String desc = TxtDescricao.getText().toString();

        c.get(c.size() - 1).setAno(anoSelecionado);
        c.get(c.size() - 1).setMes(mesSelecionado);
        c.get(c.size() - 1).setDia(diaSelecionado);


        if (c.get(c.size() - 1).getHora() == -1 || c.get(c.size() - 1).getMinuto() == -1){
            Toast.makeText(this, "Hora invalida!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (c.get(c.size() - 1).getDia() == 0  || c.get(c.size() - 1).getMes() == 0 || c.get(c.size() - 1).getAno() == 0){
            Toast.makeText(this, "Data invalida!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (desc.isEmpty()){
            Toast.makeText(this, "Descricao invalida!", Toast.LENGTH_SHORT).show();
            return;
        }


        c.get(c.size() - 1).setDescricao(desc);
        agendaDb.addCompromisso(c.get(c.size() - 1));
        Log.d("salvarRespostas", "salvarRespostas: salvou novo compromisso:" + c.get(c.size() - 1).toString());
        Toast.makeText(this, "Seu compromisso foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        c.add(new Compromissos());
        TxtDescricao.setText("");


    }
    public void addCompromisso(int dia, int mes, int ano){
        exibirCompromissos.removeAllViews();
        boolean vazio = true;

        for(Compromissos co : c){
            if(co.getDia() == dia && co.getMes() == mes && co.getAno() == ano){
                TextView tx = new TextView(this);
                if(co.getHora() < 10){
                    tx.setText(String.format("%s/%s/%s - 0%s:%s - %s", String.valueOf(co.getDia()), String.valueOf(co.getMes()), String.valueOf(co.getAno()), String.valueOf(co.getHora()), String.valueOf(co.getMinuto()), String.valueOf(co.getDescricao())));
                }
                else if(co.getMinuto() < 10){
                    tx.setText(String.format("%s/%s/%s - %s:0%s - %s", String.valueOf(co.getDia()), String.valueOf(co.getMes()), String.valueOf(co.getAno()), String.valueOf(co.getHora()), String.valueOf(co.getMinuto()), String.valueOf(co.getDescricao())));
                }
                else{
                    tx.setText(String.format("%s/%s/%s - %s:%s - %s", String.valueOf(co.getDia()), String.valueOf(co.getMes()), String.valueOf(co.getAno()), String.valueOf(co.getHora()), String.valueOf(co.getMinuto()), String.valueOf(co.getDescricao())));
                }

                tx.setTextColor(Color.BLACK);
                exibirCompromissos.addView(tx);
                vazio = false;
            }
        }
        if(vazio){
            Toast.makeText(this, "Dia sem compromissos", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscaCompromisso(int dia, int mes, int ano){
        addCompromisso(dia, mes, ano);
    }

    public void btnHoje(View v){

        Calendar calendar = Calendar.getInstance();

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        buscaCompromisso(dia, mes, ano);
    }

    public void btnOutraData(View v){
        outroDia = true;
        DialogFragment newFragment = new FragmentoDatePicker ();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void mostraDialogoTimePicker(View v) {
        DialogFragment newFragment = new FragmentoTimePicker ();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void mostraDialogoDatePicker(View v) {
        outroDia = false;
        DialogFragment newFragment = new FragmentoDatePicker ();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}