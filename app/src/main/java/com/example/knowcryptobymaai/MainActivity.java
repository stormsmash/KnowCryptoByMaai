package com.example.knowcryptobymaai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public BottomNavigationView bottomNavigationView ;
    public ViewPager2 viewPager;
    List<SliderItems> dataList;
    MyAdapter myAdapter;
    //ประกาศตัวแปรเพิ่มที่ Class Variable
    public FirebaseAuth registerAuth;
    public TextView showmail;
    public FirebaseAuth.AuthStateListener authListener;
    public Button subscribe,unsubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code สำหรับ Notification
        subscribe = findViewById(R.id.btnSub);
        unsubscribe = findViewById(R.id.btnUnsub);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications","MyNotification" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("news").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "ลงทะเบียบรับข่าวสารไม่สำเร็จ", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "ลงทะเบียบรับข่าวสารสำเร็จ", Toast.LENGTH_LONG).show();
                            subscribe.setEnabled(false);
                        }
                    }
                });
            }
        });


        unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("news").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "ยกเลิกรับข่าวสารไม่สำเร็จ", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "ยกเลิกรับข่าวสารสำเร็จ", Toast.LENGTH_LONG).show();
                            subscribe.setEnabled(true);
                        }
                    }
                });
            }
        });



        viewPager = findViewById(R.id.viewpager);
        dataList = new ArrayList<>();
        dataList.add(new SliderItems(R.drawable.bitcoin));
        dataList.add(new SliderItems(R.drawable.bnb));
        dataList.add(new SliderItems(R.drawable.stellar));
        dataList.add(new SliderItems(R.drawable.tezos));
        dataList.add(new SliderItems(R.drawable.cardano));
        dataList.add(new SliderItems(R.drawable.dogecoin));
        dataList.add(new SliderItems(R.drawable.ethereum));
        dataList.add(new SliderItems(R.drawable.solana));
        dataList.add(new SliderItems(R.drawable.terraluna));
        dataList.add(new SliderItems(R.drawable.xrp));
        myAdapter = new MyAdapter(dataList,this);
        viewPager.setAdapter(myAdapter);
// The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        registerAuth = FirebaseAuth.getInstance();
        showmail = findViewById(R.id.txtEmail);
//ผู้ใช้ที่กำลัง Login
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//สังแสดงผล email
        showmail.setText(user.getEmail());
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new
                                                                         BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                             @Override
                                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                                 switch (item.getItemId()) {
                                                                                     case R.id.action_learn:
                                                                                         startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                                                         overridePendingTransition(0,0);
                                                                                         finish();
                                                                                         return true;
                                                                                     case R.id.action_quiz:
                                                                                         startActivity(new Intent(getApplicationContext(),QuizActivity.class));
                                                                                         overridePendingTransition(0,0);
                                                                                         finish();
                                                                                         return true;
                                                                                     case R.id.action_game:
                                                                                         startActivity(new Intent(getApplicationContext(),GameActivity.class));
                                                                                         overridePendingTransition(0,0);
                                                                                         finish();
                                                                                         return true;
                                                                                 }
                                                                                 return false;
                                                                             }
                                                                         });
    }//ปิด oncreate

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.action_profile:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ผู้พัฒนา");
                builder1.setMessage("ชื่อ - สกุล: นายลภัสธร อ่อนสำลี \n"+
                        "ชื่อ - สกุล: นางสาวอังคนา บำรุงถิ่น \n"+
                        "ชื่อ - สกุล: นางสาวลลิตา นิลน้อย \n"+
                        "version: 1.0.0 \n"+
                        "date: 27 June 2022");
                builder1.show();

                return true;
            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Exit");
                builder.setMessage("Exit Program?");
                builder.setNegativeButton("No",null);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //คำสั่งออกจากระบบ
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                });
                builder.show();
                return true;
            case R.id.action_contact:
                startActivity(new Intent(getApplicationContext(),TestMap.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()< dataList.size()-1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }
                    else
                        viewPager.setCurrentItem(0);
                }
            });
        }
    }//close method


}// ปิด Class