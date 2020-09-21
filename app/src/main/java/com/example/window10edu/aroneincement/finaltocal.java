package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class finaltocal extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String[] numberbag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaltocal);


        //เรียก toolbar และ กำหนดชื่อ
        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);
        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("จำนวนถุงปูนทั้งหมดที่ต้องใช้");


        ListView lv = (ListView) findViewById(R.id.finalcallist);



        //เรียกค่าจำนวนครั้งที่คำนวนเพื่อนำไปเรียก method getnumberbagนำไปสร้างเป็น array
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        int count = sp.getInt("count",0);
        Log.i("totalSbagfianl","ด้านที่"+(count+1)+" "+Integer.toString(sp.getInt("totalbag"+count,100)));
        getnumberbag(count);

        //สร้าง listview และปรับขนาดของตัวอักษร
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numberbag){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
                tv.setTextColor(Color.parseColor("#676767"));

                // Return the view
                return view;
            }
        };


        lv.setAdapter(adapter);


        //รับค่าชนิดปูนที่เลือกเพื่อนำไปเลือกรูปมาแสดง
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        int selectcement = sp.getInt("selectcement", 0);
        ImageView img = (ImageView) findViewById(R.id.showbag);
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

        //คำนวณผลรวมของจำนวนถุงของทุกด้าน
        TextView tv = (TextView)findViewById(R.id.totalbagnumber);
        int totalbag = gettotalbag(count);
        tv.setText(Integer.toString(totalbag));










    }

    public int gettotalbag(int count){
        int i,temp = 0  ;
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        for(i = 0; i<=count;i++){
            temp = temp + sp.getInt("totalbag"+i,1000);
        }

        return  temp ;
    }

    public void getnumberbag(int  count ){
        numberbag = new String[count + 1];
        int i ;
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        for(i = 0; i<=count;i++){
            numberbag[i] = "ด้านที่ "+(i+1)+" :"+" ใช้ปูนจำนวน "+Integer.toString(sp.getInt("totalbag"+i,1000))+" ถุง";
        }


    }
    public void gohome (View v){
        Intent itn = new Intent(this,intro.class);
        editor = sp.edit();
        editor.putInt("count",0);
        editor.commit();
        startActivity(itn);
    }
}
