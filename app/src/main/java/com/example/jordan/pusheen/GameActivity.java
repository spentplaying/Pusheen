package com.example.jordan.pusheen;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private final int time_bound = 30;
    private int remain_time = time_bound;
    private int score = 0 ;
    private final int upper_sz = 10;
    private final int img_sz = 10 ;
    private final int[] img_id =new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10} ;
    int now_sz = 2, pre_p2 = -1;
    void setScore(){
        final TextView tx_sroce = (TextView) findViewById(R.id.tx_score);
        tx_sroce.setText("Score : "+score);
    }
    void create_table(){
        final LinearLayout root = (LinearLayout) findViewById(R.id.linear);
        final LinearLayout mp = new LinearLayout(GameActivity.this);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mp.setLayoutParams(layout);
        mp.setOrientation(LinearLayout.VERTICAL);
        root.addView(mp);
        LinearLayout row[] = new LinearLayout[now_sz];
        LinearLayout.LayoutParams row_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,1.0f);
        LinearLayout.LayoutParams img_layout = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
        ImageView img[][] = new ImageView[now_sz][now_sz];
        int x,y;
        Random rand = new Random();
        x = rand.nextInt(now_sz);
        y = rand.nextInt(now_sz);
        while(x==y) y=rand.nextInt(now_sz);
        int p1,p2;
        p1 = rand.nextInt(img_sz);
        p2 = rand.nextInt(img_sz);
        while(p1==p2 || p2==pre_p2) p2=rand.nextInt(img_sz);
        pre_p2 = p2 ;
        for(int k=0;k<now_sz;k++){
            row[k] = new LinearLayout(GameActivity.this);
            row[k].setOrientation(LinearLayout.HORIZONTAL);
            row[k].setLayoutParams(row_layout);
            mp.addView(row[k]);
            for(int i=0;i<now_sz;i++){
                img[k][i] = new ImageView(GameActivity.this);
                img[k][i].setLayoutParams(img_layout);
                if(k==x&&i==y) img[k][i].setImageResource(img_id[p1]);
                else img[k][i].setImageResource(img_id[p2]);
                row[k].addView(img[k][i]);
            }
        }
        img[x][y].setClickable(true);
        img[x][y].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score ++ ;
                if(now_sz < upper_sz) now_sz ++ ;
                root.removeView(mp);
                setScore();
                create_table();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final TextView tx_time = (TextView) findViewById(R.id.tx_time);
        setScore();
        new CountDownTimer(time_bound*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tx_time.setText("Time : "+ remain_time);
                remain_time = remain_time -1 ;
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(GameActivity.this,ScoreActivity.class);
                intent.putExtra("score",score);
                startActivityForResult(intent,0);
            }
        }.start();
        create_table();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }
}
