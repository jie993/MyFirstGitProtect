package com.example.administrator.myfirstgitprotect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PathPermission;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myfirstgitprotect.Utils.LogUtils;
import com.example.administrator.myfirstgitprotect.Utils.SharedPreUtils;
import com.example.administrator.myfirstgitprotect.Utils.VersionUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(VersionUtils.isM()){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS,Manifest.permission.CAMERA},100);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mhandler.sendEmptyMessageDelayed(1,2000);
                }else{
                    mhandler.sendEmptyMessageDelayed(1,2000);
                }
                return;
        }
    }
    private boolean isFirstRunApp() {
        boolean isFirst = SharedPreUtils.getBoolean(this, "isFirst", false);
        //第一次运行  为false
        if (isFirst) {
            return true;
        } else {
            SharedPreUtils.putBoolean(this, "isFirst", true);
            return false;
        }
    }

    Handler mhandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    if(isFirstRunApp()){
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    }else{
                        startActivity(new Intent(MainActivity.this,Main3Activity.class));
                    }
            }
        }
    };

}
