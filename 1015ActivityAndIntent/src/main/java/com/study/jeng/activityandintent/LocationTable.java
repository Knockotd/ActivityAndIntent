package com.study.jeng.activityandintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationTable extends SQLiteOpenHelper {

    public LocationTable(Context context) {
        //   (Context,  저장될파일명,        Cursor을직접만든경우지정, 버전)
        super(context, "database.sqlite3", null, 1);
    }

    //데이터베이스를 처음 사용하려고 할 때 한 번 호출되는 메소드
   @Override
    public void onCreate(SQLiteDatabase db) {
    String sql = "create table location_table("+
            "_id integer primary key autoincrement,"+
            "category text,"+
            "location text)";
    db.execSQL(sql);
    db.execSQL("insert into location_table(category, location) values('서울','강남구');");
       db.execSQL("insert into location_table(category, location) values('서울','양천구');");
       db.execSQL("insert into location_table(category, location) values('서울','종로구');");
       db.execSQL("insert into location_table(category, location) values('인천','계양구');");
       db.execSQL("insert into location_table(category, location) values('인천','남동구');");
       db.execSQL("insert into location_table(category, location) values('인천','서구');");
       db.execSQL("insert into location_table(category, location) values('경기도','부천시');");
       db.execSQL("insert into location_table(category, location) values('경기도','수원시');");
       db.execSQL("insert into location_table(category, location) values('경기도','성남시');");
       db.execSQL("insert into location_table(category, location) values('전라남도','목포시');");
       db.execSQL("insert into location_table(category, location) values('전라남도','여수시');");
       db.execSQL("insert into location_table(category, location) values('전라남도','순천시');");
       db.execSQL("insert into location_table(category, location) values('경상도','포항');");
       db.execSQL("insert into location_table(category, location) values('경상도','김천시');");
       db.execSQL("insert into location_table(category, location) values('경상도','경주시');");

    }
    //테이블을 삭제하고 다시 생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table location_table");
    onCreate(db);

    }
}
