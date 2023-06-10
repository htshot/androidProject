package com.example.appdesign;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;



public class Reservation extends AppCompatActivity {
    private UserDao mUserDao;
    TextView multi_name,all_name,all_watch,tab1_name,tab1_watch,tab2_name,tab2_watch
            ,tab3_name,tab3_watch,tab4_name,tab4_watch;
    ImageButton back_arrow,edit_name,edit_alltab,tab1_edit,tab2_edit,tab3_edit,tab4_edit;
    EditText multi_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);


        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "app_db")
                .fallbackToDestructiveMigration()       // 스키마 변경 가능
                .allowMainThreadQueries()               // main thread에서 db에 io가능
                .build();

        mUserDao = database.userDao(); // 인터페이스 객체 할당


        User user = new User();
        List<User> userList = mUserDao.getUserAll();
        if (userList.size() == 0){

            user.setFirstName("NAME 124");
            mUserDao.setInsertUser(user);
        }


        // 뒤로가기 버튼
        back_arrow = findViewById(R.id.back_arrow);
        Intent intent = new Intent(Reservation.this,MainActivity2.class);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });

        edit_name = findViewById(R.id.edit_name);
        multi_name = findViewById(R.id.mult_name);
        multi_edit = findViewById(R.id.multi_edit);


        for (int i = 0; i< userList.size(); i++){
            multi_name.setText(userList.get(i).getFirstName() + "");
        }



        // edit_name => 이름바꾸기 아이콘 누를시 textview => edittext로 변경
        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multi_name.setText("");
                if (multi_name.getVisibility() == View.VISIBLE) {
                    multi_name.setVisibility(View.GONE);
                    multi_edit.setVisibility(View.VISIBLE);
                    multi_edit.setText(multi_name.getText());
                } else {
                    multi_name.setVisibility(View.VISIBLE);
                    multi_edit.setVisibility(View.GONE);
                    multi_name.setText(multi_edit.getText());
                }

                user.setFirstName(multi_name.getText() + "");
                mUserDao.setInsertUser(user);

            }
        });

        // PopupActivity로 인텐트 넘기기 팝업창
        Intent intent1 = new Intent(Reservation.this,PopupActivity.class);

        edit_alltab = findViewById(R.id.edit_alltab);
        edit_alltab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
            }
        });

        tab1_edit = findViewById(R.id.tab1_edit);
        tab1_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        tab2_edit = findViewById(R.id.tab2_edit);
        tab2_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        tab3_edit = findViewById(R.id.tab3_edit);
        tab3_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        tab4_edit = findViewById(R.id.tab4_edit);
        tab4_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });



    }
}
