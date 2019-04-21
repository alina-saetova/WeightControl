package com.example.diana.weightcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.diana.weightcontrol.MainActivity.MY_SETTINGS;


public class StartActivity extends AppCompatActivity {

    public final static String MY_WEIGHT = "weight";
    public final static String MY_HEIGHT = "height";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
        Button button = findViewById(R.id.start_btn);
        mSettings = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DDAC37")));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weight = findViewById(R.id.reg_weight);
                EditText height = findViewById(R.id.reg_height);
                if (!weight.getText().toString().isEmpty() && !height.getText().toString().isEmpty()) {
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(MY_WEIGHT, weight.getText().toString());
                    editor.putString(MY_HEIGHT, height.getText().toString());
                    editor.apply();

                    DataBaseHelper databaseHelper = App.getInstance().getDatabaseInstance();
                    Note note = new Note();
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    String date = df.format(Calendar.getInstance().getTime());
                    note.setDate(date);
                    note.setWeight(Math.round(Double.parseDouble(weight.getText().toString()) * 100.0) / 100.0);
                    note.setChange("0.0");
                    databaseHelper.getJournalDao().insert(note);
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Вы не ввели данные", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });

    }

}
