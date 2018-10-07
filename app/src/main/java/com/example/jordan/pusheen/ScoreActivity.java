package com.example.jordan.pusheen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    String name;
    int score;
    boolean send = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences settings = getSharedPreferences("name",MODE_PRIVATE);
        final SharedPreferences.Editor editor= settings.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        TextView tx_score  = (TextView) findViewById(R.id.score);
        final EditText ed_tx = (EditText) findViewById(R.id.name);
        name = settings.getString("name","");
        ed_tx.setText(name);
        tx_score.setText(score+"");
        Button btn_end = (Button) findViewById(R.id.restart);
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btn_rank = (Button) findViewById(R.id.submit);
        btn_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ed_tx.getText())){
                    Toast.makeText(ScoreActivity.this,"Name can't be empty.",Toast.LENGTH_SHORT).show();
                }
                else {
                    name = ed_tx.getText().toString();
                    editor.putString("name",name);
                    editor.commit();
                    Intent intent = new Intent(ScoreActivity.this, RankActivity.class);
                    Rank rank = new Rank(name, score+"");
                    if(!send) {
                        rank.addNewPost();
                        send = true;
                    }
                    startActivity(intent);
                }
            }
        });
    }
}
