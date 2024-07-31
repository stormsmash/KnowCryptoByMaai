package com.example.knowcryptobymaai;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<SliderItems> dataList;
    private Context context;
    //ประกาศตัวแปรอาร์เรย์ เพื่อเก็บไฟล์เสียง ไฟล์ข้อความ ไฟล์ลิงค์เว็บ ตามจำนวนการเลื่อนข้อมูลที่ต้องการ
//เปลี่ยนข้อมูลได้ตามต้องการหรือเพิ่มได้ตามต้องการ
    Integer[] sound = {R.raw.bitcoin, R.raw.bnb, R.raw.stellar, R.raw.tezos, R.raw.cardano, R.raw.dogecoin, R.raw.ethereum, R.raw.solana, R.raw.terraluna, R.raw.xrp};
    String[] name = {"bitcoin", "bnb", "stellar", "tezos", "cardano", "dogecoin", "ethereum", "solana", "terraluna", "xrp"};
    String[] detail = {"BTC คือสกุลเงินดิจิทัล (Cryptocurrency) สกุลแรกของโลกที่ถูกสร้างขึ้นบน “บล็อกเชน” (Blockchain) ซึ่งเป็นเทคโนโลยีที่ใช้สำหรับตรวจสอบธุรกรรมใด ๆ ที่เกี่ยวข้องกับบิตคอยน์ หัวใจของบิตคอยน์คือ “การกระจายศูนย์” (Decentralized) ที่ปราศจากการควบคุมจากตัวกลาง", "BNB หรือ Binance Coin เป็นสกุลเงินดิจิทัลชนิดหนึ่งในตลาดคริปโทเคอร์เรนซี เปิดตัวในปี 2017 โดยผู้สร้างคือ “Binance” ซึ่งเป็นแพลตฟอร์มซื้อขายแลกเปลี่ยนสกุลเงินดิจิทัลที่ใหญ่ที่สุดในโลกที่หลายคนน่าจะรู้จักเป็นอย่างดี", "Stellar คือแพลตฟอร์มการชำระเงินแบบ Open-source ที่มุ่งเชื่อมผู้คน ระบบการชำระเงิน และสถาบันการเงินเข้าด้วยกันเพื่อลดต้นทุนการโอนเงินข้ามพรมแดนให้ถูกกว่าและเร็วกว่า Stellar อยู่ภายใต้เครือข่ายการชำระเงินของ Ripple และโครงสร้างพื้นฐานถูกสร้างจาก Ripple เช่นกัน", "เทโซส (Tezos) คือเครือข่ายบล็อกเชนที่สนับสนุนการทำสัญญาอัจฉริยะ (Smart Contract) เพื่อการนำสินทรัพย์ สัญญาเช่า หรือหลักทรัพย์ใด ๆ มาแปลงเป็นสินทรัพย์ตามสกุลเงินดิจิทัลต่าง ๆ ที่ต้องการลงทุน เช่น การออกโทเคนดิจิทัลเพื่อการลงทุน (investment tokens) หรือหลักทรัพย์ดิจิทัล", "Cardano (ADA) คือ Blockchain Platform ที่สามารถสร้าง Smart Contract ได้ โดยจะมีเหรียญ ADA ที่เป็นเหรียญที่เอาไว้จ่ายค่าธรรมเนียมในการทำธุรกรรม Cardano ได้รับฉายาว่า Ethereum Killer เนื่องจากทำได้ทุกอย่างอย่างที่ ETH แต่เร็วกว่า", "Dogecoin (DOGE) อ่านว่า “โดชคอยน์” คือ สกุลเงินดิจิทัลแบบ Open-source และเป็นแพลตฟอร์มการกระจายอำนาจบนเครือข่าย P2P ถือเป็นเหรียญ Altcoin และเหรียญ Meme ที่ทำขึ้นมาเพื่อล้อเลียนบิทคอยน์ก่อนจะได้รับความนิยมจนเป็น Meme coin", "Ethereum คือ เครือข่าย Blockchain ที่เป็นแพลตฟอร์มให้เหล่านักพัฒนาสามารถสร้างแอพพลิเคชันแบบกระจายศูนย์ หรือ Dapps (Decentralized Application) Ethereum ถูกสร้างขึ้นโดย Vitalik Bulletin และเปิดตัวในปี 2015 โดยเขาเคยเป็นผู้ร่วมก่อตั้งเว็บไซต์ Bitcoin Magazine", "Solana (SOL) เป็นโปรเจกต์โปรโตคอลที่นำเทคโนโลยี Blockchain มาสร้าง Smart contract เช่นเดียวกับ Ethereum (ETH) และ Cardano (ADA) และยังเป็นโปรเจกต์ Open Source ที่เปิดให้นักพัฒนาเข้ามาสร้าง DApps ได้อีกด้วย", "Terra หรือ LUNA เป็นโทเคนและเป็นแพลตฟอร์ม Dual-Token ที่มีเป้าหมายเพื่อแก้ปัญหาสำหรับเหรียญ Stablecoin ชั้นนำภายในกลุ่มสกุลเงินดิจิทัลต่าง ๆ เว็บไซต์ Whitepaper", "XRP คือสกุลเงินดิจิทัลที่สร้างขึ้นโดยบริษัท Ripple มีวัตถุประสงค์เพื่อใช้เป็นระบบโอนเงินข้ามประเทศที่รวดเร็วและมีค่าธรรมเนียมต่ำ"};

    String sSource = "";
    String[] sDestination = {
            "https://www.google.co.th/maps/place/มหาวิทยาลัยเทคโนโลยีราชมงคลธัญบุรี/@14.0366803,100.7254732,17z/data=!3m1!4b1!4m5!3m4!1s0x311d78a4a8713c3f:0xf019238243532a0!8m2!3d14.0366751!4d100.7276619?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
            "https://www.google.co.th/maps/place/มหาวิทยาลัยศรีนครินทรวิโรฒ/@13.9256795,100.619688,11z/data=!4m9!1m2!2m1!1z4Lih4Lio4Lin!3m5!1s0x311d9f58a2f74ff3:0x78e525c902b7e4c4!8m2!3d14.1074545!4d100.9820801!15sCgnguKHguKjguKdaDCIK4LihIOC4qOC4p5IBEXB1YmxpY191bml2ZXJzaXR5mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVJ2YlRoTU0zRm5SUkFC?hl=th",
    };




    public MyAdapter(List<SliderItems> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }







    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new ViewHolder(view);
    }


    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ShowName;
        MediaPlayer ShowSound;
        TextView ShowDetail;
        //เพิ่ม
        ImageView imageMap;
        TextView txtmap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            ShowName = itemView.findViewById(R.id.txtname);
            ShowDetail = itemView.findViewById(R.id.txtdetail);
            //ที่เพิ่มเครื่องมา
            imageMap = itemView.findViewById(R.id.imgmap);
            txtmap = itemView.findViewById(R.id.txtmap);
        }
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(dataList.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ShowName.setText(name[holder.getAdapterPosition()]);
                holder.ShowDetail.setText(detail[holder.getAdapterPosition()]);
                holder.ShowSound = MediaPlayer.create(context,sound[holder.getAdapterPosition()]);
                holder.ShowSound.start();
                holder.txtmap.setText("นำทางคลิกที่รูป");
                holder.imageMap.setImageResource(R.drawable.mapicon);
                holder.imageMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DesplayTrack(sSource,sDestination[holder.getAdapterPosition()]);
                    }
                });
            }
        });


    }
    private void DesplayTrack(String sSource, String sDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource+ "/"
                    + sDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/appsdetail?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
}//ปิดคลาส
