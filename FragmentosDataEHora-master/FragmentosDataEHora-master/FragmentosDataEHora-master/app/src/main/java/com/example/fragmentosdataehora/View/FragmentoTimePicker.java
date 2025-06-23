package com.example.fragmentosdataehora.View;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.example.fragmentosdataehora.Controllers.MainActivity;
import com.example.fragmentosdataehora.Models.Compromissos;

import java.util.Calendar;

/*
Exemplo de https://github.com/udofritzke/FragmentosDataEHora
 */

public class FragmentoTimePicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    public interface TimePickerListener {
        void onTimeSelected(int hora, int minuto);
    }
@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hora, minuto,
                DateFormat.is24HourFormat(getActivity()));
    }
@Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        Log.d("DataHora", "Hora: "+String.valueOf(hourOfDay));
        Log.d("DataHora", "Minuto: "+String.valueOf(minute));

        TimePickerListener listener = (TimePickerListener) getActivity();
        listener.onTimeSelected(hourOfDay, minute);
    }

}