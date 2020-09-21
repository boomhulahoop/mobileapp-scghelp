package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;


public class intro extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private ImageButton check;


    // for permission requests
    public static final int REQUEST_PERMISSION = 300;

    // request code for permission requests to the os for image
    public static final int REQUEST_IMAGE = 100;

    // will hold uri of image obtained from camera
    //เก็บที่อยู่ของไฟล์รูป
    private Uri imageUri;

    // string to send to next activity that describes the chosen classifier
    private String chosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

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



        // request permission to use the camera on the user's phone
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        }


        // request permission to write data (aka images) to the user's external storage of their phone
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        }

        // request permission to read data (aka images) from the user's external storage of their phone
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        }

        //onclick check
        /*onclick สำหรับการทำ ตรวจสอบปนะเภทถุงปูน
          เก็บค่าชื่อของ model ที่ใช้ทำ imp เพื่อเอาไปใช้กับ interpreter ในขั้นตอนต่อไป
          เรียก method openCameraIntent() เพื่อเปิดกล้องและเริ่มการทำงาน
         */
        check = (ImageButton)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filename in assets
                chosen = "model299.tflite";
                // model in not quantized
                // open camera
                //Log.i("testchangractivity","pass");
               openCameraIntent();
            }
        });





    }
    // openCameraIntent()
    // opens camera for user
    private void openCameraIntent(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        // tell camera where to store the resulting picture
        //imageUri เก็บตำแหน่งที่จะบันทึกรูปและset Title descrition
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //สร้าง Intent เพื่อขอใช้งานกล้อง
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //กำหนดที่อยู่ไฟล์โดยใช้จากตัวแปร imageUri ที่ได้กำหนดไว้แล้ว
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // start camera, and wait for it to finish
        //เปิดกล้องแล้วรอผู้ใช้งานถ่ายรูป เมื่อถ่ายรูปจะเข้าไปทำงานที่ onActivityResult()
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    // checks that the user has allowed all the required permission of read and write and camera. If not, notify the user and close the application
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(getApplicationContext(),"This application needs read, write, and camera permissions to run. Application now closing.",Toast.LENGTH_LONG);
                System.exit(0);
            }
        }
    }


    // dictates what to do after the user takes an image, selects and image, or crops an image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // if the camera activity is finished, obtained the uri, crop it to make it square, and send it to 'Classify' activity
        /*

         */
        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            try {
                Uri source_uri = imageUri;
                Uri dest_uri = Uri.fromFile(new File(getCacheDir(), "cropped"));
                // need to crop it to square image as CNN's always required square input
                Crop.of(source_uri, dest_uri).asSquare().start(intro.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // if cropping acitivty is finished, get the resulting cropped image uri and send it to 'Classify' activity
        else if(requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK){
            imageUri = Crop.getOutput(data);
            Intent i = new Intent(intro.this, Classify.class);
            // put image data in extras to send
            i.putExtra("resID_uri", imageUri);
            // put filename in extras
            i.putExtra("chosen", chosen);

            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("typebtintro",2);
            editor.commit();


            // send other required data
            startActivity(i);
        }
    }





    public void mix (View v){
        Intent itn = new Intent(this,select1.class);
        itn.putExtra("typebtintro",1);
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("typebtintro",1);
        editor.commit();


        //Log.i("testshpintrobt", Integer.toString(sp.getInt("typebtintro",0)));
        startActivity(itn);
    }

    public void cal (View v){
        Intent itn = new Intent(this,select1.class);
        itn.putExtra("typebtintro",2);
        sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("typebtintro",2);
        editor.commit();

        //Log.i("testshpintrobt", Integer.toString(sp.getInt("typebtintro",0)));
        startActivity(itn);
    }

    public void chat (View v){
        Uri uri = Uri.parse("https://line.me/R/ti/p/%40971tabbq");
        Intent itn = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(itn);
    }

    public void feed (View v){
        Uri uri = Uri.parse("https://line.me/R/ti/p/%40971tabbq");
        Intent itn = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(itn);
    }


    public void exit (View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
