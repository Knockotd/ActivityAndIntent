package com.study.jeng.activityandintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class SubActivityPracticeSub extends AppCompatActivity {
    TextView subLabel;
    Button moveMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_practice_sub);
        subLabel = (TextView)findViewById(R.id.subLabel);
        moveMain = (Button)findViewById(R.id.moveMain);
        //메인에서 전달해준 데이터 읽기
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("data1");
        HashMap<String, Object> map = (HashMap<String, Object>)intent.getSerializableExtra("data2");
        subLabel.setText(data1);
        subLabel.setText(map.toString());
        Log.e("SubActivity","onCeate 진행");

        moveMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("SubActivity","onClick 호출");
                Intent intent = new Intent();
                intent.putExtra("subdata",
                        "하위 Activity에서 넘겨주는 데이터");
                //응답 코드 생성 한 후 데이터 전달
                setResult(100,intent);
                Log.e("SubActivity","onClick 진행");
                finish();
            }
        });
        Log.e("SubActivity","onCreate 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SubActivity","onResume 호출");
    }

}
