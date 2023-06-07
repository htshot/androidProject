package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    TextView hour,day,month,year;
    View view1,view2,view3,view4;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4;
    boolean isimgbtn1,isimgbtn2,isimgbtn3,isimgbtn4 = true;


    //bluetooth 관련 변수
    private static final int REQUEST_ENABLE_BT = 1;
    public BluetoothAdapter mBluetoothAdapter;
    public Set<BluetoothDevice> mDevices;
    public boolean onBT = false;
    public ProgressDialog asyncDialog;
    private BluetoothSocket bSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private BluetoothDevice mRemoteDevice;
    private Button BTButton;

    private static final byte COMMAND1_BTN_ON = 0x21;
    private static final byte COMMAND1_BTN_OFF = 0x23;
    private static final byte COMMAND2_BTN_ON = 0x25;
    private static final byte COMMAND2_BTN_OFF = 0x27;
    private static final byte COMMAND3_BTN_ON = 0x29;
    private static final byte COMMAND3_BTN_OFF = 0x31;
    private static final byte COMMAND4_BTN_ON = 0x33;
    private static final byte COMMAND4_BTN_OFF = 0x35;

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

        //블루투스 연결 관련부
        BTButton = findViewById(R.id.connect);
        BTButton.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint({"SetTextI18n", "MissingPermission"})
            @Override
            public void onClick(View view) {
                if (!onBT) { //Connect
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter == null) { //장치가 블루투스를 지원하지 않는 경우.
                        Toast.makeText(getApplicationContext(), "Bluetooth 지원을 하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
                    } else { // 장치가 블루투스를 지원하는 경우.
                        if (!mBluetoothAdapter.isEnabled()) {
                            // 블루투스를 지원하지만 비활성 상태인 경우
                            // 블루투스를 활성 상태로 바꾸기 위해 사용자 동의 요청
                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                        } else {
                            // 블루투스를 지원하며 활성 상태인 경우
                            // 페어링된 기기 목록을 보여주고 연결할 장치를 선택.
                            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                            if (pairedDevices.size() > 0) {
                                // 페어링 된 장치가 있는 경우.
                                selectDevice();
                            } else {
                                // 페어링 된 장치가 없는 경우.
                                Toast.makeText(getApplicationContext(), "먼저 Bluetooth 설정에 들어가 페어링을 진행해 주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else { //DisConnect
                    try {
                        mInputStream.close();
                        mOutputStream.close();
                        bSocket.close();
                        setButtonEnabled(false);
                        onBT = false;
                        BTButton.setText("connect");
                    } catch (Exception ignored) {
                    }
                }
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
                    sendBTCommand(COMMAND1_BTN_ON);
                } else {
                    imgbtn1.setImageResource(R.drawable.imgbtn2);
                    isimgbtn1 = true;
                    sendBTCommand(COMMAND1_BTN_OFF);
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
    @SuppressLint("MissingPermission")
    private void selectDevice() {
        mDevices = mBluetoothAdapter.getBondedDevices();
        final int mPairedDeviceCount = mDevices.size();

        if (mPairedDeviceCount == 0) {
            //  페어링 된 장치가 없는 경우
            Toast.makeText(getApplicationContext(), "장치를 페어링 해주세요!", Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("블루투스 장치 선택");


        // 페어링 된 블루투스 장치의 이름 목록 작성
        List<String> listItems = new ArrayList<>();
        for (BluetoothDevice device : mDevices) {
            listItems.add(device.getName());
        }
        listItems.add("취소");    // 취소 항목 추가

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == mPairedDeviceCount) {
                    // 연결할 장치를 선택하지 않고 '취소'를 누른 경우
                    //finish();
                } else {
                    // 연결할 장치를 선택한 경우
                    // 선택한 장치와 연결을 시도함
                    connectToSelectedDevice(items[item].toString());
                }
            }
        });

        builder.setCancelable(false);    // 뒤로 가기 버튼 사용 금지
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void connectToSelectedDevice(final String selectedDeviceName) {
        mRemoteDevice = getDeviceFromBondedList(selectedDeviceName);

        //Progress Dialog
        asyncDialog = new ProgressDialog(MainActivity.this);
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.show();
        asyncDialog.setCancelable(false);

        Thread BTConnect = new Thread(new Runnable() {
            @SuppressLint("MissingPermission")
            public void run() {
                try {
                    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //HC-06 UUID
                    // 소켓 생성
                    bSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);

                    // RFCOMM 채널을 통한 연결
                    bSocket.connect();

                    // 데이터 송수신을 위한 스트림 열기
                    mOutputStream = bSocket.getOutputStream();
                    mInputStream  = bSocket.getInputStream();
                    runOnUiThread(new Runnable() {

                        @SuppressLint({"ShowToast", "SetTextI18n"})
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), selectedDeviceName + " 연결 완료", Toast.LENGTH_LONG).show();
                            BTButton.setText("disconnect");
                            setButtonEnabled(true);
                            asyncDialog.dismiss();
                            receiveDate();

                        }
                    });
                    onBT = true;
                } catch (Exception e) {
                    // 블루투스 연결 중 오류 발생
                    runOnUiThread(new Runnable() {
                        @SuppressLint({"ShowToast", "SetTextI18n"})
                        @Override
                        public void run() {
                            asyncDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "블루투스 연결 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        BTConnect.start();
    }

    private void setButtonEnabled(boolean enabled) {
//        btnOn.setEnabled(enabled);
//        btnOff.setEnabled(enabled);
        imgbtn1.setEnabled(enabled);
        imgbtn2.setEnabled(enabled);
    }
    @SuppressLint("MissingPermission")
    public BluetoothDevice getDeviceFromBondedList(String name) {
        for (BluetoothDevice device : mDevices) {
            if (name.equals(device.getName())) {
                return device;
            }
        }
        return null;
    }

    public void sendBTCommand(byte cmd) {
        try {
            mOutputStream.write(new byte[]{(byte) cmd});    // 프로토콜 전송
            mOutputStream.flush();
        } catch (Exception e) {
            // 문자열 전송 도중 오류가 발생한 경우.
            asyncDialog.setMessage("failed");
        }
    }

    public void receiveDate() {
        final Handler handler = new Handler();
        boolean _isRunning;
        // 화재 감지시 토스트 메시지 ..
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            int bytesAvailable = mInputStream.available();

                            char read = (char) mInputStream.read();
                            if (read == 'q') {
                                LayoutInflater layout2 = getLayoutInflater();
                                startService(new Intent(MainActivity.this, AlertSound.class));
//                                View v2 = layout2.inflate(null, null);
                                MainActivity.this.runOnUiThread(() -> {

                                    Toast.makeText(MainActivity.this, "화재가 감지되었습니다.", Toast.LENGTH_SHORT).show();
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("화재발생")
                                            .setMessage("119에 연결하시겠습니까?")
                                            .setPositiveButton("네", new DialogInterface.OnClickListener()
                                            {

                                                @RequiresApi(api = Build.VERSION_CODES.M)
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                                    stopService(new Intent(MainActivity.this, AlertSound.class));
                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01075942362"));
                                                    startActivity(intent);

                                                }
                                            })
                                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    stopService(new Intent(MainActivity.this, AlertSound.class));
                                                }
                                            }).show();
                                });
                            }
                            char a = (char) mInputStream.read();
                            if (a == 'z') {
                                stopService(new Intent(MainActivity.this, AlertSound.class));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        //데이터 수신 thread 시작
        receiveThread.start();
    }
}


