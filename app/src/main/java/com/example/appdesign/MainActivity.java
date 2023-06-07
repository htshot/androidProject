package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {
    TextView hour,day,month,year;
    View view1,view2,view3,view4;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4;
    boolean isimgbtn1,isimgbtn2,isimgbtn3,isimgbtn4 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 그래프 삽입시 layout 부분 imagebutton gridlayout부분에서 margintop 변경

        // !주의사항 (해당 파일에서 작업 할거면 상관 X)
        // drawable 부분 button_round.xml 파일 생성할 것 (element를 shape로 생성)
        // 생성했다면 Manifest 부분 14~16줄 meta-data 생성
        // values 부분 dimens.xml 생성할 것 (element를 resources로 생성)
        // manifest.xml 에서 theme을 @style/Theme.AppCompat.NoActionBar로 변경


        //Hour,day,month,year Text id
        hour = findViewById(R.id.hour);
        day  = findViewById(R.id.day);
        month= findViewById(R.id.month);
        year = findViewById(R.id.year);

        //view 1,2,3,4 id 밑에 hour ,day... 텍스트 밑 실선
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        //view의 높이 조절을 위한 params임  /values/dimens.xml 생성 후 코드 작성
        GridLayout.LayoutParams params1 = (GridLayout.LayoutParams) view1.getLayoutParams();
        GridLayout.LayoutParams params2 = (GridLayout.LayoutParams) view2.getLayoutParams();
        GridLayout.LayoutParams params3 = (GridLayout.LayoutParams) view3.getLayoutParams();
        GridLayout.LayoutParams params4 = (GridLayout.LayoutParams) view4.getLayoutParams();

        view1.setLayoutParams(params1);
        view2.setLayoutParams(params1);
        view3.setLayoutParams(params1);
        view4.setLayoutParams(params1);

        //view 첫 화면 초기 params 설정 (안해놨더니 처음에 아무것도 안나옴)
        params1.height = (int) getResources().getDimension(R.dimen.view_height_5dp);
        view1.setLayoutParams(params1);
        params2.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
        view2.setLayoutParams(params2);
        params3.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
        view3.setLayoutParams(params3);
        params4.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
        view4.setLayoutParams(params4);

        //hour 텍스트 클릭 이벤트
        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //텍스트 클릭 시 색 변경 #87CEEB<-- 이게 퍼런색
                hour.setTextColor(Color.parseColor("#87CEEB"));
                day.setTextColor(Color.parseColor("#000000"));
                month.setTextColor(Color.parseColor("#000000"));
                year.setTextColor(Color.parseColor("#000000"));

                //텍스트 클릭 시 view 퍼런색으로 변경
                view1.setBackgroundColor(Color.parseColor("#87CEEB"));
                view2.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view3.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view4.setBackgroundColor(Color.parseColor("#CCCCCC"));

                //텍스트 클릭 시 view params 변경
                params1.height = (int) getResources().getDimension(R.dimen.view_height_5dp);
                params1.topMargin = (int)getResources().getDimension(R.dimen.view_topon);
                view1.setLayoutParams(params1);
                params2.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params2.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view2.setLayoutParams(params2);
                params3.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params3.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view3.setLayoutParams(params3);
                params4.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params4.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view4.setLayoutParams(params4);
            }
        });

        // day 텍스트 클릭 이벤트
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour.setTextColor(Color.parseColor("#000000"));
                day.setTextColor(Color.parseColor("#87CEEB"));
                month.setTextColor(Color.parseColor("#000000"));
                year.setTextColor(Color.parseColor("#000000"));

                view1.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view2.setBackgroundColor(Color.parseColor("#87CEEB"));
                view3.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view4.setBackgroundColor(Color.parseColor("#CCCCCC"));

                params1.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params1.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view1.setLayoutParams(params1);
                params2.height = (int) getResources().getDimension(R.dimen.view_height_5dp);
                params2.topMargin = (int)getResources().getDimension(R.dimen.view_topon);
                view2.setLayoutParams(params2);
                params3.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params3.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view3.setLayoutParams(params3);
                params4.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params4.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view4.setLayoutParams(params4);
            }
        });

        // month 텍스트 클릭 이벤트
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour.setTextColor(Color.parseColor("#000000"));
                day.setTextColor(Color.parseColor("#000000"));
                month.setTextColor(Color.parseColor("#87CEEB"));
                year.setTextColor(Color.parseColor("#000000"));

                view1.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view2.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view3.setBackgroundColor(Color.parseColor("#87CEEB"));
                view4.setBackgroundColor(Color.parseColor("#CCCCCC"));

                params1.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params1.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view1.setLayoutParams(params1);
                params2.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params2.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view2.setLayoutParams(params2);
                params3.height = (int) getResources().getDimension(R.dimen.view_height_5dp);
                params3.topMargin = (int)getResources().getDimension(R.dimen.view_topon);
                view3.setLayoutParams(params3);
                params4.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params4.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view4.setLayoutParams(params4);
            }
        });

        // year 텍스트 클릭 이벤트
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour.setTextColor(Color.parseColor("#000000"));
                day.setTextColor(Color.parseColor("#000000"));
                month.setTextColor(Color.parseColor("#000000"));
                year.setTextColor(Color.parseColor("#87CEEB"));

                view1.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view2.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view3.setBackgroundColor(Color.parseColor("#CCCCCC"));
                view4.setBackgroundColor(Color.parseColor("#87CEEB"));

                params1.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params1.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view1.setLayoutParams(params1);
                params2.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params2.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view2.setLayoutParams(params2);
                params3.height = (int) getResources().getDimension(R.dimen.view_height_3dp);
                params3.topMargin = (int)getResources().getDimension(R.dimen.view_topnull);
                view3.setLayoutParams(params3);
                params4.height = (int) getResources().getDimension(R.dimen.view_height_5dp);
                params4.topMargin = (int)getResources().getDimension(R.dimen.view_topon);
                view4.setLayoutParams(params4);
            }
        });

        //이미지 버튼
        imgbtn1 = findViewById(R.id.imgbtn1);
        imgbtn2 = findViewById(R.id.imgbtn2);
        imgbtn3 = findViewById(R.id.imgbtn3);
        imgbtn4 = findViewById(R.id.imgbtn4);

        // 전원 버튼 클릭 이벤트
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // drawable.imgbutton = 파란색 ,
                // drawable.imgbtn2 = 검정색
                // 클릭시 계속 on off 변동
                if (isimgbtn1) {
                    imgbtn1.setImageResource(R.drawable.imgbutton);
                    isimgbtn1 = false;
                } else {
                    imgbtn1.setImageResource(R.drawable.imgbtn2);
                    isimgbtn1 = true;
                }
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isimgbtn2) {
                    imgbtn2.setImageResource(R.drawable.imgbutton);
                    isimgbtn2 = false;
                } else {
                    imgbtn2.setImageResource(R.drawable.imgbtn2);
                    isimgbtn2 = true;
                }
            }
        });

        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isimgbtn3) {
                    imgbtn3.setImageResource(R.drawable.imgbutton);
                    isimgbtn3 = false;
                } else {
                    imgbtn3.setImageResource(R.drawable.imgbtn2);
                    isimgbtn3 = true;
                }
            }
        });

        imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isimgbtn4) {
                    imgbtn4.setImageResource(R.drawable.imgbutton);
                    isimgbtn4 = false;
                } else {
                    imgbtn4.setImageResource(R.drawable.imgbtn2);
                    isimgbtn4 = true;
                }
            }
        });

        //제일 밑 버튼 제어
        Button allon = findViewById(R.id.allon);
        Button alloff = findViewById(R.id.alloff);
        Button connect = findViewById(R.id.connect);
        Button mainmenu = findViewById(R.id.mainmenu);

        //전체 전원 켜기 이벤트
        allon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtn1.setImageResource(R.drawable.imgbutton);
                imgbtn2.setImageResource(R.drawable.imgbutton);
                imgbtn3.setImageResource(R.drawable.imgbutton);
                imgbtn4.setImageResource(R.drawable.imgbutton);
                Log.d("MyActivity", "allon 버튼이 클릭되었습니다.");
            }
        });

        alloff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtn1.setImageResource(R.drawable.imgbtn2);
                imgbtn2.setImageResource(R.drawable.imgbtn2);
                imgbtn3.setImageResource(R.drawable.imgbtn2);
                imgbtn4.setImageResource(R.drawable.imgbtn2);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // connect 부분 코드 작성
            }
        });

        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mainmenu 부분 코드 작성
                startActivity(intent);
                finish();
            }
        });
    }
}