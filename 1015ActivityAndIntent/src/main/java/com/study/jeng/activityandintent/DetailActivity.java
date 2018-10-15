package com.study.jeng.activityandintent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button back = (Button)findViewById(R.id.back);
        TextView title = (TextView)findViewById(R.id.title);
        ListView listView = (ListView)findViewById(R.id.subList);
        Log.e("Detail","변수선언");
        //앞에서 넘겨준 데이터 가져오기
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        title.setText(category);
        Log.e("Detail","category : " +category);
        //category에 해당하는 location 찾아오기
        LocationTable locationTable = new LocationTable(this);
        SQLiteDatabase db = locationTable.getReadableDatabase();
        Cursor cursor = db.rawQuery("select location from location_table where category=?", new String[]{category});
        Log.e("Detail","cursor : "+cursor.toString());
        //ArrayList에 읽은 데이터 추가
        ArrayList<String> list = new ArrayList<>();
        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        Log.e("Detail","반복문 나옴");
        //ListView에 데이터 출력
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        Log.e("Detail","데이터 출력");

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)view;
                String location = textView.getText().toString();
                Toast.makeText(DetailActivity.this,location,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
