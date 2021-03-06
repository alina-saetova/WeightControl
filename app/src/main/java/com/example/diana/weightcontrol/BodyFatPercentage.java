package com.example.diana.weightcontrol;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;


public class BodyFatPercentage extends Fragment implements View.OnClickListener {
    EditText weight, height, waist, hip, neck;
    double heightInt, waistInt, hipInt, neckInt;
    double percent;
    Button button;
    TextView result, row1, row2, row3, row4, text1, text2, text3, text4;
    RadioGroup radioGroup;
    RadioButton radioBut1, radioBut2;
    TableLayout table;
    TextView tvgender, tvweight, tvheight, tvwaist, tvhip, tvneck;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_fat_percentage, container, false);

        weight = view.findViewById(R.id.editText_weight);
        height = view.findViewById(R.id.editText_height);
        waist = view.findViewById(R.id.editText_waist);
        hip = view.findViewById(R.id.editText_hip);
        neck = view.findViewById(R.id.editText_neck);
        row1 = view.findViewById(R.id.row1);
        row2 = view.findViewById(R.id.row2);
        row3 = view.findViewById(R.id.row3);
        row4 = view.findViewById(R.id.row4);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);

        tvgender = view.findViewById(R.id.gender);
        tvweight = view.findViewById(R.id.weight);
        tvheight = view.findViewById(R.id.height);
        tvwaist = view.findViewById(R.id.waist);
        tvhip = view.findViewById(R.id.hip);
        tvneck = view.findViewById(R.id.neck);

        result = view.findViewById(R.id.text_result);
        radioGroup = view.findViewById(R.id.radio_group);

        radioBut1 = view.findViewById(R.id.radio_but_female);
        radioBut2 = view.findViewById(R.id.radio_but_male);

        table = view.findViewById(R.id.table);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
        SharedPreferences sp = getContext().getSharedPreferences("firstSettings",
                Context.MODE_PRIVATE);
        weight.setText(sp.getString("weight", ""));
        height.setText(sp.getString("height", ""));
        return view;
    }


    public static double percentFatMale(double height, double waist, double neck) {
        double percent = 495 / (1.0324 - (0.19077 * (Math.log10(waist - neck))) + (0.15456 * (Math.log10(height)))) - 450;
        percent = (double)Math.round(percent * 100) / 100;
        return percent;
    }

    public static double percentFatFemale(double height, double waist, double hip, double neck) {
        double percent = 495 / (1.29579 - 0.35004 * (Math.log10(waist + hip - neck)) + 0.22100 * (Math.log10(height))) - 450;
        percent = (double) Math.round(percent * 100) / 100;
        return percent;
    }


    @Override
    public void onClick(View v) {
        String heightS = height.getText().toString();
        String waistS = waist.getText().toString();
        String hipS = hip.getText().toString();
        String neckS = neck.getText().toString();

        int idGen = radioGroup.getCheckedRadioButtonId();

        row1.setTextColor(getResources().getColor(R.color.textNav));
        row2.setTextColor(getResources().getColor(R.color.textNav));
        row3.setTextColor(getResources().getColor(R.color.textNav));
        row4.setTextColor(getResources().getColor(R.color.textNav));
        text1.setTextColor(getResources().getColor(R.color.textNav));
        text2.setTextColor(getResources().getColor(R.color.textNav));
        text3.setTextColor(getResources().getColor(R.color.textNav));
        text4.setTextColor(getResources().getColor(R.color.textNav));
        if (waistS.isEmpty()) {
            tvwaist.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvwaist.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (heightS.isEmpty()) {
            tvheight.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvheight.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (hipS.isEmpty()) {
            tvhip.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvhip.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (neckS.isEmpty()) {
            tvneck.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvneck.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (idGen == -1) {
            tvgender.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvgender.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if(!heightS.isEmpty() && !waistS.isEmpty() && !hipS.isEmpty() && !neckS.isEmpty() && idGen != -1){
            switch (idGen) {
                case R.id.radio_but_female:


                    result.setTextColor(getResources().getColor(R.color.textNav));
                    row1.setText("< 22%");
                    row2.setText("23%-34%");
                    row3.setText("35%-40%");
                    row4.setText("> 40%");
                    table.setVisibility(View.VISIBLE);
                    percent = percentFatFemale(Integer.parseInt(heightS), Integer.parseInt(waistS),
                            Integer.parseInt(hipS), Integer.parseInt(neckS));
                    result.setText("Процент жира в организме:\n " + String.valueOf(percent) + "%");
                    if (percent <= 22.0) {
                        text1.setTextColor(getResources().getColor(R.color.colorBmi));
                        row1.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if ((percent >= 23.0) & (percent <= 34.0)) {
                        text2.setTextColor(getResources().getColor(R.color.colorBmi));
                        row2.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if ((percent >= 35.0) && (percent <= 40.0)) {
                        text3.setTextColor(getResources().getColor(R.color.colorBmi));
                        row3.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if (percent > 40.0) {
                        text4.setTextColor(getResources().getColor(R.color.colorBmi));
                        row4.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    result.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_but_male:
                    result.setTextColor(getResources().getColor(R.color.textNav));
                    row1.setText("< 8%");
                    row2.setText("9%-20%");
                    row3.setText("21%-25%");
                    row4.setText("> 25%");
                    table.setVisibility(View.VISIBLE);
                    percent = percentFatMale(Integer.parseInt(heightS), Integer.parseInt(waistS),
                            Integer.parseInt(neckS));
                    result.setText("Процент жира в организме:\n " + String.valueOf(percent) + "%");
                    if (percent <= 8.0) {
                        text1.setTextColor(getResources().getColor(R.color.colorBmi));
                        row1.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if ((percent >= 9.0) & (percent <= 20.0)) {
                        text2.setTextColor(getResources().getColor(R.color.colorBmi));
                        row2.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if ((percent >= 21.0) && (percent <= 25.0)) {
                        text3.setTextColor(getResources().getColor(R.color.colorBmi));
                        row3.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    if (percent > 25.0) {
                        text4.setTextColor(getResources().getColor(R.color.colorBmi));
                        row4.setTextColor(getResources().getColor(R.color.colorBmi));
                    }
                    result.setVisibility(View.VISIBLE);
                    break;
            }
        }
        else {
            result.setText("Проверьте введенные данные\n и повторите попытку");
            result.setVisibility(View.VISIBLE);
        }
    }
}
