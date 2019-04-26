package com.example.diana.weightcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment bFragment, calorCalcFragement, bodyFatPercentageFragment, idealWeightFragment;
    public final static String MY_SETTINGS = "mysettings";
    Fragment jFragment, graphFrg;
    FragmentTransaction fragTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.calcs_background);
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited) {
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.apply();

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
        graphFrg = new GraphFragmnent();
        calorCalcFragement = new CaloriesCalculatorFragment();
        bodyFatPercentageFragment = new BodyFatPercentage();
        idealWeightFragment = new IdealWeightFragment();
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
            getSupportActionBar().setTitle("Индекс массы тела");
            fragTrans.replace(R.id.fragCont, bFragment);
        } else if (id == R.id.nav_fatmass) {
            getSupportActionBar().setTitle("Процент жировой массы");
            fragTrans.replace(R.id.fragCont, bodyFatPercentageFragment);
        } else if (id == R.id.nav_idealweight) {
            getSupportActionBar().setTitle("Расчет идеального веса");

            fragTrans.replace(R.id.fragCont, idealWeightFragment);
        } else if (id == R.id.nav_rsk) {
            getSupportActionBar().setTitle("Расчет калорий в день");
            fragTrans.replace(R.id.fragCont, calorCalcFragement);
        } else if (id == R.id.nav_journal) {
            getSupportActionBar().setTitle("Журнал взвешиваний");
            fragTrans.replace(R.id.fragCont, jFragment);
        } else if (id == R.id.nav_graphic) {
            getSupportActionBar().setTitle("График изменений");
            fragTrans.replace(R.id.fragCont, graphFrg);
        }
        fragTrans.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
