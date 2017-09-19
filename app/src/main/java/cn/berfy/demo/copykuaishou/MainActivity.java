package cn.berfy.demo.copykuaishou;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zc-android on 2017/9/13.
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_demo1)
    Button mBtnDemo1;
    @Bind(R.id.btn_demo2)
    Button mBtnDemo2;
    @Bind(R.id.btn_demo3)
    Button mBtnDemo3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_demo1, R.id.btn_demo2, R.id.btn_demo3, R.id.btn_demo4})
    @Override
    protected void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_demo4:
                startActivity(new Intent(mContext, Demo4Activity.class));
                break;
        }
    }
}
