package cn.berfy.demo.copykuaishou;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import cn.berfy.demo.copykuaishou.adapter.ListAdapter;
import cn.berfy.demo.copykuaishou.view.Coor2Layout;
import cn.berfy.demo.copykuaishou.view.SListView;
import cn.berfy.framework.utils.ToastUtil;

/**
 * Created by Berfy on 2017/9/12.
 * 仿造协调者布局
 */
public class Demo2Activity extends BaseActivity {

    private final String TAG = "Demo1Activity";
    private Coor2Layout mLlParent;//协调者视图
    private RelativeLayout mRlTop;//顶部布局
    private SListView mListView;//列表

    private ListAdapter mAdapter;

    private int mRawY, mY;

    @Override
    protected int getLayoutId() {
        return R.layout.demo2;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void initView() {
        mLlParent = (Coor2Layout) findViewById(R.id.layout_parent);
        mRlTop = (RelativeLayout) findViewById(R.id.layout_top);
        mRlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.getInstance().showToast("点击Top");
            }
        });
        mRlTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        mListView = (SListView) findViewById(R.id.listView);
        mListView.setAdapter(new ListAdapter(mContext));
        mListView.setOnScrollListener(new SListView.OnScrollListener() {
            @Override
            public void onScroll(int y) {
                Log.i(TAG, "列表滑动" + y);
                mLlParent.startMove(0, y, 0);
            }
        });
    }

    @Override
    protected void onClickEvent(View view) {

    }
}
