package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class calculate extends AppCompatActivity {
    private int  testcycle = 0 ;
    private int count = 0 ;
    SharedPreferences sp;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Intent itn = getIntent();




        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("จำนวนปูนที่ต้องใช้");
        testcycle = testcycle + 1 ;

        Log.i("test",Integer.toString(testcycle));




        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        int selectcement = sp.getInt("selectcement", 0);
        int selecttype = sp.getInt("selecttype",0);
        int selectit = sp.getInt("selectit",7);
        //จำนวนด้านที่คำนวน
        count = sp.getInt("count",0);
        float width = itn.getFloatExtra("width",0);
        float height = itn.getFloatExtra("height",0);
        String thicks =  getIntent().getExtras().getString("thick");
        float thick =  Float.parseFloat(thicks);

        ImageView img = (ImageView) findViewById(R.id.showbag);
        TextView showarea = (TextView)findViewById(R.id.showarea);
        TextView showthick = (TextView)findViewById(R.id.showthick);
        TextView showtcalculate  = (TextView)findViewById(R.id.showtotalcalculate);
        TextView sandcal = (TextView)findViewById(R.id.sand);
        TextView rockcal = (TextView)findViewById(R.id.rockcal);


        //รูปแสดงบนหน้าโชว์
        if (selectcement == 0) {
            img.setImageResource(R.drawable.tigerall1);
        } else if (selectcement == 1) {
            img.setImageResource(R.drawable.tigersuper1);

        } else if (selectcement == 2) {
            img.setImageResource(R.drawable.tigerglaze1);

        } else if (selectcement == 3) {
            img.setImageResource(R.drawable.changstucter1);

        } else if (selectcement == 4) {
            img.setImageResource(R.drawable.changcast1);

        } else if (selectcement == 5) {
            img.setImageResource(R.drawable.rat1);

        }
       // Log.i("showcalculate", Float.toString(itn.getFloatExtra("calculate", 0)) );
       //Log.i("check",Integer.toString(selectcement));
        //Log.i("check",Integer.toString(selecttype));

        Log.i("countcount","จำนวนรอบที่เทำซ้ำคือ"+Integer.toString(count));

        //คำนวนจำนวนถุงปูน


        //เก็บค่าจำนวนถุงไว้ใช้คำนวณหิน + ทราย
        long temp;
        //เสือ ก่อ ฉาบ เท

        if (selectcement == 0) {

            //ก่อ
            if (selecttype == 0) {
                if(selectit == 0){
                    //อิฐมอญ
                    temp = Math.round(Math.ceil((width*height)/2.6));
                    showtcalculate.setText(Math.round(Math.ceil((width*height)/2.6))+"");
                    adddatatoarray((int)Math.round(Math.ceil((width*height)/2.6)));
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");
                }else if(selectit == 1){
                    //อิฐบลีอค
                    temp = Math.round(Math.ceil((width*height)/5));
                    adddatatoarray((int)Math.round(Math.ceil((width*height)/5)));
                    showtcalculate.setText(Math.round(Math.ceil((width*height)/5))+"");
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");
                }


                //ฉาบ1
            } else if (selecttype == 1) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*2.5+"");
                rockcal.setText("-");

              //ฉาบ2
            } else if (selecttype == 2) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*3+"");
                rockcal.setText("-");
                //เท
            } else if (selecttype == 3) {
                temp  = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/7))+"");
                sandcal.setText(temp*2+"");
                rockcal.setText(temp*3+"");
            }

            //เสือ super
        } else if (selectcement == 1) {

            //ก่อ
            if (selecttype == 0) {
                if(selectit == 0){
                    //อิฐมอญ
                    temp = Math.round(Math.ceil((width*height)/2.6));
                    adddatatoarray((int)temp);
                    showtcalculate.setText(Math.round(Math.ceil((width*height)/2.6))+"");
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");

                }else if(selectit == 1){
                    //อิฐบลีอค
                    temp = Math.round(Math.ceil((width*height)/5));
                    adddatatoarray((int)temp);
                    showtcalculate.setText(Math.round(Math.ceil((width*height)/5))+"");
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");
                }

                //ฉาบ1
            } else if (selecttype == 1) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*2.5+"");
                rockcal.setText("-");

                //ฉาบ2
            } else if (selecttype == 2) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*3+"");
                rockcal.setText("-");
                //เท
            } else if (selecttype == 3) {
                temp  = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/7))+"");
                sandcal.setText(temp*2+"");
                rockcal.setText(temp*3+"");
            }



            //เสือ ฉาบสูตรพิเศษ
        } else if (selectcement == 2) {
            temp = Math.round(Math.ceil((width*height*thick)/4));
            adddatatoarray((int)temp);
            showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
            sandcal.setText(temp*3+"");
            rockcal.setText("-");



            //ช้างงานโครงสร้าง
        } else if (selectcement == 3) {
            if(selecttype==0){
                temp = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(temp+"");
                sandcal.setText(temp*3+"");
                rockcal.setText(temp*5+"");

            }else if(selecttype == 1 ){
                temp = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(temp+"");
                sandcal.setText(temp*2+"");
                rockcal.setText(temp*4+"");
            }

            //ช้างงานหล่อ
        } else if (selectcement == 4) {
            if(selecttype ==0){
                temp = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(temp+"");
                sandcal.setText(temp*3+"");
                rockcal.setText(temp*5+"");

            }else if(selecttype == 1){

                temp = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(temp+"");
                sandcal.setText(temp*2+"");
                rockcal.setText(temp*4+"");


            }

            //แรด
        } else if (selectcement == 5) {

            //ก่อ
            if (selecttype == 0) {
                if(selectit == 0){
                    //อิฐมอญ (ก*ย)/2.6
                    temp = Math.round(Math.ceil((width*height)/2.6));
                    adddatatoarray((int)temp);
                    showtcalculate.setText(temp + "");
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");
                }else if(selectit == 1){
                    //อิฐบลีอค (ก*ย)/5
                    temp = Math.round(Math.ceil((width*height)/5));
                    adddatatoarray((int)temp);
                    showtcalculate.setText(Math.round(Math.ceil((width*height)/5))+"");
                    sandcal.setText(temp*3+"");
                    rockcal.setText("-");
                }

                //ฉาบ1
            } else if (selecttype == 1) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*2.5+"");
                rockcal.setText("-");


                //ฉาบ2
            } else if (selecttype == 2) {
                temp = Math.round(Math.ceil((width*height*thick)/4));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/4))+"");
                sandcal.setText(temp*3+"");
                rockcal.setText("-");

                //เท
            } else if (selecttype == 3) {
                temp = Math.round(Math.ceil((width*height*thick)/7));
                adddatatoarray((int)temp);
                showtcalculate.setText(Math.round(Math.ceil((width*height*thick)/7))+"");
                sandcal.setText(temp*2+"");
                rockcal.setText(temp*3+"");

            }



        }






       showarea.setText(Float.toString(width*height));
       showthick.setText(thick+"");








    }

    //method สำหรับส่งชุดข้อมูลไปแสดงผลรวมจำนวนถุง
    public  void adddatatoarray(int x){
        int i = 0;
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("totalbag"+count,x);
        editor.commit();

        /*for(i = 0 ; i<=count ; i++ ){
            Log.i("totalbag","ด้านที่"+(i+1)+" "+Integer.toString(sp.getInt("totalbag"+i,100)));

        }*/

    }




    public void gotocal (View v){
        Intent itn = new Intent(this,getinput.class);
        editor = sp.edit();
        //user กด "คำนวนอีกครั้ง" จะเพิ่มค่า count++
        editor.putInt("count",count+1);
        editor.commit();
        startActivity(itn);
    }

    public void gohome (View v){
        Intent itn = new Intent(this,intro.class);
        editor = sp.edit();
        editor.putInt("count",0);
        editor.commit();
        startActivity(itn);
    }

    public void gofinalcal (View v){
        Intent itn = new Intent(this,finaltocal.class);
        startActivity(itn);
    }


}
