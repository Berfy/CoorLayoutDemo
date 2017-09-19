package cn.berfy.demo.copykuaishou;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Berfy on 2017/9/12.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getParent()) {
            mContext = getParent();
        } else {
            mContext = this;
        }
        View view = getLayoutView();
        if (null == view) {
            view = View.inflate(mContext, getLayoutId(), null);
        }
        setContentView(view);
        ButterKnife.bind(this);
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract View getLayoutView();

    protected abstract void initView();

    protected abstract void onClickEvent(View view);

    @Override
    public void onClick(View view) {
        onClickEvent(view);
    }

    protected void close() {
        finish();
    }
}
