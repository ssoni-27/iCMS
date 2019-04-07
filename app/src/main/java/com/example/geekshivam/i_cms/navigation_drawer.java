package com.example.geekshivam.i_cms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.app.FragmentManager;
import android.widget.Toast;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String currentFragment="";
    public final String TAG="iCMS";

    public MySQLiteOpenHelper myDB;

    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Complaints");

    final String preference="iCMSpreferences";
    final String userNamekey="userName";
    final String userEmailkey="userEmail";

    CardView a;
    CardView b;
    CardView c;
    TextView header_name;
    TextView header_email;
    CircleImageView header_image;

    //Header information
    FirebaseUser user;
    String userName="Anonymous";
    String userEmail="";
    Uri userPhotoUrl=null;

    //Firebase Objects
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Log.d(TAG,"nav_drawer:onCreate() called");


        mFirebaseAuth=FirebaseAuth.getInstance();

        getUserDetail();
        Log.d("iCMS","User data:"+userName+userEmail+userPhotoUrl);

        //Create SQL database access instance and populate if needed.
        myDB=new MySQLiteOpenHelper(this);
        Log.d("iCMS","Database count:"+myDB.getAllData().getCount());
        if(myDB.getAllData().getCount()==0 && userEmail.length()==34)
        {
            myDB.fillDatabase(userEmail);
        }

        //update database
        myDB.updateDatabase(userEmail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //gridView
        a=findViewById(R.id.new1);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridview1_new g=new gridview1_new();

                g.setDataFromActivity(myDB,userEmail);

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
        View headerView = navigationView.getHeaderView(0);

        header_name=(TextView)headerView.findViewById(R.id.header_name);
        header_name.setText(userName);

        header_email=(TextView)headerView.findViewById(R.id.header_email) ;
        header_email.setText(userEmail);

        header_image=(CircleImageView)headerView.findViewById(R.id.header_imageView);
    //TODO:set image
//        URL url = new URL(userPhotoUrl.toString());
//        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        header_image.setImageBitmap(bmp);

        Picasso.get().load(userPhotoUrl).fit().into(header_image);
        //header_image.setImageURI(userPhotoUrl);

    }

    public void getUserDetail()
    {
        Log.d(TAG,"nav_drawer: getUserDetail() called");
        SharedPreferences sp=getSharedPreferences(preference, Context.MODE_PRIVATE);

        if(sp.contains(userNamekey)&&sp.contains(userEmailkey))
        {
            userEmail=sp.getString(userEmailkey,"");
            userName=sp.getString(userNamekey,"Anonymous");

            //TODO:save photo in internal memory and shift command below with get email and name command
            //userPhotoUri=mFirebaseAuth.getCurrentUser().getPhotoUrl();


            if(userName!="Anonymous" && userEmail!="")
                return;
        }

        //TODO:save photo in internal memory and shift command below with get email and name command

        //if user info not in shared preferences
        try
        {
            user=mFirebaseAuth.getCurrentUser();
            if(user!= null) {
                userName = user.getDisplayName();
                userEmail = user.getEmail();
                userPhotoUrl=user.getPhotoUrl();

                //TODO:Logout if not bits email
                if(!userEmail.contains("@pilani.bits-pilani.ac.in"));
                {
                    Toast.makeText(this,"Please login with registered student email id.",Toast.LENGTH_LONG);
                    logout();
                }

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
        Log.d(TAG,"nav_drawer:onBackPressed() called");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Log.d(TAG,"nav_drawer:onNavigationItemSelected() called");


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            profile_activity pa1=new profile_activity();
            getUserDetail();
            pa1.setDataFromActivity(userName,userEmail);

            ft.replace(R.id.containHome, pa1);

            ft.commit();

            // Handle the camera action

        }
        else if (id == R.id.nav_developer) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.containHome, new Developer());

            ft.commit();

        }
        else if (id == R.id.nav_contact) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.containHome, new Contact());

            ft.commit();

        }
        else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout()
    {

        Log.d("iCMS","logout() called.");
        //clear shared preferences
        SharedPreferences sp=getSharedPreferences(preference,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(userNamekey);
        editor.remove(userEmailkey);
        editor.commit();
        Log.d("iCMS","Cleared shared preferences.");

        //TODO:clear database
        getApplicationContext().deleteDatabase("iCMS.db");


        mFirebaseAuth.signOut();
        Log.d("iCMS","logging out.");
        startActivity(new Intent( navigation_drawer.this , i_CMS.class));

        Log.d("iCMS","logged out.");
    }

}
