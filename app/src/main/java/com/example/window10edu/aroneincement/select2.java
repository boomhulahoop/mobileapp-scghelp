package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
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

public class select2 extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private MyAdaptersl2 mAdapter;
    private RecyclerView recyclerView;
    private List<Dataselect2> dataselect2s = new ArrayList<>();
    private String[] phototype = new String[8];
    private int i = 0 ;
    private int selectcement;
    private int typebt ;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select2);


        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("เลือกประเภทงาน");

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize ( true );


        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        Intent itn = getIntent();
        selectcement = sp.getInt("selectcement",0);
        Log.i("checkselectfromimg",Integer.toString(selectcement));

        typebt = sp.getInt("typebtintro",0);
        //setค่า count = 0 ;
        editor = sp.edit();
        editor.putInt("count",0);
        editor.commit();

        //ทดสอบจำนวนรอบ
        int count = sp.getInt("count",8);
        Log.i("countcount","[2]จำนวนรอบที่เทำซ้ำคือ"+Integer.toString(count));


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



        //ชื่อรูปใส่ไว้แปลให้กลายเป็น int
        setphoto();


        //show type of fomat
        if(selectcement == 0){
            getDatatigersuper();
        }else if(selectcement == 1 ){
            getDatatigersuper();
        }else if(selectcement == 2 ){
            getDatatigerglaze();
        }else if(selectcement == 3 ){
            getDatastucter();
        }else if(selectcement == 4 ){
            getDatacast();
        }else if(selectcement == 5 ){
            getDatatigersuper();
        }
        mAdapter = new MyAdaptersl2(dataselect2s);
        recyclerView.setAdapter(mAdapter);


    }


    private void getDatatigersuper() {

        for(i=0 ; i<4;i++) {
            dataselect2s.add(new Dataselect2(getResourceByFilename(this, getPhoto(i))));
        }

    }
    private void getDatatigerglaze() {
        dataselect2s.add(new Dataselect2(getResourceByFilename(this, getPhoto(4))));

    }
    private void getDatastucter() {

        for(i=5;i<7;i++){
            dataselect2s.add(new Dataselect2(getResourceByFilename(this, getPhoto(i))));

        }

    }
    private void getDatacast() {
        dataselect2s.add(new Dataselect2(getResourceByFilename(this, getPhoto(5))));
        dataselect2s.add(new Dataselect2(getResourceByFilename(this, getPhoto(7))));



    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyAdaptersl2) mAdapter).setOnItemClickListener(
                new MyAdaptersl2.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        //Toast.makeText(getApplication(),"Clicked item "+Integer.toString(position)
                        // ,Toast.LENGTH_SHORT).show(); // + Integer.tดoString(position)


                        //เปลี่ยนหน้าระหว่าง mix และ cal
                        if (typebt == 1) {
                            //เสือก่อฉาบเท
                            if (selectcement == 0) {
                                if (position == 0) {
                                    //Log.i("checkshowvideos","t100");
                                    Uri uri = Uri.parse("https://youtu.be/JBKSXt_pth4");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);
                                } else if (position == 1) {
                                    //Log.i("check","t101");
                                    Uri uri = Uri.parse("https://youtu.be/405V4SfxzGo");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);
                                } else if (position == 2) {
                                    //Log.i("check","t102");
                                    Uri uri = Uri.parse("https://youtu.be/l9k1dnxKxKU");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);
                                } else if (position == 3) {
                                    //Log.i("check","t103");
                                    Uri uri = Uri.parse("https://youtu.be/4hYe82ahzLE");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);
                                }
                                //เสือซุปเปอร์
                            } else if (selectcement == 1) {
                                if (position == 0) {
                                    //Log.i("checkshowvideos","t110");
                                    Uri uri = Uri.parse("https://youtu.be/7ZHnHSeoHVA");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 1) {
                                    //Log.i("checkshowvideos","t111");
                                    Uri uri = Uri.parse("https://youtu.be/fms-xKl2aWU");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 2) {
                                    //Log.i("checkshowvideos","t112");
                                    Uri uri = Uri.parse("https://youtu.be/gh-zagsQr8E");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 3) {
                                    // Log.i("checkshowvideos","t113");
                                    Uri uri = Uri.parse("https://youtu.be/eMCl6P2XEtY");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                }

                                //เสือฉาบสูตรพิเศษ
                            } else if (selectcement == 2) {
                                //Log.i("checkshowvideos","t120");
                                Uri uri = Uri.parse("https://youtu.be/NrooRj3gUqY");
                                Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(itn);

                                //ช้างงานโครงสร้าง
                            } else if (selectcement == 3) {
                                if (position == 0) {
                                    // Log.i("checkshowvideos","t130");
                                    Uri uri = Uri.parse("https://youtu.be/QXf5m3C1wI8");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 1) {
                                    Log.i("checkshowvideos", "t131");
                                    Uri uri = Uri.parse("https://youtu.be/azDr9308u3w");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                }
                                //ช้างงานหล่อ
                            } else if (selectcement == 4) {
                                if (position == 0) {
                                    //Log.i("checkshowvideos","t140");
                                    Uri uri = Uri.parse("https://youtu.be/uTIy3QWKEKU");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 1) {
                                    //Log.i("checkshowvideos","t141");
                                    Uri uri = Uri.parse("https://youtu.be/3clWN0tlBhg");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                }
                                //แรด
                            } else if (selectcement == 5) {
                                if (position == 0) {
                                    //Log.i("checkshowvideos","t150");
                                    Uri uri = Uri.parse("https://youtu.be/Hgfr4qZ6iOA");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 1) {
                                    //Log.i("checkshowvideos","t151");
                                    Uri uri = Uri.parse("https://youtu.be/VRbrR3pvn5Y");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 2) {
                                    // Log.i("checkshowvideos","t152");
                                    Uri uri = Uri.parse("https://youtu.be/HAMM49JHjfk");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);

                                } else if (position == 3) {
                                    //Log.i("checkshowvideos","t153");
                                    Uri uri = Uri.parse("https://youtu.be/Tf1uwTET0EQ");
                                    Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(itn);
                                }

                            }

                            //ตั้งค่าให้ไปหน้า select 3
                        } else if (typebt == 2) {
                            //เลือกเสือ , super , แรด
                            if (selectcement == 0 || selectcement == 1 || selectcement == 5) {
                                //เลือกงานก่อ
                                if (position == 0) {
                                    Log.i("checkgotoselect3", "ก่อmix");
                                    Intent itn = new Intent(getBaseContext(), select3.class);
                                    //ประเภทที่เลือก
                                    itn.putExtra("selecttype", position);
                                    sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
                                    editor = sp.edit();
                                    editor.putInt("selecttype",position);
                                    editor.commit();
                                    startActivity(itn);
                                } else {

                                    Intent itn = new Intent(getBaseContext(), getinput.class);
                                    //sentnumberselect
                                    itn.putExtra("selecttype", position);
                                    sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
                                    editor = sp.edit();
                                    editor.putInt("selecttype",position);
                                    editor.commit();
                                    startActivity(itn);

                                }

                            } else {
                                Intent itn = new Intent(getBaseContext(), getinput.class);
                                //sentnumberselect
                                itn.putExtra("selecttype", position);
                                sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
                                editor = sp.edit();
                                editor.putInt("selecttype",position);
                                editor.commit();
                                startActivity(itn);
                            }
                        }


                    }
                }
        );


    }

    private void setphoto(){

        //super + rat
        phototype[0] = "lay";
        phototype[1] = "glaze1";
        phototype[2] = "glaze2";
        phototype[3] = "concrete";


        //tigerglaze
        phototype[4] = "glaze";

        //stucter
        phototype[5] = "concreterough";
        phototype[6] = "concretenormal";

        //cast
        phototype[7] = "concretehard";
    }

    private String getPhoto(int i){
        if(i == 0){
            return phototype[0];
        }else if(i == 1 ){
            return phototype[1];
        }else if(i == 2 ){
            return phototype[2];
        }else if(i == 3 ){
            return phototype[3];
        }else if(i == 4 ){
            return phototype[4];
        }else if(i == 5 ){
            return phototype[5];
        }else if(i == 6 ){
            return phototype[6];
        }else if(i == 7 ){
            return phototype[7];
        }
        return null;
    }
    public static int getResourceByFilename(Context c, String name){
        return c.getResources().getIdentifier(name,"drawable",c.getPackageName());
    }

}
