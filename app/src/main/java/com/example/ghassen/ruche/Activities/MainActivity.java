package com.example.ghassen.ruche.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ghassen.ruche.Entities.User;
import com.example.ghassen.ruche.Fragments.HumidityFrag;
import com.example.ghassen.ruche.Fragments.LightFrag;
import com.example.ghassen.ruche.Fragments.PoidsFrag;
import com.example.ghassen.ruche.R;
import com.example.ghassen.ruche.Fragments.TempFrag;
import com.example.ghassen.ruche.Fragments.TodayFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glide
                .with(this)
                .load(R.drawable.bee);

        navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView navUsername = (TextView) headerView.findViewById(R.id.NomUtili);
        TextView navmail = (TextView) headerView.findViewById(R.id.mailUtili);
        ImageView imageProfil = (ImageView) headerView.findViewById(R.id.imageprofil);

        navUsername.setText(User.login);
        navmail.setText(User.mail);
        navigationView.getMenu().findItem(R.id.com).setVisible(false);

        imageProfil.setImageResource(R.drawable.person);



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new TodayFrag()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new TempFrag()).commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new HumidityFrag()).commit();

        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new PoidsFrag()).commit();


        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new LightFrag()).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id==R.id.today)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new TodayFrag()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
