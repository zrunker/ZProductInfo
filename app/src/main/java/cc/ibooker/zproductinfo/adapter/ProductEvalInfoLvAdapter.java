package cc.ibooker.zproductinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cc.ibooker.zproductinfo.R;
import cc.ibooker.zproductinfo.bean.Geval;

/**
 * 评价列表Adapter
 * Created by 邹峰立 on 2017/2/23.
 */
public class ProductEvalInfoLvAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Geval> datas = new ArrayList<>();
    private LayoutInflater inflater;

    public ProductEvalInfoLvAdapter(Context context, HashMap<String, Geval> map) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        if (map != null) {
            for (String key : map.keySet()) {
                datas.add(map.get(key));
            }
        }
    }

    // 刷新数据
    public void reflashData(HashMap<String, Geval> map) {
        for (String key : map.keySet()) {
            datas.add(map.get(key));
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_product_evalinfo_lv_item, parent, false);
            viewHolder.picImg = convertView.findViewById(R.id.iv_pic);
            viewHolder.nameTv = convertView.findViewById(R.id.tv_nickname);
            viewHolder.evalTv = convertView.findViewById(R.id.tv_eval);
            viewHolder.evalTimeTv = convertView.findViewById(R.id.tv_eval_time);
            viewHolder.replyTv = convertView.findViewById(R.id.tv_reply);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Geval geval = datas.get(position);
//        // 一般加载网络图片
//        String picPath = geval.getUserAvatarUrl();
//        if (!TextUtils.isEmpty(picPath)) {}
        viewHolder.nameTv.setText(geval.getGeval_frommembername());
        viewHolder.evalTv.setText(geval.getGeval_content());
        viewHolder.evalTimeTv.setText(geval.getCreateTime());
        if (!TextUtils.isEmpty(geval.getGeval_explain()) && !"null".equals(geval.getGeval_explain())) {
            viewHolder.replyTv.setText(geval.getGeval_explain());
            viewHolder.replyTv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.replyTv.setVisibility(View.GONE);
        }
        viewHolder.elseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView picImg;
        TextView nameTv, evalTv, evalTimeTv, replyTv, elseTv;
    }
}
