package com.example.diana.weightcontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(getActivity());
        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper databaseHelper = App.getInstance().getDatabaseInstance();
                Note note = new Note();
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                String date = df.format(Calendar.getInstance().getTime());
                EditText weight = view.findViewById(R.id.add_weight);
                if (!weight.getText().toString().isEmpty()) {
                    double currentWeight = Math.round(Double.parseDouble(weight.getText().toString()) * 100.0) / 100.0;
                    double previousWeight = databaseHelper.getJournalDao().getPreviousWeight
                            (databaseHelper.getJournalDao().getDBSize());
                    if ((currentWeight > 30) && (currentWeight < 200)) {
                        note.setDate(date);
                        note.setWeight(currentWeight);
                        System.out.println(currentWeight + " " + previousWeight);
                        if (currentWeight - previousWeight > 0) {
                            String s = "+" + Math.round((currentWeight - previousWeight) * 100.0) / 100.0;
                            note.setChange(s);
                        } else {
                            note.setChange("" + Math.round((currentWeight - previousWeight) * 100.0) / 100.0);
                        }
                        databaseHelper.getJournalDao().insert(note);

                        Fragment jFragment = new JournalFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragCont, jFragment);
                        ft.commit();
                    } else {
                        Toast toast = Toast.makeText(getContext(), "Проверьте введенные данные", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getContext(), "Вы не ввели данные", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
