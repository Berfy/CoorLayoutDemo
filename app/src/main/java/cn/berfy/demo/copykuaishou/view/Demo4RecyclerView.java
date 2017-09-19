package cn.berfy.demo.copykuaishou.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.berfy.framework.utils.LogUtil;

/**
 * 创建日期: 17/9/14 下午9:55
 * 作者:Berfy
 */
public class Demo4RecyclerView extends RecyclerView {

    private static final String TAG = "Demo4RecyclerView";

    private int mRootViewHeight;

    public Demo4RecyclerView(Context context) {
        this(context, null);
    }

    public Demo4RecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo4RecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTopViewHeight(int topHeight) {
        mRootViewHeight = topHeight;
    }

    /**
     * listview到顶部
     *
     * @return
     */
    public boolean isTop() {
        return !canScrollVertically(-1);//到顶部
    }

    /**
     * listview到底部
     *
     * @return
     */
    public boolean isBottom() {
        return !canScrollVertically(1);//到底部
    }

    float down = 0;
    float y;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down = event.getRawY();
                LogUtil.e(TAG, "按下");
//                ToastUtil.showShort("自己");
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                y = event.getRawY();
                LogUtil.e(TAG, "移动 y=" + event.getRawY());
                if (isTop()) {
                    LogUtil.e(TAG, "到顶部");
                    if (y - down > 1) {
                        LogUtil.e(TAG, "向下滑动  父类");
//                        到顶端,向下滑动 把事件教给父类
//                        ToastUtil.showShort("向下滑动 父类");
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        LogUtil.e(TAG, "向上滑动 自己");
                        //到顶端,向上滑动 把事件拦截 由自己处理
//                        ToastUtil.showShort("自己");
                        getParent().requestDisallowInterceptTouchEvent(true);
                        if (down >= mRootViewHeight && y - down < 0) {//向上
                            LogUtil.e(TAG, "向上 y=" + y + "  位置" + mRootViewHeight);
                            if (y <= mRootViewHeight) {
//                                ToastUtil.showShort("向上滑动 父类");
                                getParent().requestDisallowInterceptTouchEvent(false);
                                return false;
                            }
                        }
                    }
                } else {
                    if (down >= mRootViewHeight && y - down < 0) {//向上
                        LogUtil.e(TAG, "向上 y=" + y + "  位置" + mRootViewHeight);
                        if (y <= mRootViewHeight) {
//                            ToastUtil.showShort("向上滑动 父类");
                            getParent().requestDisallowInterceptTouchEvent(false);
                            return false;
                        }
                    }

                    if (isBottom()) {
                        LogUtil.e(TAG, "到底部");
                        if (y - down > 1) {
                            LogUtil.e(TAG, "向下滑动");
//                        到底端,向下滑动 把事件拦截 由自己处理
                            getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
//                            LogUtil.e(TAG, "向上滑动");
////                        到底端,向上滑动 把事件教给父类
//                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        LogUtil.e(TAG, "onInterceptTouchEvent 拦截" + super.onInterceptTouchEvent(e));
        return super.onInterceptTouchEvent(e);
    }

    //    private int listViewTop() {
//        if (getChildCount() > 0) {
//            View view = getChildAt(0);
//            if (view == null) {
//                return 0;
//            }
//            int firstVisiblePosition = getFirstVisiblePosition();
//            int top = view.getTop();
//            return top;
//        }
//        return 0;
//    }

//    private int listViewScorllY() {
//        if (getChildCount() > 0) {
//            View view = getChildAt(0);
//            if (view == null) {
//                return 0;
//            }
//            int firstVisiblePosition = getFirstVisiblePosition();
//            int top = view.getTop();
//            return -top + firstVisiblePosition * view.getHeight();
//        }
//        return 0;
//    }

    private int listViewBottom() {
        if (getChildCount() > 0) {
            View view = getChildAt(getChildCount() - 1);
            if (view == null) {
                return 0;
            }
            int bottom = view.getBottom();
            return bottom;
        }
        return 0;
    }
}