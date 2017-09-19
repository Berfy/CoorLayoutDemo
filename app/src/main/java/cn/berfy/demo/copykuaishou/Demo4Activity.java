package cn.berfy.demo.copykuaishou;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import cn.berfy.demo.copykuaishou.adapter.List4Adapter;
import cn.berfy.demo.copykuaishou.view.AScrollView;
import cn.berfy.demo.copykuaishou.view.Demo4RecyclerView;
import cn.berfy.framework.utils.ToastUtil;
import cn.berfy.framework.utils.ViewUtils;

/**
 * Created by Berfy on 2017/9/12.
 * 仿造协调者布局
 */
public class Demo4Activity extends BaseActivity {

    private final String TAG = "Demo1Activity";
    @Bind(R.id.scrollView)
    AScrollView mScrollView;
    @Bind(R.id.layout_parent)
    LinearLayout mLlParent;//协调者视图
    @Bind(R.id.layout_bottom)
    LinearLayout mLlBottom;//协调者视图
    @Bind(R.id.layout_top)
    RelativeLayout mRlTop;//顶部布局
    @Bind(R.id.listView)
    Demo4RecyclerView mListView;//列表

    private int mRawY, mY;

    @Override
    protected int getLayoutId() {
        return R.layout.demo4;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void initView() {
        mRlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.getInstance().showToast("点击Top");
            }
        });
        mScrollView.setOnScrollListener(new AScrollView.OnScrollListener() {
            @Override
            public void onScroll(int top, int bottom, int dX, int dY) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mListView.getLayoutParams();
                layoutParams.height = ViewUtils.getScreenHeight(mContext) - ViewUtils.getStatusBarHeight(mContext) - ViewUtils.dip2px(mContext, 500) + top;
                mListView.setLayoutParams(layoutParams);
            }
        });
        int bottomHeight = ViewUtils.getScreenHeight(mContext) - ViewUtils.getStatusBarHeight(mContext) - ViewUtils.dip2px(mContext, 500);
        mListView.setHasFixedSize(true);
        mListView.setNestedScrollingEnabled(false);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));
        mListView.setAdapter(new List4Adapter(mContext));
        int bottomMaxHeight = bottomHeight + ViewUtils.dip2px(mContext, 450);//ScrollView长度预留50dp
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mListView.getLayoutParams();
        layoutParams.height = bottomHeight;
        mListView.setLayoutParams(layoutParams);
        mListView.setTopViewHeight(ViewUtils.dip2px(mContext, 500) + ViewUtils.getStatusBarHeight(mContext));
        LinearLayout.LayoutParams layoutParamsBottom = (LinearLayout.LayoutParams) mLlBottom.getLayoutParams();
        layoutParamsBottom.height = bottomMaxHeight;
        mLlBottom.setLayoutParams(layoutParamsBottom);
    }

    @Override
    protected void onClickEvent(View view) {

    }
}
