package cn.berfy.demo.copykuaishou;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.berfy.demo.copykuaishou.adapter.ListAdapter;
import cn.berfy.demo.copykuaishou.view.AScrollView;
import cn.berfy.demo.copykuaishou.view.CoorLayout;
import cn.berfy.demo.copykuaishou.view.ScrollListView;
import cn.berfy.framework.utils.ToastUtil;

/**
 * Created by Berfy on 2017/9/12.
 * 仿造协调者布局
 * 没能实现
 */
public class Demo1Activity extends BaseActivity {

    private final String TAG = "Demo1Activity";
    private LinearLayout mLlParent;//协调者视图
    private RelativeLayout mRlTop;//顶部布局
    private AScrollView mScrollView;//滑动视图
    private ScrollListView mListView;//列表

    private ListAdapter mAdapter;

    private int mRawY, mY;

    @Override
    protected int getLayoutId() {
        return R.layout.demo1;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void initView() {
        mLlParent = (CoorLayout) findViewById(R.id.layout_parent);
        mRlTop = (RelativeLayout) findViewById(R.id.layout_top);
        mRlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.getInstance().showToast("点击Top");
            }
        });
        mScrollView = (AScrollView) findViewById(R.id.layout_scroll);
        mListView = (ScrollListView) findViewById(R.id.listView);
        mListView.setAdapter(new ListAdapter(mContext));
//        mLlParent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i(TAG, "顶层滑动" + motionEvent.getY() + " top视图顶部位置" + mRlTop.getTop());
//
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        mRawY = (int) motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        mY = (int) motionEvent.getY();
//                        int moveY = mY - mRawY;
//                        mRlTop.setPadding(0, moveY, 0, 0);
//                        if (moveY > 200) {
//                            Log.i(TAG, "向下释放");
//                            mScrollView.intercept(true);
//                            return true;
//                        }
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                    case MotionEvent.ACTION_UP:
//
//                        break;
//                }
//                return true;
//            }
//        });
//        mParentScrollView.setTAG("mParentScrollView");
//        mParentScrollView.setOnScrollListener(new AScrollView.OnScrollListener() {
//            @Override
//            public void onScroll(int top, int bottom, int dX, int dY) {
//                Log.i(TAG, "顶层滑动" + top + "," + bottom + "   " + mRlTop.getHeight());
//                Log.i(TAG, "mRlTop  " + mRlTop.getMeasuredWidth() + "," + mRlTop.getMeasuredHeight());
//
//                mRlTop.setPadding(0, -top, 0, 0);
//                if (top > 200) {
//                    Log.i(TAG, "向下释放" + top + "," + bottom + "   " + mRlTop.getHeight());
//                    mParentScrollView.intercept(false);
//                    mScrollView.intercept(true);
//                }
//            }
//        });
        mScrollView.setTAG("mScrollView");
        mScrollView.setOnScrollListener(new AScrollView.OnScrollListener() {
            @Override
            public void onScroll(int top, int bottom, int dX, int dY) {
                Log.i(TAG, "列表滑动" + top + "," + bottom + "   " + mRlTop.getHeight());
            }
        });
    }

    @Override
    protected void onClickEvent(View view) {

    }
}
