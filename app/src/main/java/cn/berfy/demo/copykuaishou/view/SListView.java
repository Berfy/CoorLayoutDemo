package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import cn.berfy.framework.utils.LogUtil;

/**
 * Created by Berfy on 2017/9/11.
 */
public class SListView extends ListView {

    private OnScrollListener mOnScrollListener;

    public SListView(Context context) {
        super(context);
    }

    public SListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (null != mOnScrollListener)
            mOnScrollListener.onScroll(listViewScorllY());
        LogUtil.i("fsfsf", "onScrollChanged " + l + "," + t + "," + oldl + "," + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        return super.onTouchEvent(ev);
    }

    private int listViewScorllY() {
        if (getChildCount() > 0) {
            View view = getChildAt(0);
            if (view == null) {
                return 0;
            }
            int firstVisiblePosition = getFirstVisiblePosition();
            int top = view.getTop();
            return -top + firstVisiblePosition * view.getHeight();
        }
        return 0;
    }

    public interface OnScrollListener {
        void onScroll(int y);
    }
}
