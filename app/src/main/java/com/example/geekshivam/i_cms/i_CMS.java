package com.example.geekshivam.i_cms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class i_CMS extends AppCompatActivity {
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i__cms);
       sign= (Button) findViewById(R.id.sign);
        final Intent intent = new Intent(i_CMS.this, navigation_drawer.class);
       sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
    }

}
