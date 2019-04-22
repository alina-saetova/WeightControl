package com.example.diana.weightcontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.drawable.*;


import java.util.TreeMap;

public class CaloriesCalculatorFragment extends Fragment implements View.OnClickListener{

    TextView res;
    EditText etAge;
    EditText etHeight;
    EditText etWeight;
    Spinner spActivity;
    RadioGroup gender;
    RadioGroup formula;
    TextView tvgender;
    TextView tvage;
    TextView tvheight;
    TextView tvweight;
    TextView tvformula;
    TextView tvactivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rsk_fragment, container, false);
        res = view.findViewById(R.id.tv_result);
        etAge = view.findViewById(R.id.et_age);
        etHeight = view.findViewById(R.id.et_height);
        etWeight = view.findViewById(R.id.et_weight);
        spActivity = view.findViewById(R.id.sp_active);
        gender = view.findViewById(R.id.radioGroup_gender);
        formula = view.findViewById(R.id.radioGroup_formula);
        tvage = view.findViewById(R.id.tv_age);
        tvgender = view.findViewById(R.id.tv_gender);
        tvheight = view.findViewById(R.id.tv_height);
        tvweight = view.findViewById(R.id.tv_weight);
        tvformula = view.findViewById(R.id.tv_formula);
        tvactivity = view.findViewById(R.id.tv_active);

//        Drawable back = getActivity().getWindow().getDecorView().getBackground();
//        view.setBackground(back);
        Button count = view.findViewById(R.id.btn_count);
        count.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String age = etAge.getText().toString();
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();
        String activity = spActivity.getSelectedItem().toString();
        int idG = gender.getCheckedRadioButtonId();
        int idF = formula.getCheckedRadioButtonId();

        TreeMap<String, Double> act = new TreeMap<>();
        act.put("отсутствие физ. активности", 1.2);
        act.put("1–2 раза в неделю", 1.375);
        act.put("3–4 раза в неделю", 1.55);
        act.put("5–7 раз в неделю", 1.725);
        act.put("ежедневные многократные тренировки", 1.9);

        if (age.isEmpty()) {
            tvage.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvage.setTextColor(Color.parseColor("#474744"));
        }
        if (height.isEmpty()) {
            tvheight.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvheight.setTextColor(Color.parseColor("#474744"));
        }
        if (weight.isEmpty()) {
            tvweight.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvweight.setTextColor(Color.parseColor("#474744"));
        }
        if (idG == -1) {
            tvgender.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvgender.setTextColor(Color.parseColor("#474744"));
        }
        if (idF == -1) {
            tvformula.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvformula.setTextColor(Color.parseColor("#474744"));
        }
        if (activity.isEmpty()) {
            tvactivity.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tvactivity.setTextColor(Color.parseColor("#474744"));
        }
        if (!age.isEmpty() && !height.isEmpty() && !weight.isEmpty() && idF != 1 && idG != 1 && !activity.isEmpty()) {
            switch (idG) {
                case R.id.rb_female:
                    switch (idF) {
                        case R.id.rb_harris:
                            Long rskHarris = Math.round((447.593 + 9.247 * Integer.parseInt(weight)
                                    + 3.098 * Integer.parseInt(height)
                                    - 4.33 * Integer.parseInt(age))
                                    * act.get(activity));
                            res.setText("Рекомендуемое РСК: \n" + rskHarris.toString() + " калорий");
                            break;
                        case R.id.rb_mifflin:
                            Long rskMifflin = Math.round((10 * Integer.parseInt(weight)
                                    + 6.25 * Integer.parseInt(height)
                                    - 5 * Integer.parseInt(age)
                                    - 161)
                                    * act.get(activity));
                            res.setText("Рекомендуемое РСК: \n" + rskMifflin.toString() + " калорий");
                            break;
                    }
                    break;
                case R.id.rb_male:
                    switch (idF) {
                        case R.id.rb_harris:
                            Long rskHarris = Math.round((float)(88.362 + 13.397 * Integer.parseInt(weight)
                                    + 4.799 * Integer.parseInt(height)
                                    - 5.677 * Integer.parseInt(age))
                                    * act.get(activity));
                            res.setText("Рекомендуемое РСК: \n"+ rskHarris.toString() + " калорий");
                            break;
                        case R.id.rb_mifflin:
                            Long rskMifflin = Math.round((10 * Integer.parseInt(weight)
                                    + 6.25 * Integer.parseInt(height)
                                    - 5 * Integer.parseInt(age)
                                    + 5)
                                    * act.get(activity));
                            res.setText("Рекомендуемое РСК: \n" + rskMifflin.toString() + " калорий");
                            break;
                    }
                    break;
            }
        }

    }
}
