package com.example.fragmentosdataehora.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.widget.DatePicker;



import java.util.Calendar;

public class FragmentoDatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public interface DatePickerListener {
        void onDateSelected(int ano, int mes, int dia);
    }


@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
    return new DatePickerDialog(requireContext(), this, ano, mes, dia);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Log.d("DataHora", "Ano: "+String.valueOf(year));
        Log.d("DataHora", "Mês: "+String.valueOf(month+1));
        Log.d("DataHora", "Dia: "+String.valueOf(day));

        DatePickerListener listener = (DatePickerListener) getActivity();
        listener.onDateSelected(year, month, day);

    }
}
