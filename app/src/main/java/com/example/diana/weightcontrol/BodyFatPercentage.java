package com.example.diana.weightcontrol;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BodyFatPercentage extends Fragment implements View.OnClickListener {
    EditText weight, height, waist, hip, neck;
    double heightInt, waistInt, hipInt, neckInt;
    double percent;
    Button button;
    TextView result;
    RadioGroup radioGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_fat_percentage, container, false);

        weight = view.findViewById(R.id.editText_weight);
        height = view.findViewById(R.id.editText_height);
        waist = view.findViewById(R.id.editText_waist);
        hip = view.findViewById(R.id.editText_hip);
        neck = view.findViewById(R.id.editText_neck);
//
        result = view.findViewById(R.id.text_result);
        radioGroup = view.findViewById(R.id.radio_group);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
                result.setText("Результат " + String.valueOf(percent));
            }
        });

        return view;
    }

    public void calculateVolume() {
        heightInt = Double.valueOf(height.getText().toString());
        waistInt = Double.valueOf(waist.getText().toString());
        hipInt = Double.valueOf(hip.getText().toString());
        neckInt = Double.valueOf(neck.getText().toString());

        int idRadioBut = radioGroup.getCheckedRadioButtonId();

        if (idRadioBut == R.id.radio_but_female) {
            percent = percentFatFemale(heightInt, waistInt, hipInt, neckInt);
        } else if (idRadioBut == R.id.radio_but_male) {
            percent = percentFatMale(heightInt, waistInt, neckInt);
        }

    }

    public static double percentFatMale(double height, double waist, double neck) {
        double percent = 495 / (1.0324 - (0.19077 * (Math.log10(waist - neck))) + (0.15456 * (Math.log10(height)))) - 450;
        return percent;
    }

    public static double percentFatFemale(double height, double waist, double hip, double neck) {
        double percent = 495 / (1.29579 - 0.35004 * (Math.log10(waist + hip - neck)) + 0.22100 * (Math.log10(height))) - 450;
        return percent;
    }


    @Override
    public void onClick(View v) {

    }
}
