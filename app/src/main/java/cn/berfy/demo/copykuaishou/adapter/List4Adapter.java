package cn.berfy.demo.copykuaishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.berfy.demo.copykuaishou.R;
import cn.berfy.framework.utils.ToastUtil;

/**
 * Created by Berfy on 2017/9/11.
 */

public class List4Adapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public List4Adapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.adapter_button, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Holder) {
            Holder holder1 = (Holder) holder;
            holder1.button.setText("测试");
            holder1.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.getInstance().showToast("点击测试" + position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class Holder extends RecyclerView.ViewHolder {
        Button button;

        Holder(View v) {
            super(v);
            button = (Button) v.findViewById(R.id.btn);
        }
    }
}
