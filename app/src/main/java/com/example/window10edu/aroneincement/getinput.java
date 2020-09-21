package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class getinput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int selectcement, selecttype,selectit;
    private String x;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getinput);


        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("กรอกค่าเพื่อคำนวณปริมาณปูน");



        Spinner  spinner = findViewById(R.id.thickspinner);
        ArrayAdapter<CharSequence> adpter = ArrayAdapter.createFromResource(this,R.array.thick,R.layout.color_spinder);
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adpter);
        spinner.setOnItemSelectedListener(this);





        Intent itn = getIntent();
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        selectcement = sp.getInt("selectcement", 0);
        selecttype = sp.getInt("selecttype", 0);
        selectit = sp.getInt("selectit",7);
        //Log.i("showcement",Integer.toString(selectcement));

        //Log.i("show2", Integer.toString(selectcement));

        //Log.i("selectit", Integer.toString(selectit));


        //เปลี่ยนสี + ขนาด หspinder

        Spinner colorspinder = findViewById(R.id.thickspinner);






        //setimageshow
        ImageView img = (ImageView) findViewById(R.id.showbag);
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

        final EditText edtwidth, edtheight, edtthick;
        edtwidth = (EditText) findViewById(R.id.editwidth);
        edtheight = (EditText) findViewById(R.id.editheight);
        Button btn = (Button) findViewById(R.id.bttocal);
        //ต้องกรอกเลขก่อนจึงจะกดปุ่มได้


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn;
                float w,h;

                //เช็คช่องว่าง
                if((edtwidth.getText().length() == 0) || (edtheight.getText().length() == 0) ){
                    itn = new Intent(getBaseContext(), getinput.class);
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ครบ" , Toast.LENGTH_SHORT).show();

                }else  {
                    w = Float.parseFloat(edtwidth.getText().toString());
                    h = Float.parseFloat(edtheight.getText().toString());
                    //เช็ค0
                    if(((w == 0) || (h == 0) )) {
                        //itn = new Intent(getBaseContext(), getinput.class);
                        Toast.makeText(getApplicationContext(), "ไม่สามารถใช้ค่า 0 ในการคำนวณได้", Toast.LENGTH_SHORT).show();
                    }else if(selectit ==7){
                        itn = new Intent(getBaseContext(), calculate.class);
                        //Log.i("selectit",Integer.toString(selectit));
                        itn.putExtra("height",h);
                        itn.putExtra("width",w);
                        itn.putExtra("thick",x);
                        startActivity(itn);
                    }else {
                        itn = new Intent(getBaseContext(), calculate.class);
                        //Log.i("selectit",Integer.toString(selectit));
                        itn.putExtra("height",h);
                        itn.putExtra("width",w);
                        itn.putExtra("thick",x);
                        startActivity(itn);

                    }

                }





            }
        });












    }

    public void gohome (View v){
        Intent itn = new Intent(this,intro.class);
        startActivity(itn);
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        x = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




