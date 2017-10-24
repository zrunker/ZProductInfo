package cc.ibooker.zproductinfo.bean;

/**
 * 品牌类
 * <p>
 * Created by 邹峰立 on 2017/10/24.
 */
public class Brand {
    private String brand_id;// 品牌id
    private String brand_image;// 品牌图片地址
    private String brand_url;// 品牌链接地址
    private String brand_name;// 品牌名称
    private String brand_initial;// 品牌大写字母分组
    private String brand_class;// 品牌分类id
    private String brand_pic;
    private String brand_sort;
    private String brand_recommend;
    private String store_id;
    private String brand_apply;
    private String class_id;
    private String show_type;// 品牌展示类型 0表示图片 1表示文字
    private String parent_id;// 上级id
    private String brand_describe;// 品牌描述
    private String brand_pic_url;// 品牌图片
    private String pinyin;// 保存品牌名称拼音
    private int brand_res;


    public Brand() {
        super();
    }

    public Brand(String brand_id, String brand_image, String brand_url, String brand_name, String brand_initial, String brand_class, String brand_pic, String brand_sort, String brand_recommend, String store_id, String brand_apply, String class_id, String show_type, String parent_id, String brand_describe, String brand_pic_url, String pinyin, int brand_res) {
        this.brand_id = brand_id;
        this.brand_image = brand_image;
        this.brand_url = brand_url;
        this.brand_name = brand_name;
        this.brand_initial = brand_initial;
        this.brand_class = brand_class;
        this.brand_pic = brand_pic;
        this.brand_sort = brand_sort;
        this.brand_recommend = brand_recommend;
        this.store_id = store_id;
        this.brand_apply = brand_apply;
        this.class_id = class_id;
        this.show_type = show_type;
        this.parent_id = parent_id;
        this.brand_describe = brand_describe;
        this.brand_pic_url = brand_pic_url;
        this.pinyin = pinyin;
        this.brand_res = brand_res;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(String brand_image) {
        this.brand_image = brand_image;
    }

    public String getBrand_url() {
        return brand_url;
    }

    public void setBrand_url(String brand_url) {
        this.brand_url = brand_url;
    }

    public String getBrand_pic_url() {
        return brand_pic_url;
    }

    public void setBrand_pic_url(String brand_pic_url) {
        this.brand_pic_url = brand_pic_url;
    }

    public String getBrand_describe() {
        return brand_describe;
    }

    public void setBrand_describe(String brand_describe) {
        this.brand_describe = brand_describe;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getBrand_apply() {
        return brand_apply;
    }

    public void setBrand_apply(String brand_apply) {
        this.brand_apply = brand_apply;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getBrand_recommend() {
        return brand_recommend;
    }

    public void setBrand_recommend(String brand_recommend) {
        this.brand_recommend = brand_recommend;
    }

    public String getBrand_sort() {
        return brand_sort;
    }

    public void setBrand_sort(String brand_sort) {
        this.brand_sort = brand_sort;
    }

    public String getBrand_pic() {
        return brand_pic;
    }

    public void setBrand_pic(String brand_pic) {
        this.brand_pic = brand_pic;
    }

    public String getBrand_class() {
        return brand_class;
    }

    public void setBrand_class(String brand_class) {
        this.brand_class = brand_class;
    }

    public String getBrand_initial() {
        return brand_initial;
    }

    public void setBrand_initial(String brand_initial) {
        this.brand_initial = brand_initial;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getBrand_res() {
        return brand_res;
    }

    public void setBrand_res(int brand_res) {
        this.brand_res = brand_res;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand_id='" + brand_id + '\'' +
                ", brand_image='" + brand_image + '\'' +
                ", brand_url='" + brand_url + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", brand_initial='" + brand_initial + '\'' +
                ", brand_class='" + brand_class + '\'' +
                ", brand_pic='" + brand_pic + '\'' +
                ", brand_sort='" + brand_sort + '\'' +
                ", brand_recommend='" + brand_recommend + '\'' +
                ", store_id='" + store_id + '\'' +
                ", brand_apply='" + brand_apply + '\'' +
                ", class_id='" + class_id + '\'' +
                ", show_type='" + show_type + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", brand_describe='" + brand_describe + '\'' +
                ", brand_pic_url='" + brand_pic_url + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", brand_res=" + brand_res +
                '}';
    }
}
