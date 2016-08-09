package com.qianfeng.useful;

import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * 常用的小知识点
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉头标题,必须在setContentView之前调用
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);视版本而定
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //获取屏幕的大小
        int width = this.getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int height = getResources().getDisplayMetrics().heightPixels;
        Log.e("aa", width + "------" + height);

        //设置控件的横竖屏
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        //全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**
         * 横竖屏锁定android机器有方向感应器，所以屏幕会进行自动横屏/竖屏切换。
         * 解决的方法:在manifest中的activity节点内加入
         android:screenOrientation="portrait"  // 竖屏
         android:screenOrientation="landscape" // 横屏
         */

        //对于WIFI的管理
        initWifi();

        //获得当天的0时0分0秒的Calender
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        //Activity页面的切换动画,在res目录下新建anim目录
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);


    }


    //对于WIFI的管理
    private void initWifi() {
        //清单文件添加权限

        //获得WIFI管理
        WifiManager wifi = (WifiManager) this.getSystemService(Service.WIFI_SERVICE);

        //WIFI管理
        wifi.setWifiEnabled(true);//true打开 false关闭

        //获得状态,状态信息为0,1,2,3,4分别代表不同状态,具体查看SDK
        int state = wifi.getWifiState();
        Log.e("aa", "-----state----" + state);

    }
}
