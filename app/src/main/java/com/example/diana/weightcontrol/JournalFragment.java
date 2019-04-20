package com.example.diana.weightcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.diana.weightcontrol.DataBaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JournalFragment extends Fragment {

    RecyclerView recyclerView;

    private DataBaseHelper databaseHelper;

    public JournalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_journal, container, false);
        ButterKnife.bind(getActivity());
        recyclerView = rootView.findViewById(R.id.recyclerview);
        Button buttonAdd = rootView.findViewById(R.id.add);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        databaseHelper = App.getInstance().getDatabaseInstance();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                String date = df.format(Calendar.getInstance().getTime());
               if (!date.equals(databaseHelper.getJournalDao().getPreviousDate(databaseHelper.getJournalDao().getDBSize()))) {
                    Fragment addFragment = new AddFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragCont, addFragment);
                    ft.commit();
                } else {
                    Toast toast = Toast.makeText(getContext(), "Сегодня Вы уже взвешивались", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        JournalAdapter adapter =
                new JournalAdapter(getActivity(), databaseHelper.getJournalDao().getAllData());
        recyclerView.setAdapter(adapter);
    }


}
