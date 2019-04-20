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
import android.widget.TextView;




public class BMIFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


    TextView textWeight;
    TextView textHeight;
    EditText height;
    EditText weight;
    TextView result;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView warning;

    public BMIFragment() {
    }


    public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_bmi, container, false);
        Drawable back = getActivity().getWindow().getDecorView().getBackground();
        rootView.setBackground(back);
        textHeight = rootView.findViewById(R.id.rost);
        textWeight = rootView.findViewById(R.id.ves);
        height = rootView.findViewById(R.id.editHeightF);
        weight = rootView.findViewById(R.id.editWeightF);
        result = rootView.findViewById(R.id.resultF);
        textView1 = rootView.findViewById(R.id.text1);
        textView2 = rootView.findViewById(R.id.text2);
        textView3 = rootView.findViewById(R.id.text3);
        textView4 = rootView.findViewById(R.id.text4);
        textView5 = rootView.findViewById(R.id.text5);
        textView6 = rootView.findViewById(R.id.text6);
        textView7 = rootView.findViewById(R.id.text7);
        warning = rootView.findViewById(R.id.warning);
        Button button = rootView.findViewById(R.id.btn);
        button.setOnClickListener(this);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view) {
        textView1.setTextColor(getResources().getColor(R.color.colorText1));
        textView2.setTextColor(getResources().getColor(R.color.colorText1));
        textView3.setTextColor(getResources().getColor(R.color.colorText1));
        textView4.setTextColor(getResources().getColor(R.color.colorText1));
        textView5.setTextColor(getResources().getColor(R.color.colorText1));
        textView6.setTextColor(getResources().getColor(R.color.colorText1));
        textView7.setTextColor(getResources().getColor(R.color.colorText1));

        if (height.getText().toString().equals("")) {
            textHeight.setTextColor(Color.parseColor("#FF0000"));
        } else {
            textHeight.setTextColor(Color.parseColor("#808080"));
        }

        if (weight.getText().toString().equals("")) {
            textWeight.setTextColor(Color.parseColor("#FF0000"));
        } else {
            textWeight.setTextColor(Color.parseColor("#808080"));
        }
        if (!(height.getText().toString().equals("")) && !(weight.getText().toString().equals(""))) {
            int h = Integer.parseInt(height.getText().toString());
            int w = Integer.parseInt(weight.getText().toString());
            int index = ((w * 100 * 100) / (h * h));
            if (index < 16) {
                textView1.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if ((index >= 16) && (index < 18.5)) {
                textView2.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if ((index >= 18.5) && (index < 25)) {
                textView3.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if ((index >= 25) && (index < 30)) {
                textView4.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if ((index >= 30) && (index < 35)) {
                textView5.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if ((index >= 35) && (index < 40)) {
                textView6.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if (index >= 40) {
                textView7.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        } else {
            warning.setText("Проверьте введенные данные и повторите попытку");
        }

    }
}
