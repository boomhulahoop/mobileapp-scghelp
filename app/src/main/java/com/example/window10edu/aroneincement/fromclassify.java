package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class fromclassify extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fromclassify);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("เลือกประเภทความช่วยเหลือ");





        //bottomNavigationView

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent itn ;
                switch (item.getItemId()) {
                    case R.id.item_home:
                        itn = new Intent(getBaseContext(),intro.class);
                        startActivity(itn);
                        return true;
                    case R.id.item_tutorial:
                        itn = new Intent(getBaseContext(),tutorialtab.class);
                        startActivity(itn);
                        return true;
                    case R.id.item_about:
                        itn = new Intent(getBaseContext(),about.class);
                        startActivity(itn);
                        return true;
                    case R.id.item_exit:
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);


                        return true;
                }
                return false;


            }
        });


    }


    public void mix (View v){
        Intent itn = new Intent(this,select2.class);
        itn.putExtra("typebtintro",1);
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("typebtintro",1);
        editor.commit();


        //Log.i("testshpintrobt", Integer.toString(sp.getInt("typebtintro",0)));
        startActivity(itn);
    }

    public void cal (View v){
        Intent itn = new Intent(this,select2.class);
        itn.putExtra("typebtintro",2);
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("typebtintro",2);
        editor.commit();

        //Log.i("testshpintrobt", Integer.toString(sp.getInt("typebtintro",0)));
        startActivity(itn);
    }
}
