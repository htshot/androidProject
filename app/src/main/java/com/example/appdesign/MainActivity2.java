package com.example.appdesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Array;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        //멀티탭 선택하기
        Spinner spinner = findViewById(R.id.spinner);
        if (spinner != null) {
            String[] str = getResources().getStringArray(R.array.spinnerArray);
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this, R.layout.spinner_item, str);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        Log.v("알림", spinner.getSelectedItem().toString() + " is selected");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        // on,off페이지로 이동
        Button btn1 = findViewById(R.id.btn1);
        Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
                finish();
            }
        });

        // 예약으로 이동
        Button btn3 = findViewById(R.id.btn3);
        Intent intent3 = new Intent(MainActivity2.this,Reservation.class);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
                finish();
            }
        });
    }
}