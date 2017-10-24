package cc.ibooker.zproductinfo.bean;

import java.util.HashMap;

/**
 * 商品评价信息
 * Created by 邹峰立 on 2017/2/23.
 */
public class GoodsEvaluate {
    private String normal_percent;
    private String bad_percent;
    private int star_average;// 星星的数量
    private HashMap<String, Geval> content;// 评价内容条数

    public GoodsEvaluate() {
        super();
    }

    public GoodsEvaluate(HashMap<String, Geval> content, int star_average, String bad_percent, String normal_percent) {
        this.content = content;
        this.star_average = star_average;
        this.bad_percent = bad_percent;
        this.normal_percent = normal_percent;
    }

    public String getNormal_percent() {
        return normal_percent;
    }

    public void setNormal_percent(String normal_percent) {
        this.normal_percent = normal_percent;
    }

    public HashMap<String, Geval> getContent() {
        return content;
    }

    public void setContent(HashMap<String, Geval> content) {
        this.content = content;
    }

    public int getStar_average() {
        return star_average;
    }

    public void setStar_average(int star_average) {
        this.star_average = star_average;
    }

    public String getBad_percent() {
        return bad_percent;
    }

    public void setBad_percent(String bad_percent) {
        this.bad_percent = bad_percent;
    }

    @Override
    public String toString() {
        return "GoodsEvaluate{" +
                "normal_percent='" + normal_percent + '\'' +
                ", bad_percent='" + bad_percent + '\'' +
                ", star_average=" + star_average +
                ", content=" + content +
                '}';
    }
}
