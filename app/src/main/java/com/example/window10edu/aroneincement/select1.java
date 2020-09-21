package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
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

public class select1 extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private MyAdaptersl1 mAdapter;
    private RecyclerView recyclerView;
    private List<Dataselect1> dataselect1s = new ArrayList<>();
    private int i  = 0 ;
    private String[] nameCement = new String[6];
    private String[] photoCement = new String[6];
    private int typebt ;


    private BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select1);


        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("เลือกชนิดถุงปูน");



        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize ( true );
        Intent itn = getIntent();
        typebt = itn.getIntExtra("typebtintro",0);


        setNameCement();
        setPhotoCement();
        getData();
        mAdapter = new MyAdaptersl1(dataselect1s);
        recyclerView.setAdapter(mAdapter);


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



    private void getData() {

        for(i=0;i<6;i++){
            dataselect1s.add(new Dataselect1(getNameCement(i),getResourceByFilename(this,getPhoto(i))));
        }

//
    }


    @Override
    protected void onResume() {

        super.onResume();
        ((MyAdaptersl1) mAdapter).setOnItemClickListener(
                new MyAdaptersl1.MyClickListener(){
                    @Override
                    public void onItemClick(int position, View v){
                        //Toast.makeText(getApplication(),"Clicked item "+Integer.toString(position)
                               // ,Toast.LENGTH_SHORT).show(); // + Integer.tดoString(position)


                        //change activity
                        Intent itn = new Intent(getBaseContext(),select2.class);
                        //Log.i("check",Integer.toString(typebt));
                        //Log.i("testshpintrobt", Integer.toString(sp.getInt("typebtintro",0)));
                        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putInt("selectcement",position);
                        editor.commit();
                        //itn.putExtra("selectcement",position);
                        startActivity(itn);




                    }
                }
        );

    }

    private void setNameCement(){
        nameCement[0] = "ปูนตราเสือ ก่อ ฉาบ เท";
        nameCement[1] = "ปูนตราเสือ super";
        nameCement[2] = "ปูนตราเสือ ฉาบสูตรพิเศษ";
        nameCement[3] = "ปูนตราช้าง งานโครงสร้าง";
        nameCement[4] = "ปูนตราช้าง งานหล่อ";
        nameCement[5] = "ปูนตรา แรด";
    }
    private void setPhotoCement(){
        photoCement[0] = "tigerall";
        photoCement[1] = "tigersuper";
        photoCement[2] = "tigerglaze";
        photoCement[3] = "changstucter";
        photoCement[4] = "changcast";
        photoCement[5] = "rat";
    }




    private String getNameCement(int i){
        if(i == 0){
            return nameCement[0];
        }else if(i == 1 ){
            return nameCement[1];
        }else if(i == 2 ){
            return nameCement[2];
        }else if(i == 3 ){
            return nameCement[3];
        }else if(i == 4 ){
            return nameCement[4];
        }else if(i == 5 ){
            return nameCement[5];
        }
        return null;

    }

    private String getPhoto(int i){
        if(i == 0){
            return photoCement[0];
        }else if(i == 1 ){
            return photoCement[1];
        }else if(i == 2 ){
            return photoCement[2];
        }else if(i == 3 ){
            return photoCement[3];
        }else if(i == 4 ){
            return photoCement[4];
        }else if(i == 5 ){
            return photoCement[5];
        }
        return null;

    }

    //แปลงstringเป็นint

    public static int getResourceByFilename(Context c, String name){
        return c.getResources().getIdentifier(name,"drawable",c.getPackageName());
    }



}
