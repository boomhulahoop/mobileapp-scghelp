package com.example.window10edu.aroneincement;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fist);
        // delete action bar
        //getSupportActionBar().hide();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) { }

                Intent intent = new Intent(getBaseContext(), intro.class);
                startActivity(intent);
            }
        }).start();


    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Intent intent = new Intent(getBaseContext(), intro.class);
        startActivity(intent);

    }


}
