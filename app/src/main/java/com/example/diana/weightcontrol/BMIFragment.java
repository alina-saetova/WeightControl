package com.example.diana.weightcontrol.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diana.weightcontrol.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BMIFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

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

    public BMIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_bmi, container, false);

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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view) {
        textView1.setTextColor(getResources().getColor(R.color.colorText));
        textView2.setTextColor(getResources().getColor(R.color.colorText));
        textView3.setTextColor(getResources().getColor(R.color.colorText));
        textView4.setTextColor(getResources().getColor(R.color.colorText));
        textView5.setTextColor(getResources().getColor(R.color.colorText));
        textView6.setTextColor(getResources().getColor(R.color.colorText));
        textView7.setTextColor(getResources().getColor(R.color.colorText));

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
        }

    }
}
