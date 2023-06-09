package com.example.appdesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PopupActivity extends Activity {
    TextView txtText;
    NumberPicker numberPicker1,numberPicker2,numberPicker3;
    int currentValue1,currentValue2,currentValue3 = 00;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 제목지우기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);


        numberPicker1 = findViewById(R.id.numberPicker1);
        numberPicker1.setMinValue(00);
        numberPicker1.setMaxValue(23);
        numberPicker1.setValue(currentValue1);
        numberPicker1.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value); // 두 자리 숫자로 포맷 설정
            }
        });

        // 넘버 스크롤 picker 시간 단위
        numberPicker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker1.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int value = numberPicker1.getValue();

                    // 최댓값인 23일 때 스크롤을 올리면 0으로 설정
                    if (value == 23) {
                        numberPicker1.setValue(0);
                        currentValue1 = 0;
                    } else {
                        currentValue1 = value;
                    }
                }
            }
        });

        // 넘버 스크롤 picker 분 단위
        numberPicker2 = findViewById(R.id.numberPicker2);
        numberPicker2.setMinValue(00);
        numberPicker2.setMaxValue(59);
        numberPicker2.setValue(currentValue2);
        numberPicker2.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        numberPicker2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_UP){
                    int value = numberPicker1.getValue();
                    if(value==59){
                        numberPicker2.setValue(0);
                        currentValue2=00;
                    }else{
                        currentValue2 = value;
                    }
                }
                return false;
            }
        });

        // 넘버 스크롤 picker 초 단위
        numberPicker3 = findViewById(R.id.numberPicker3);
        numberPicker3.setMinValue(00);
        numberPicker3.setMaxValue(59);
        numberPicker3.setValue(currentValue1);
        numberPicker3.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        numberPicker3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        numberPicker3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_UP){
                    int value = numberPicker3.getValue();
                    if(value==59){
                        numberPicker3.setValue(0);
                        currentValue3=00;
                    }else{
                        currentValue3 = value;
                    }
                }
                return false;
            }
        });

    }

    //확인버튼 종료
    public void mOnClose(View v){
        finish();
    }

    //바깥에 눌려도 안꺼짐 ㅇㅇ
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //뒤로가기 눌러도 안꺼짐 ㅇㅇ
    @Override
    public void onBackPressed() {
        return;
    }


}
