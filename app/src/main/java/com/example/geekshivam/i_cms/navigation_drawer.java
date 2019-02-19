package com.example.geekshivam.i_cms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String currentFragment="";

    CardView a;
    CardView b;
    CardView c;
    CardView d;
    Button logout_Btn;
    TextView displayName_TV;

    String displayName="";

    //Firebase Objects
    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(navigation_drawer.this,i_CMS.class));
                }
            }
        };

        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO:get display name
        if(mFirebaseAuth.getCurrentUser().getEmail()!=null)
        displayName=mFirebaseAuth.getCurrentUser().getDisplayName();

        //gridView
        a=findViewById(R.id.new1);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new12=new Intent(navigation_drawer.this,gridview1_new.class);
                startActivity(new12);
            }
        });

        b=findViewById(R.id.new2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new12=new Intent(navigation_drawer.this,gridview1_new.class);
                startActivity(new12);
            }
        });

        c=findViewById(R.id.new3);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new12=new Intent(navigation_drawer.this,gridview1_new.class);
                startActivity(new12);
            }
        });

        d=findViewById(R.id.new4);
            d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new12=new Intent(navigation_drawer.this,gridview1_new.class);
                startActivity(new12);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(navigation_drawer.this,add_compilant.class);
                startActivity(i);
            }
        });

        //Display name text view
        displayName_TV=findViewById(R.id.displayName_TextView);
        displayName_TV.setText(displayName);

        //Log out button
        logout_Btn=findViewById(R.id.logout_button);
        logout_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

        if (id == R.id.nav_profile) {
            Intent intent=new Intent(this,profile_activity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_pc) {
            Intent pc = new Intent(this,Previous_complaints.class);
            startActivity(pc);

        } else if (id == R.id.nav_developer) {
            Intent developer = new Intent(this,Developer.class);
            startActivity(developer);

        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(this,Settings.class);
            startActivity(settings);

        } else if (id == R.id.nav_contact) {
            Intent contact = new Intent(this,Contact.class);
            startActivity(contact);

        } else if (id == R.id.nav_logout) {
            Intent logout = new Intent(this,i_CMS.class);
            startActivity(logout);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
