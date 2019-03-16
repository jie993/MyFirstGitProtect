package com.example.administrator.myfirstgitprotect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myfirstgitprotect.Utils.LogUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    private TextView tvfirst;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LogUtils.d("这是我自定义的日志");

    }

    private void initView() {
        tvfirst = (TextView) findViewById(R.id.tvfirst);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                call_phone("10086");
                break;
        }
    }

    public void call_phone(String phone) {

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale( this,Manifest.permission.CALL_PHONE)) {

                Toast.makeText(this, "请授权！", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CALL_PHONE},1);
               /* Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);*/
            }else{
                Toast.makeText(this, "请手动授权！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CALL_PHONE},1);
            }
        }else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        }


    }
}
