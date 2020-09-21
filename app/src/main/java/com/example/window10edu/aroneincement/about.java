package com.example.window10edu.aroneincement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class about extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("เกี่ยวกับแอปพลิเคชั่น");




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
}
