package cn.berfy.demo.copykuaishou.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import cn.berfy.framework.utils.ToastUtil;

/**
 * Created by Berfy on 2017/9/11.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;

    public ListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button = new Button(mContext);
        button.setText("测试" + position);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.getInstance().showToast("点击测试" + position);
            }
        });
        return button;
    }
}
