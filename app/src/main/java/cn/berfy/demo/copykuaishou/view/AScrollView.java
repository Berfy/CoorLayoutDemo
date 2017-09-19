package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import cn.berfy.framework.utils.LogUtil;
import cn.berfy.framework.utils.ViewUtils;

public class AScrollView extends ScrollView {

    private String TAG = "AScrollView";
    private OnScrollListener mOnScrollListener;
    private boolean mIsIntercept = true, mIsUseTouch = true;
    private VelocityTracker mVelocityTracker;
    private int mMaxVelocity;
    private int mTop;
    private float mRawY;
    private int mIsUpScroll;//1向上滑 2 向下滑 0不动

    public AScrollView(Context context) {
        this(context, null, 0);
    }

    public AScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMaxVelocity = ViewConfiguration.get(getContext()).getMaximumFlingVelocity();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mTop = t;
        if (null != mOnScrollListener)
            mOnScrollListener.onScroll(t, t + getMeasuredHeight(), l - oldl, t - oldt);
        LogUtil.i(TAG, "onScrollChanged " + l + "," + t + "," + oldl + "," + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public int getdTop() {
        return mTop;
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
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.i(TAG, "onTouchEvent  " + super.onTouchEvent(ev) + "   " + ev.getAction());
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                mRawY = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                mIsUpScroll = 0;
                LogUtil.e(TAG, "速度" + mVelocityTracker.getYVelocity());
                if (ev.getY() - mRawY > 0 && mVelocityTracker.getYVelocity() > mMaxVelocity / 5) {//向下滑
                    LogUtil.e(TAG, "向下滑" + mVelocityTracker.getYVelocity());
                    mIsUpScroll = 2;
                } else if (ev.getY() - mRawY < 0 && mVelocityTracker.getYVelocity() < -mMaxVelocity / 5) {//向上滑
                    LogUtil.e(TAG, "向上滑" + mVelocityTracker.getYVelocity());
                    mIsUpScroll = 1;
                }
                mRawY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i(TAG, "判断方向  " + mTop + "   " + ViewUtils.dip2px(getContext(), 250));
                if (mIsUpScroll == 1) {
                    smoothScrollBy(0, ViewUtils.dip2px(getContext(), 500) - mTop);
                } else if (mIsUpScroll == 2) {
                    smoothScrollTo(0, 0);
                } else {
                    if (mTop > ViewUtils.dip2px(getContext(), 250)) {
                        smoothScrollBy(0, ViewUtils.dip2px(getContext(), 250));
                    } else {
                        smoothScrollTo(0, 0);
                    }
                }
                releaseVelocityTracker();
                break;
        }
        return false;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
//        requestDisallowInterceptTouchEvent(true);
    }

    //释放VelocityTracker
    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
