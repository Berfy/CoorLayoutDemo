package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class AScrollView extends ScrollView {

    private String TAG = "AScrollView";
    private OnScrollListener mOnScrollListener;
    private boolean mIsIntercept = true, mIsUseTouch = true;

    public AScrollView(Context context) {
        super(context);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    public AScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (null != mOnScrollListener)
            mOnScrollListener.onScroll(t, t + getMeasuredHeight(), l - oldl, t - oldt);
//        Log.i(TAG, "onScrollChanged " + l + "," + t + "," + oldl + "," + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setTAG(String tag) {
        TAG = tag;
    }

    public interface OnScrollListener {
        void onScroll(int top, int bottom, int dX, int dY);
    }

    public boolean isIntercept() {
        return mIsIntercept;
    }

    public void intercept(boolean isIntercept) {
        mIsIntercept = isIntercept;
    }

    public void useTouch(boolean isUseTouch) {
        mIsUseTouch = isUseTouch;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogUtil.i(TAG, "onInterceptTouchEvent   是否拦截" + mIsIntercept);
//        for (int i = 0; i < getChildCount(); i++) {
//            mIsIntercept = ((ViewGroup) getChildAt(i)).onInterceptTouchEvent(ev);
//        }
//        return mIsIntercept;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        LogUtil.i(TAG, "onTouchEvent  " + super.onTouchEvent(ev) + "   " + ev.getAction());
////        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
////            mIsIntercept = false;
////            invalidate();
////        }
//        for (int i = 0; i < getChildCount(); i++) {
//            mIsIntercept = ((ViewGroup) getChildAt(i)).onTouchEvent(ev);
//        }
//        return mIsIntercept;
//    }
}
