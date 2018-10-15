package com.study.jeng.activityandintent;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class SubActivityPracticeMain extends AppCompatActivity {
    TextView mainLabel;
    Button moveSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_practice_main);
        mainLabel = (TextView)findViewById(R.id.mainLabel);
        moveSub = (Button)findViewById(R.id.moveSub);

        moveSub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //출력할 액티비티의 인텐트 만들기
                //* * * 하위 액티비티 인스턴스를 직접 생성하지 않습니다. - 액티비티를 이용해서는 데이터를 전달할 수 없습니다.
                //IOC:제어의역전 - 내가 액티비티를 만들었으나 객체는 내가 생성하지 않는것 (실수할 확률이 적다)
                Intent intent = new Intent(SubActivityPracticeMain.this, SubActivityPracticeSub.class);
                //액티비티 출력
                intent.putExtra("data1","메인에서 넘겨준 데이터");
                //데이터를 넘겨주기 전에 데이터를 저장을 해야 한다. (안그러면 데이터가 넘어가고 설정한거라서 서브에서는 null이나 예외가 뜸)
                //데이터 여러개 줄 때는 이렇게 묶어서 준다.
                HashMap<String, Object> map = new HashMap<>();
                //new map<>()으로 만들면 안되는데 왜냐면, 인터페이스라서 implements를 할 수 없어서이다.
                map.put("이름", "홍길동");
                map.put("나이", 87);
                //안드로이드에서만 쓰는 데이터는 안드로이드는 utf-8을 쓰기 때문에 이름이 한글을 써도 된다.
                intent.putExtra("data2", map);
                //startActivity(intent);
                //하위 액티비티에서 데이터를 리턴받을 수 있는 메소드를 호출해서 하위 액티비티 출력
                startActivityForResult(intent,100);

            }
        });
        Log.e("MainActivity","onCreate 호출");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","onResume 호출");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==100 && resultCode == 100){
            String subdata = data.getStringExtra("subdata");
            mainLabel.setText(subdata);
        }
    }
}
