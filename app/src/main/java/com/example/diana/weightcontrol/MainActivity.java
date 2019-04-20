package com.example.diana.weightcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.TreeMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String MY_SETTINGS = "mysettings";
    Fragment bFragment, calorCalcFragement, bodyFatPercentageFragment, jFragment, startFragment ;
    FragmentTransaction fragTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.apply(); // не забудьте подтвердить изменения

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bFragment = new BMIFragment();
        calorCalcFragement = new CaloriesCalculatorFragment();
        bodyFatPercentageFragment = new BodyFatPercentage();
        jFragment = new JournalFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        fragTrans = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_imt) {
            fragTrans.replace(R.id.fragCont, bFragment);
        } else if (id == R.id.nav_fatmass) {
            fragTrans.replace(R.id.fragCont,bodyFatPercentageFragment);
        } else if (id == R.id.nav_idealweight) {

        } else if (id == R.id.nav_rsk) {
            fragTrans.replace(R.id.fragCont, calorCalcFragement);
        } else if (id == R.id.nav_journal) {
            fragTrans.replace(R.id.fragCont, jFragment);
        } else if (id == R.id.nav_graphic) {

        }
        fragTrans.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public TextView res;

    public void onCountButtonClicked(View view) {
        res = findViewById(R.id.tv_result);
        EditText etAge = findViewById(R.id.et_age);
        EditText etHeight = findViewById(R.id.et_height);
        EditText etWeight = findViewById(R.id.et_weight);
        Spinner spActivity = findViewById(R.id.sp_active);
        String age = etAge.getText().toString();
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();
        String activity = spActivity.getSelectedItem().toString();

        RadioGroup gender = findViewById(R.id.radioGroup_gender);
        int idG = gender.getCheckedRadioButtonId();
        RadioGroup formula = findViewById(R.id.radioGroup_formula);
        int idF = formula.getCheckedRadioButtonId();

        TreeMap<String, Double> act = new TreeMap<>();
        act.put("отсутствие физ. активности", 1.2);
        act.put("1–3 раза в неделю", 1.375);
        act.put("3–4 раза в неделю", 1.55);
        act.put("5–7 раз в неделю", 1.725);
        act.put("ежедневные многократные тренировки", 1.9);
        if (age.isEmpty() | height.isEmpty() | weight.isEmpty() | idG == -1 | idF == -1) {
            idG = -1;
            res.setText("Проверьте введенные данные и повторите попытку");
        }
        switch (idG) {
            case R.id.rb_female:
                switch (idF) {
                    case R.id.rb_harris:
                        Long rskHarris = Math.round((447.593 + 9.247 * Integer.parseInt(weight)
                                + 3.098 * Integer.parseInt(height)
                                - 4.33 * Integer.parseInt(age))
                                * act.get(activity));
                        res.setText("Рекомендуемое РСК: " + rskHarris.toString() + " калорий");
                        break;
                    case R.id.rb_mifflin:
                        Long rskMifflin = Math.round((10 * Integer.parseInt(weight)
                                + 6.25 * Integer.parseInt(height)
                                - 5 * Integer.parseInt(age)
                                - 161)
                                * act.get(activity));
                        res.setText("Рекомендуемое РСК: " + rskMifflin.toString() + " калорий");
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
                        res.setText("Рекомендуемое РСК: " + rskHarris.toString() + " калорий");
                        break;
                    case R.id.rb_mifflin:
                        Long rskMifflin = Math.round((10 * Integer.parseInt(weight)
                                + 6.25 * Integer.parseInt(height)
                                - 5 * Integer.parseInt(age)
                                + 5)
                                * act.get(activity));
                        res.setText("Рекомендуемое РСК: " + rskMifflin.toString() + " калорий");
                        break;
                }
                break;
        }
    }
}
