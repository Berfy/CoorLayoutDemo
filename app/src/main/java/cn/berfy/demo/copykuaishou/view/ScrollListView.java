package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Berfy on 2017/9/11.
 */
public class ScrollListView extends ListView {

    private OnScrollListener mOnScrollListener;

    public ScrollListView(Context context) {
        super(context);
    }

    public ScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (null != mOnScrollListener)
            mOnScrollListener.onScroll(t, t + getMeasuredHeight(), l - oldl, t - oldt);
//        Log.i(TAG, "onScrollChanged " + l + "," + t + "," + oldl + "," + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public interface OnScrollListener {
        void onScroll(int top, int bottom, int dX, int dY);
    }
}
