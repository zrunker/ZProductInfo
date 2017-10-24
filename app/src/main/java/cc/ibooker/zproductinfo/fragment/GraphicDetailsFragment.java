package cc.ibooker.zproductinfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cc.ibooker.zproductinfo.R;
import cc.ibooker.zproductinfo.bean.ViewBundle;
import cc.ibooker.zproductinfo.view.ChildAutoHeightViewPager;

/**
 * 商品详情-图文详情-当前全部是图片
 *
 * Created by 邹峰立 on 2017/2/22.
 */
public class GraphicDetailsFragment extends Fragment {
    private View view;
    private LayoutInflater mInflater;
    private LinearLayout mLinearLayout;
    private ChildAutoHeightViewPager viewPager;

    public static GraphicDetailsFragment newInstance(ViewBundle viewBundle) {
        Bundle args = new Bundle();
        args.putParcelable("viewBundle", viewBundle);
        GraphicDetailsFragment fragment = new GraphicDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewBundle viewBundle = getArguments().getParcelable("viewBundle");
        viewPager = viewBundle != null ? viewBundle.getViewPager() : null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_graphic_details, container, false);
            if (viewPager != null)
                viewPager.setObjectForPosition(view, 0);
        }
        initView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    // 初始化控件
    private void initView() {
        if (view != null) {
            if (mLinearLayout == null)
                mLinearLayout = view.findViewById(R.id.linearLayout);
            if (mInflater == null)
                mInflater = LayoutInflater.from(getContext());
        }
    }

    // 设置流式布局控件
    public void setLinearLayoutData(ArrayList<Integer> datas) {
        if (datas != null && datas.size() > 0 && mInflater != null) {
            mLinearLayout.removeAllViews();
            for (Integer value : datas) {
                ImageView iv = (ImageView) mInflater.inflate(R.layout.tag_imageview, mLinearLayout, false);
                iv.setAdjustViewBounds(true);
//                // 加载图片-一般为加载网络图片
//                if (!TextUtils.isEmpty(value)) {}
                Picasso.with(getActivity())
                        .load(value)
                        .into(iv);
                mLinearLayout.addView(iv);
            }
        }
    }
}
