package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

import cn.berfy.framework.utils.DeviceUtil;
import cn.berfy.framework.utils.LogUtil;
import cn.berfy.framework.utils.ViewUtils;

/**
 * Created by Berfy on 2017/9/12.
 */

public class CoorLayout extends LinearLayout {

    private Context mContext;
    private String TAG = "CoorLayout";
    private List<ViewGroup> mChildViews = new ArrayList<>();
    private int mRawY, mY;
    private float mSpeedY;
    private long mTime;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mHeight, mTopHieght;
    private int mMaxVelocity;

    public CoorLayout(Context context) {
        this(context, null, 0);
    }

    public CoorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScroller = new Scroller(context);
        mMaxVelocity = ViewConfiguration.get(mContext).getMaximumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //暂时写死
        mChildViews.add((ViewGroup) getChildAt(0));
        mChildViews.add((ViewGroup) getChildAt(1));
        mHeight = getViewTrueHeight(this);
        mTopHieght = getViewTrueHeight(mChildViews.get(0)) - ViewUtils.dip2px(mContext, 50);
        LogUtil.i(TAG, "View真实高度 " + mTopHieght + "   " + mHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (null != mScroller && mScroller.computeScrollOffset()) {
            int y = mScroller.getCurrY();
            LogUtil.i(TAG, "computeScroll 位置 " + y + "  " + y + " getPaddingTop" + getPaddingTop() + "  "
                    + (-getPaddingTop() + ViewUtils.getScreenHeight(mContext)));
            if (y < 0) {
                y = 0;
            }
            if (y > mTopHieght - DeviceUtil.getStatusBarHeight(mContext)) {
                y = mTopHieght - DeviceUtil.getStatusBarHeight(mContext);
            }
//            if (y + ViewUtils.getScreenHeight(mContext) > mHeight) {
//                y = mHeight - ViewUtils.getScreenHeight(mContext);
//            }
            setPadding(0, -y, 0, 0);
            postInvalidate();

        }
    }

    /**
     * 开始滑动
     */
    public void startMove(int dx, int dy, int time) {
//        if (mScroller.getFinalY() + dy < 0) {
//            dy = -mScroller.getFinalY();
//        }
//        if (mScroller.getFinalY() + dy > mTopHieght + DeviceUtil.getStatusBarHeight(mContext)) {
//            dy = mTopHieght + DeviceUtil.getStatusBarHeight(mContext) - mScroller.getFinalY();
//        }
        LogUtil.i(TAG, "startMove " + dy);
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, time);
        invalidate();
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean isIntercept = false;
//        for (int i = 0; i < mChildViews.size(); i++) {
//            mChildViews.get(i).onInterceptTouchEvent(ev);
//        }
//        LogUtil.i(TAG, "onInterceptTouchEvent   是否拦截 " + isIntercept);
//        return false;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        LogUtil.i(TAG, "onTouchEvent");
//        boolean isIntercept = false;
//        for (int i = 0; i < mChildViews.size(); i++) {
//            isIntercept = mChildViews.get(i).onTouchEvent(event);
//        }
//        if (null == mVelocityTracker) {
//            mVelocityTracker = VelocityTracker.obtain();
//        }
//        mVelocityTracker.addMovement(event);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mRawY = (int) event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mY = (int) event.getY();
//                mTime = System.currentTimeMillis();
//                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
////                move(mY - mRawY);
//                LogUtil.i(TAG, "移动距离   " + (mY - mRawY));
//                startMove(0, -(mY - mRawY), 0);
//                mRawY = mY;
//                mChildViews.get(1).onInterceptTouchEvent(event);
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                LogUtil.i(TAG, " 速度" + mVelocityTracker.getYVelocity());
//                mSpeedY = (mVelocityTracker.getYVelocity() / mMaxVelocity);
//                startMove(0, (int) (-mHeight * mSpeedY), (int) (1500 * (1 - mSpeedY)));
//                releaseVelocityTracker();
//                ((AScrollView) mChildViews.get(1)).intercept(true);
//                break;
//        }
//        return true;
//    }

    //释放VelocityTracker
    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param view
     * @return
     */
    private int getViewTrueHeight(ViewGroup view) {
        int h = 0;
        // 获取scrollview实际高度
        for (int i = 0; i < view.getChildCount(); i++) {
            if (view.getChildAt(i) instanceof ViewGroup) {
                ViewGroup child = (ViewGroup) view.getChildAt(i);
                if (child.getChildCount() > 0) {
                    h += getViewTrueHeight(child);
                } else {
                    h += child.getHeight();
                }
            } else {
                h += view.getChildAt(i).getHeight();
            }
        }
        return h;
    }

    protected void setLister() {

    }
}
