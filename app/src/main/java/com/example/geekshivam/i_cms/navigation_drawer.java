package com.example.geekshivam.i_cms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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
import android.app.Fragment;
import android.app.FragmentManager;

public class navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String currentFragment="";

    public MySQLiteOpenHelper myDB;

    CardView a;
    CardView b;
    CardView c;
    TextView header_name;
    TextView header_email;


    String displayName="Anonymous",email="";

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
                    //TODO: undo  this
                    //startActivity(new Intent(navigation_drawer.this,i_CMS.class));
                }
            }
        };

        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        myDB=new MySQLiteOpenHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO:undo comment
        //get display name
       // if(mFirebaseAuth.getCurrentUser().getEmail()!=null) {
//            displayName=mFirebaseAuth.getCurrentUser().getDisplayName();
       //     email = mFirebaseAuth.getCurrentUser().getEmail();
        //}


        //gridView
        a=findViewById(R.id.new1);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridview1_new g=new gridview1_new();

                g.setDataFromActivity(myDB);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.containHome, g);

                ft.commit();
            }
        });
        b=findViewById(R.id.new2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                gridview_2 gv2=new gridview_2();
                gv2.setDataFromActivity(myDB);
                ft.replace(R.id.containHome,gv2);

                ft.commit();
            }
        });
        c=findViewById(R.id.acl);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Active_complaints ac1=new Active_complaints();
                ac1.setDataFromActivity(myDB);

                ft.replace(R.id.containHome, ac1);

                ft.commit();
            }
        });





        //TODO:undo comment.
        //Display name text view
//        displayName_TV=(TextView) findViewById(R.id.displayName_TextView);
//        displayName_TV.setText(displayName);


        header_name=(TextView)findViewById(R.id.header_name);
        //header_name.setText(displayName);

        header_email=(TextView)findViewById(R.id.header_email) ;
        //header_email.setText(email);

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.containHome, new profile_activity());

            ft.commit();
            // Handle the camera action

        }
        else if (id == R.id.nav_developer) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.containHome, new Developer());

            ft.commit();

        }  else if (id == R.id.nav_contact) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.containHome, new Contact());

            ft.commit();

        } else if (id == R.id.nav_logout) {
            mFirebaseAuth.signOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
