package com.example.geekshivam.i_cms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
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

    final String preference="iCMSpreferences";
    final String userNamekey="userName";
    final String userEmailkey="userEmail";

    CardView a;
    CardView b;
    CardView c;
    TextView header_name;
    TextView header_email;

    //Header information
    String userName="Anonymous";
    String userEmail="";

    //Firebase Objects
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseAuth=FirebaseAuth.getInstance();

//        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                if(firebaseAuth.getCurrentUser()==null)
//                {
//                    //TODO: undo  this
//                    //startActivity(new Intent(navigation_drawer.this,i_CMS.class));
//                }
//            }
//        };
//
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //Create SQL database access instance.
        myDB=new MySQLiteOpenHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getUserDetail();

        header_name=(TextView)findViewById(R.id.header_name);
        header_name.setText(userName);

        header_email=(TextView)findViewById(R.id.header_email) ;
        header_email.setText(userEmail);

    }

    public void getUserDetail()
    {
        SharedPreferences sp=getSharedPreferences(preference, Context.MODE_PRIVATE);
        if(sp.contains(userNamekey)&&sp.contains(userEmailkey))
        {
            userEmail=sp.getString(userEmailkey,"");
            userName=sp.getString(userNamekey,"Anonymous");

            if(userName!="Anonymous" && userEmail!="")return;
        }

        //if user info not in shared preferences
        try
        {
            if(mFirebaseAuth.getCurrentUser()!=null) {
                userName = mFirebaseAuth.getCurrentUser().getDisplayName();
                userEmail = mFirebaseAuth.getCurrentUser().getEmail();

                //TODO:Logout if not bits email

                //Add user info to shared preferences.
                SharedPreferences.Editor editor=sp.edit();
                editor.putString(userNamekey,userName);
                editor.putString(userEmailkey,userEmail);
                editor.commit();

            }
        }
        catch (Exception e)
        {
            //TODO:task if user not found.
            Log.d("iCMS","Unable to get username email.Error:"+e);

        }
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
            logout();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout()
    {
        //clear shared preferences
        SharedPreferences sp=getSharedPreferences(preference,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(userNamekey);
        editor.remove(userEmailkey);
        editor.commit();

        //TODO:clear database

        mFirebaseAuth.signOut();
    }
}
