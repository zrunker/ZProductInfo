package cc.ibooker.zproductinfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cc.ibooker.zproductinfo.R;
import cc.ibooker.zproductinfo.adapter.ProductEvalInfoLvAdapter;
import cc.ibooker.zproductinfo.bean.ViewBundle;
import cc.ibooker.zproductinfo.bean.GoodsEvaluate;
import cc.ibooker.zproductinfo.view.ChildAutoHeightViewPager;

/**
 * 评价详情列表
 *
 * Created by 邹峰立 on 2017/2/10.
 */
public class ProductEvalInfoFragment extends Fragment {
    private View view;
    private View emptyEvalLayout;
    private ListView listView;
    private ProductEvalInfoLvAdapter adapter;
    private ChildAutoHeightViewPager viewPager;

    public static ProductEvalInfoFragment newInstance(ViewBundle viewBundle) {
        Bundle args = new Bundle();
        args.putParcelable("viewBundle", viewBundle);
        ProductEvalInfoFragment fragment = new ProductEvalInfoFragment();
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
            view = inflater.inflate(R.layout.fragment_product_evalinfo, container, false);
            if (viewPager != null)
                viewPager.setObjectForPosition(view, 2);
        }
        initView();
        return view;
    }

    // 初始化控件
    private void initView() {
        if (view != null) {
            if (listView == null)
                listView = view.findViewById(R.id.lv_evalinfo);
            if (emptyEvalLayout == null)
                emptyEvalLayout = view.findViewById(R.id.layout_eval_empty);
        }
    }

    // 自定义setAdapter对外公开方法
    public void setAdapter(GoodsEvaluate goods_evaluate) {
        if (goods_evaluate == null || goods_evaluate.getContent() == null || goods_evaluate.getContent().size() <= 0) {
            listView.setVisibility(View.GONE);
            emptyEvalLayout.setVisibility(View.VISIBLE);
        } else {
            emptyEvalLayout.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            // 刷新数据
            if (adapter == null) {
                adapter = new ProductEvalInfoLvAdapter(getActivity(), goods_evaluate.getContent());
                listView.setAdapter(adapter);
            } else {
                adapter.reflashData(goods_evaluate.getContent());
            }
        }
    }
}
