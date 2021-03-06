package com.example.diana.weightcontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class IdealWeightFragment extends Fragment implements View.OnClickListener {

    EditText height;
    RadioButton gender1, gender2, formula1, formula2;
    Button button;
    TextView result;
    TextView tv_height, tv_gender, tv_formula;

    // удалить ?
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public IdealWeightFragment() {}

    public static IdealWeightFragment newInstance(String param1, String param2) {
        IdealWeightFragment fragment = new IdealWeightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ideal_weight, container, false);

        height = rootView.findViewById(R.id.editText_height);
        gender1 = rootView.findViewById(R.id.male);
        gender2 = rootView.findViewById(R.id.female);
        formula1 = rootView.findViewById(R.id.Devine);
        formula2 = rootView.findViewById(R.id.Robinson);
        result = rootView.findViewById(R.id.result);
        tv_height = rootView.findViewById(R.id.Height);
        tv_gender = rootView.findViewById(R.id.Gender);
        tv_formula = rootView.findViewById(R.id.Formula);
        SharedPreferences sp = getContext().getSharedPreferences("firstSettings",
                Context.MODE_PRIVATE);
        height.setText(sp.getString("height", ""));
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View view){
        if (height.getText().toString().equals("")){
            tv_height.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tv_height.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (!(gender1.isChecked() || gender2.isChecked())){
            tv_gender.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tv_gender.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (!(formula1.isChecked() || formula2.isChecked())){
            tv_formula.setTextColor(Color.parseColor("#FFEC5F5F"));
        }
        else {
            tv_formula.setTextColor(Color.parseColor("#A3362C2C"));
        }
        if (!(height.getText().toString().equals("")) && (gender1.isChecked() || gender2.isChecked()) && (formula1.isChecked() || formula2.isChecked())){

            int value = Integer.parseInt(height.getText().toString());
            if (gender1.isChecked()) {
                if (formula1.isChecked()){
                    int res = (int) (50 + 2.3 * (0.394 * value - 60));
                    result.setText("Ваш идеальный вес " + Integer.toString(res) + " кг");
                } else {
                    int res = (int) (52 + 1.9 * (0.394 * value - 60));
                    result.setText("Ваш идеальный вес " + Integer.toString(res) + " кг");
                }
            }
            if (gender2.isChecked()) {
                if (formula1.isChecked()){
                    int res = (int) (45.5 + 2.3 * (0.394 * value - 60));
                    result.setText("Ваш идеальный вес " + Integer.toString(res) + " кг");
                } else {
                    int res = (int) (49 + 1.7 * (0.394 * value - 60));
                    result.setText("Ваш идеальный вес " + Integer.toString(res) + " кг");
                }
            }
        }
        else {
            result.setText("Проверьте введенные данные и повторите попытку");
        }
    }

}
