package cc.ibooker.zproductinfo.bean;

/**
 * 商品规格
 * Created by 邹峰立 on 2017/2/23.
 */
public class Specification {
    private int id;// 规格ID
    private String text;// 规格名称
    private String goods_image_url_60x60;// 规格图片

    public Specification() {
        super();
    }

    public Specification(int id, String text, String goods_image_url_60x60) {
        this.id = id;
        this.text = text;
        this.goods_image_url_60x60 = goods_image_url_60x60;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGoods_image_url_60x60() {
        return goods_image_url_60x60;
    }

    public void setGoods_image_url_60x60(String goods_image_url_60x60) {
        this.goods_image_url_60x60 = goods_image_url_60x60;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", goods_image_url_60x60='" + goods_image_url_60x60 + '\'' +
                '}';
    }
}
