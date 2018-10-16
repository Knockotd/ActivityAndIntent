package com.study.jeng.a1016systemskillactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button callBook;
    Button camera;
    Button soundCatch;
    Button map;
    Button browser;
    Button call;

    TextView dataOutput;
    ImageView cameraBringImg;

    //이미지 출력 크기를 위한 변수
    int reqWidth;
    int reqHeight;

    View.OnClickListener buttonEvent = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.callBook :
                    //연락처 앱 실행
                    Intent callBookIntent = new Intent(Intent.ACTION_PICK);
                    callBookIntent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    //실행
                    startActivityForResult(callBookIntent,10);
                    break;
                case R.id.camera :
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,20);
                    break;
                case R.id.soundCatch :
                    Intent soundCatchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    soundCatchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    soundCatchIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"음성인식 테스트");
                    startActivityForResult(soundCatchIntent, 30);
                    break;
                case R.id.map :
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.530823,126.7216"));
                    //맵은 리턴하는 게 없다
                    startActivity(mapIntent);
                    break;
                case R.id.browser :
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
                    //이것도 리턴 받는 게 없다.
                    startActivity(browserIntent);
                    break;
                case R.id.call :
                    //전화기능은 퍼미션을 확인 후 수행해야한다.
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-0000-0000"));
                        startActivity(callIntent);
                    }else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},100);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    callBook = (Button)findViewById(R.id.callBook);
    camera = (Button)findViewById(R.id.camera);
    soundCatch = (Button)findViewById(R.id.soundCatch);
    map = (Button)findViewById(R.id.map);
    browser = (Button)findViewById(R.id.browser);
    call = (Button)findViewById(R.id.call);
    dataOutput = (TextView)findViewById(R.id.dataOutput);
    cameraBringImg = (ImageView)findViewById(R.id.cameraBringImg);

    reqWidth = getResources().getDimensionPixelOffset(R.dimen.request_image_width);
    reqHeight = getResources().getDimensionPixelOffset(R.dimen.request_image_height);

    //버튼에 이벤트 핸들러를 연결
    callBook.setOnClickListener(buttonEvent);
    camera.setOnClickListener(buttonEvent);
    soundCatch.setOnClickListener(buttonEvent);
    map.setOnClickListener(buttonEvent);
    browser.setOnClickListener(buttonEvent);
    call.setOnClickListener(buttonEvent);

    }
    //startActivityForResult로 인텐트를 실행한 후 인턴트의 액티비티가 종료되었을 때 호출되는 메소드
    //requestCode는 호출할 때 설정한 코드(호출한 액티비니 구분)이고 resultCode 출력된 액티비티에서 전달해준 코드(응답 구분)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && requestCode == RESULT_OK){
          String result = data.getDataString();
          dataOutput.setText(result);
        }else if(requestCode == 20 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            cameraBringImg.setImageBitmap(bitmap);
        }
    }
}
