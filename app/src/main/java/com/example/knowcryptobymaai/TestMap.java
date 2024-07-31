package com.example.knowcryptobymaai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestMap extends AppCompatActivity {
public EditText etsource,etDestination;
    String sSource = "";
    String sdestination = "https://www.google.co.th/maps/place/มหาวิทยาลัยเทคโนโลยีราชมงคลธัญบุรี/@14.0366803,100.7254732,17z/data=!3m1!4b1!4m5!3m4!1s0x311d78a4a8713c3f:0xf019238243532a0!8m2!3d14.0366751!4d100.7276619?hl=th";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);
        etsource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        DesplayTrack(sSource,sdestination);

    }//ปิด onCreate
    //สร้างเมธอด เพื่อ onclick ที่ปุ่ม
    public void  openmap (View view){
        //ต้นทาง ปลายทาง
    //เอาเช็ค เอามาตรวจสอบ ว่าผู้ใช้กรอกไหม
    if(sSource.equals("")&& sdestination.equals(""))
        Toast.makeText(this,"กรุณากรอกต้นทางปลายทาง",
                Toast.LENGTH_SHORT).show();
    else
        DesplayTrack(sSource,sdestination);
    }
    private void DesplayTrack(String sSource, String sDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource+ "/"
                    + sDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/appsdetail?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}//ปิด class

