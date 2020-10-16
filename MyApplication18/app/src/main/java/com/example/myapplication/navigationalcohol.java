package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class navigationalcohol extends AppCompatActivity {

    DrawerLayout d1;
    Toolbar t1;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationalcohol);

        mAuth = FirebaseAuth.getInstance();

        d1 = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        setUpToolbar();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.logout:
                        mAuth.signOut();
                        Intent k = new Intent(navigationalcohol.this, MainActivity.class);
                        k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(k);
                        return true;
                    case R.id.share:
                        Intent m=new Intent(Intent.ACTION_SEND);
                        m.setType("text/plain");
                        String ShareSubject=("your subject here");
                        m.putExtra(Intent.EXTRA_SUBJECT,ShareSubject);
                        startActivity(Intent.createChooser(m,"share using"));
                        return true;
                    case R.id.myinformation:
                        Intent i = new Intent(navigationalcohol.this, myinfoalcohol.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        return true;

                    case R.id.feedback:
                        Intent n = new Intent(navigationalcohol.this, feedback.class);
                        n.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(n);
                        return true;

                    default:
                        break;
                }
                d1 = findViewById(R.id.drawer);
                d1.closeDrawer(GravityCompat.START);
                return true;
            }

        });


    }
    public void setUpToolbar() {
        d1 = findViewById(R.id.drawer);
        t1 = findViewById(R.id.toolbar);
        setSupportActionBar(t1);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayUseLogoEnabled(false);
        actionbar.setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
        actionbar.setIcon(android.R.color.transparent);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d1, t1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        d1.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }


}
