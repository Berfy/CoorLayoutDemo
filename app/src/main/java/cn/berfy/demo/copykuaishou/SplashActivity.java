package cn.berfy.demo.copykuaishou;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import cn.berfy.framework.utils.LogUtil;
import cn.berfy.framework.utils.ToastUtil;

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void initView() {
        LogUtil.setTag("Berfy");
        ToastUtil.init(getApplicationContext());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Demo2Activity.class));
                close();
            }
        }, 500);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onClickEvent(View view) {

    }
}
