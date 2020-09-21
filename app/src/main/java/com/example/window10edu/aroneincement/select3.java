package com.example.window10edu.aroneincement;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class select3 extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private MyAdapters3 mAdapter;
    private RecyclerView recyclerView;
    private List<Dataselect3> dataselects3 = new ArrayList<>();
    private String[] phototype = new String[8];
    private int i = 0;
    private int selectcement,selecttype;
    private int typebt;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select3);



        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("เลือกชนิดอิฐ");


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize ( true );

        //รับค่าจาก select 2
        Intent itn = getIntent();
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        selectcement = sp.getInt("selectcement",0);
        selecttype = sp.getInt("typebtintro",0);
        //setค่า count = 0 ;
        editor = sp.edit();
        editor.putInt("count",0);
        editor.commit();


        //ทดสอบจำนวนรอบ
        int count = sp.getInt("count",8);
        Log.i("countcount","[3]จำนวนรอบที่เทำซ้ำคือ"+Integer.toString(count));

        // tab ข้าง่ล่าง
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

        //ชื่อรูปใส่ไว้แปลให้กลายเป็น int
        setphoto();
        dataselects3.add(new Dataselect3(getResourceByFilename(this,getPhoto(0))));
        dataselects3.add(new Dataselect3(getResourceByFilename(this,getPhoto(1))));
        mAdapter = new MyAdapters3(dataselects3);
        recyclerView.setAdapter(mAdapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyAdapters3) mAdapter).setOnItemClickListener(
                new MyAdapters3.MyClickListener(){
                    @Override
                    public void onItemClick(int position, View v){
                        //Toast.makeText(getApplication(),"Clicked item "+Integer.toString(position)
                        // ,Toast.LENGTH_SHORT).show(); // + Integer.tดoString(position)

                        Intent itn = new Intent(getBaseContext(), getinput.class);
                        itn.putExtra("selectit",position);
                        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putInt("selectit",position);
                        editor.commit();
                        startActivity(itn);










                    }
                }
        );

    }

    private void setphoto(){


        phototype[0] = "itmorn";
        phototype[1] = "itblock";

    }

    private String getPhoto(int i){
        if(i == 0){
            return phototype[0];
        }else if(i == 1 ){
            return phototype[1];
        }else if(i == 1 ){
            return phototype[1];
        }
        return null;
    }

    public static int getResourceByFilename(Context c, String name){
        return c.getResources().getIdentifier(name,"drawable",c.getPackageName());
    }
}
