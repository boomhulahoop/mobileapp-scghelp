package com.example.window10edu.aroneincement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
public class Classify extends AppCompatActivity {

    // presets for rgb conversion
    private static final int RESULTS_TO_SHOW = 3;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;


    private BottomNavigationView bottomNavigationView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;



    //สร้างตัว interpreter เพื่อนำไฟล์tfมาrun ใช้งาน
    // options for model interpreter
    private final Interpreter.Options tfliteOptions = new Interpreter.Options();
    // tflite graph
    private Interpreter tflite;
    //listสำหรับclass ทั้งหมด
    // holds all the possible labels for model
    private List<String> labelList;
    //ค่าbyteของไฟล์รูปที่เราถ่าย
    // holds the selected image data as bytes
    private ByteBuffer imgData = null;
    //เก็บค่าของแต่ละ lable
    // holds the probabilities of each label for non-quantized graphs
    private float[][] labelProbArray = null;
    // holds the probabilities of each label for quantized graphs
    private byte[][] labelProbArrayB = null;
    // array that holds the labels with the highest probabilities
    private String[] topLables = null;
    // array that holds the highest probabilities
    private String[] topConfidence = null;

    // selected classifier information received from extras
    private String chosen;

    // input image dimensions for the Inception Model
    private int DIM_IMG_SIZE_X = 299;
    private int DIM_IMG_SIZE_Y = 299;
    private int DIM_PIXEL_SIZE = 3;


    // int array to hold image data
    private int[] intValues;

    // activity elements
    private ImageView selected_image;
    private Button classify_button;
    private Button back_button;
    private TextView label1;
    private TextView label2;
    private TextView label3;
    private TextView Confidence1;
    private TextView Confidence2;
    private TextView Confidence3;

    //การจัดเรียงค่าผล
    // priority queue that will hold the top results from the CNN
    private PriorityQueue<Map.Entry<String, Float>> sortedLabels =
            new PriorityQueue<>(
                    RESULTS_TO_SHOW,
                    new Comparator<Map.Entry<String, Float>>() {
                        @Override
                        public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                            return (o1.getValue()).compareTo(o2.getValue());
                        }
                    });




    @Override
    protected void onCreate(Bundle savedInstanceState) {





        //รับค่าจากหน้า intro
        // get all selected classifier data from classifiers
        chosen = (String) getIntent().getStringExtra("chosen");
        //สร้าง array สำหรับเก็บข้อมูลรูป
        // initialize array that holds image data
        intValues = new int[DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y];

        super.onCreate(savedInstanceState);
        //โหลดtflite
        //initilize graph and labels
        try{
            tflite = new Interpreter(loadModelFile(), tfliteOptions);
            labelList = loadLabelList();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        //สร้าง byte array ขนาดเท่ากับรูปที่รับมา
        // initialize byte array. The size depends if the input data needs to be quantized or not
        imgData =
                ByteBuffer.allocateDirect(
                        4 * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
        imgData.order(ByteOrder.nativeOrder());
        //สร้าง array ตามขนาด lable
        // initialize probabilities array. The datatypes that array holds depends if the input data needs to be quantized or not
        labelProbArray = new float[1][labelList.size()];


        setContentView(R.layout.activity_take__photo);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        ActionBar appbar = getSupportActionBar();
        appbar.setTitle("ประมวลผลรูปภาพ");





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



       /* // labels that hold top three results of CNN
        label1 = (TextView) findViewById(R.id.label1);
        label2 = (TextView) findViewById(R.id.label2);
        label3 = (TextView) findViewById(R.id.label3);
        // displays the probabilities of top labels
        Confidence1 = (TextView) findViewById(R.id.Confidence1);
        Confidence2 = (TextView) findViewById(R.id.Confidence2);
        Confidence3 = (TextView) findViewById(R.id.Confidence3);*/
        // initialize imageView that displays selected image to the user
        selected_image = (ImageView) findViewById(R.id.selected_image);

        // initialize array to hold top labels
        topLables = new String[RESULTS_TO_SHOW];
        // initialize array to hold top probabilities
        topConfidence = new String[RESULTS_TO_SHOW];

        // allows user to go back to activity to select a different image
        back_button = (Button)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Classify.this, intro.class);
                startActivity(i);
            }
        });



        // classify current dispalyed image
        classify_button = (Button)findViewById(R.id.classify_image);
        classify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current bitmap from imageView
                Bitmap bitmap_orig = ((BitmapDrawable)selected_image.getDrawable()).getBitmap();
                // resize the bitmap to the required input size to the CNN
                Bitmap bitmap = getResizedBitmap(bitmap_orig, DIM_IMG_SIZE_X, DIM_IMG_SIZE_Y);
                // convert bitmap to byte array
                convertBitmapToByteBuffer(bitmap);
                // pass byte data to the graph
                tflite.run(imgData, labelProbArray);
                // display the results
                printTopKLabels();
                //Intent i = new Intent(Classify.this, select2.class);
                //startActivity(i);
            }
        });


        // get image from previous activity to show in the imageView
        Uri uri = (Uri)getIntent().getParcelableExtra("resID_uri");
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            selected_image.setImageBitmap(bitmap);
            // not sure why this happens, but without this the image appears on its side
            selected_image.setRotation(selected_image.getRotation() + 90);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // loads tflite grapg from file
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd(chosen);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // converts bitmap to byte array which is passed in the tflite graph
    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        if (imgData == null) {
            return;
        }
        //กลับมาจุดเริ่มต้นของข้อมูล
        imgData.rewind();
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // loop through all pixels
        int pixel = 0;
        for (int i = 0; i < DIM_IMG_SIZE_X; ++i) {
            for (int j = 0; j < DIM_IMG_SIZE_Y; ++j) {
                final int val = intValues[pixel++];
                // get rgb values from intValues where each int holds the rgb values for a pixel.
                // if quantized, convert each rgb value to a byte, otherwise to a float
                    imgData.putFloat((((val >> 16) & 0xFF)-IMAGE_MEAN)/IMAGE_STD);
                    imgData.putFloat((((val >> 8) & 0xFF)-IMAGE_MEAN)/IMAGE_STD);
                    imgData.putFloat((((val) & 0xFF)-IMAGE_MEAN)/IMAGE_STD);

            }
        }
    }

    // loads the labels from the label txt file in assets into a string array
    private List<String> loadLabelList() throws IOException {
        /*
        อ่านค่าของ lable แต่ละบรรทัด ด้วย BufferedReader แล้วพิ่มลง Lablelist
         */
        List<String> labelList = new ArrayList<String>();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(this.getAssets().open("labels299.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            labelList.add(line);
        }
        reader.close();
        return labelList;
    }



    // print the top labels and respective confidences
    private void printTopKLabels() {
        // add all results to priority queue
        for (int i = 0; i < labelList.size(); ++i) {

            //ตรวจสอบค่า ระหว่าง lablelist กับ  labelProbArray
                sortedLabels.add(
                        new AbstractMap.SimpleEntry<>(labelList.get(i), labelProbArray[0][i]));

                //ไล่ pointer ไปเรื่อยๆ
                if (sortedLabels.size() > RESULTS_TO_SHOW) {
                    sortedLabels.poll();
                 }

            }




        // get top results from priority queue
        final int size = sortedLabels.size();
        for (int i = 0; i < size ; ++i) {
            Map.Entry<String, Float> label = sortedLabels.poll();
            topLables[i] = label.getKey();
            topConfidence[i] = String.format("%.0f%%",label.getValue()*100);
        }

       /* // set the corresponding textviews with the results
        label1.setText("1. "+topLables[2]);
        label2.setText("2. "+topLables[1]);
        label3.setText("3. "+topLables[0]);
        Confidence1.setText(topConfidence[2]);
        Confidence2.setText(topConfidence[1]);
        Confidence3.setText(topConfidence[0]);*/
        senddetailselected(topLables[2]);
    }



    // resizes bitmap to given dimensions
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        //ใช้ฟังก์ชัน createBitmap ในการแปลง
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

   public void senddetailselected(String bag){
       Log.i("checkselectfromimg","ทำ");

       sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);

        if(bag.equals("cast")){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",4);
            editor.commit();
            Log.i("checkselectfromimg","castทำ");

        }else if (bag.equals("changstructure")){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",3);
            editor.commit();
            Log.i("checkselectfromimg","changstructureทำ");

        }else if (bag.equals("glaze") ){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",2);
            editor.commit();
            Log.i("checkselectfromimg","glazeทำ");

        }else if (bag.equals("rhino")){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",5);
            editor.commit();
            Log.i("checkselectfromimg","rhino ทำ");

        }else if (bag.equals("tigerall")){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",0);
            editor.commit();
            Log.i("checkselectfromimg"," tigerall ทำ ");

        }else if(bag.equals("tigerallsuper") ){
            sp = getSharedPreferences("checkstatus", Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putInt("selectcement",1);
            editor.commit();
            Log.i("checkselectfromimg","tigerallsuper ทำ");

        }

       Intent i = new Intent(this, fromclassify.class);
       startActivity(i);


    }











}



