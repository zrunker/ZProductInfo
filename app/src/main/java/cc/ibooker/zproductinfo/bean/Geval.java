package cc.ibooker.zproductinfo.bean;

import java.util.ArrayList;

/**
 * 评价信息
 * Created by 邹峰立 on 2017/2/23.
 */
public class Geval {
    private String geval_id;// 评价对象id属性
    private String geval_goodsid;// 评价对象商品Id
    private String geval_content;// 评价内容
    private String geval_scores;// 评价分数
    private ArrayList<String> geval_image;// 评价图片链接，用,隔开的图片链接
    private String geval_content_next;// 追加评价内容
    private String geval_addtime;// 评价时间戳
    private String geval_frommemberid;// 评价人id
    private String geval_frommembername;// 评价人用户名
    private String createTime;// 评价创建时间
    private String userAvatarUrl;// 评价人头像地址
    private String geval_explain;// 评论回复
    private String format_reviews;

    public Geval() {
        super();
    }

    public Geval(String geval_id, String format_reviews, String geval_explain, String userAvatarUrl, String geval_frommembername, String createTime, String geval_frommemberid, String geval_addtime, String geval_content_next, ArrayList<String> geval_image, String geval_scores, String geval_content, String geval_goodsid) {
        this.geval_id = geval_id;
        this.format_reviews = format_reviews;
        this.geval_explain = geval_explain;
        this.userAvatarUrl = userAvatarUrl;
        this.geval_frommembername = geval_frommembername;
        this.createTime = createTime;
        this.geval_frommemberid = geval_frommemberid;
        this.geval_addtime = geval_addtime;
        this.geval_content_next = geval_content_next;
        this.geval_image = geval_image;
        this.geval_scores = geval_scores;
        this.geval_content = geval_content;
        this.geval_goodsid = geval_goodsid;
    }

    public String getGeval_id() {
        return geval_id;
    }

    public void setGeval_id(String geval_id) {
        this.geval_id = geval_id;
    }

    public String getFormat_reviews() {
        return format_reviews;
    }

    public void setFormat_reviews(String format_reviews) {
        this.format_reviews = format_reviews;
    }

    public String getGeval_explain() {
        return geval_explain;
    }

    public void setGeval_explain(String geval_explain) {
        this.geval_explain = geval_explain;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGeval_frommembername() {
        return geval_frommembername;
    }

    public void setGeval_frommembername(String geval_frommembername) {
        this.geval_frommembername = geval_frommembername;
    }

    public String getGeval_frommemberid() {
        return geval_frommemberid;
    }

    public void setGeval_frommemberid(String geval_frommemberid) {
        this.geval_frommemberid = geval_frommemberid;
    }

    public String getGeval_addtime() {
        return geval_addtime;
    }

    public void setGeval_addtime(String geval_addtime) {
        this.geval_addtime = geval_addtime;
    }

    public String getGeval_content_next() {
        return geval_content_next;
    }

    public void setGeval_content_next(String geval_content_next) {
        this.geval_content_next = geval_content_next;
    }

    public ArrayList<String> getGeval_image() {
        return geval_image;
    }

    public void setGeval_image(ArrayList<String> geval_image) {
        this.geval_image = geval_image;
    }

    public String getGeval_scores() {
        return geval_scores;
    }

    public void setGeval_scores(String geval_scores) {
        this.geval_scores = geval_scores;
    }

    public String getGeval_content() {
        return geval_content;
    }

    public void setGeval_content(String geval_content) {
        this.geval_content = geval_content;
    }

    public String getGeval_goodsid() {
        return geval_goodsid;
    }

    public void setGeval_goodsid(String geval_goodsid) {
        this.geval_goodsid = geval_goodsid;
    }

    @Override
    public String toString() {
        return "Geval{" +
                "geval_id='" + geval_id + '\'' +
                ", geval_goodsid='" + geval_goodsid + '\'' +
                ", geval_content='" + geval_content + '\'' +
                ", geval_scores='" + geval_scores + '\'' +
                ", geval_image=" + geval_image +
                ", geval_content_next='" + geval_content_next + '\'' +
                ", geval_addtime='" + geval_addtime + '\'' +
                ", geval_frommemberid='" + geval_frommemberid + '\'' +
                ", geval_frommembername='" + geval_frommembername + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userAvatarUrl='" + userAvatarUrl + '\'' +
                ", geval_explain='" + geval_explain + '\'' +
                ", format_reviews='" + format_reviews + '\'' +
                '}';
    }
}
