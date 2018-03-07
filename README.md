# ZProductInfo
Android商品详情页实现，包括顶部滚动半透明渐变效果实现，流式布局的实现，顶部悬浮(ScrollView包裹ViewPager,ViewPager包裹Fragment,Fragment高度自适应)等相关功能。

>作者：邹峰立，微博：zrunker，邮箱：zrunker@yahoo.com，微信公众号：书客创作，个人平台：[www.ibooker.cc](http://www.ibooker.cc)。

>本文选自[书客创作](http://www.ibooker.cc)平台第68篇文章。[阅读原文](http://www.ibooker.cc/article/68/detail) 。

![书客创作](http://upload-images.jianshu.io/upload_images/3480018-1a0e4b208281c055..jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

现今的市场说O2O模式很火一点都不假，例如电商，各行各业都在做电商。而做电商平台必不可少的是商品详情页，那么如何实现一个功能比较全的商品详情页呢？

首先看一看商品详情页效果图：

![商品详情页效果图](http://upload-images.jianshu.io/upload_images/3480018-6cb309ba13d3ace9..gif?imageMogr2/auto-orient/strip)

从效果图上可以看去本商品详情页需要实现重难点，主要包括以下七个方面：
1、商品图轮播。
2、顶部栏透明度变化。
3、顶部悬浮。
4、流式布局，如标签。
5、底部子控件高度自适应。
6、底部内容区返回顶部。
7、底部布局左右滑动切换。

### 一、布局

本案例中整体采用ScrollView进行上下滚动的实现，在ScrollView中包裹着两个ViewPager，顶部的ViewPager用来实现商品图轮播。底部的ViewPager展示图文详情、产品实拍、评论详情三部分数据，这三部分的数据采用Fragment来显示。

![布局样式结构图](http://upload-images.jianshu.io/upload_images/3480018-94bd9f8513d2e390..jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 二、功能

**1、商品图轮播。**

商品轮播采用[ZViewPager库](https://github.com/zrunker/ZViewPager/releases)中提供的GeneralVpLayout来实现。

首先通过Android Studio中引入ZViewPager：
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    compile 'com.github.zrunker:ZViewPager:v1.0.4'
}
```
接下来可以将商品图轮播单独定义一个布局文件来显示，最后通过include引入主布局文件。但是需要在布局文件中引入GeneralVpLayout控件。
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="400dp"
    android:background="#FFFFFF"
    android:orientation="vertical">

    <!-- 商品详情页，头部商品详情轮播 -->
    <cc.ibooker.zviewpagerlib.GeneralVpLayout
        android:id="@+id/generalVpLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!--点游标-->
    <LinearLayout
        android:id="@+id/viewGroup"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="19dp"
        android:gravity="center"
        android:orientation="horizontal" />

</RelativeLayout>
```
最后实现，这里定义三个变量。
```
private GeneralVpLayout<Integer> generalVpLayout;
private LinearLayout mtopVGroup;
private ImageView[] mImageViews;
```
可以将generalVpLayout的实现封装成一个方法，调用的时候可以直接进行引用。
```
/**
 * 设置商品详情轮播
 *
 * @param data 数据源
 */
private void setProductTopViewPager(ArrayList<Integer> data) {
    if (data != null && data.size() > 0) {
        generalVpLayout.setVisibility(View.VISIBLE);
        // 初始化指示器
        // 对imageViews进行填充
        if (mImageViews == null)
            mImageViews = new ImageView[data.size()];
        mtopVGroup.removeAllViews();
        // 小图标
         for (int k = 0; k < data.size(); k++) {
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dotParams.setMargins(0, 0, 20, 0);
            dotParams.gravity = Gravity.CENTER_VERTICAL;
            ImageView mImageView = new ImageView(MainActivity.this);
            mImageView.setLayoutParams(dotParams);
            mImageViews[k] = mImageView;
            if (k == 0) {
                mImageViews[k].setBackgroundResource(R.mipmap.icon_focusdot);
            } else {
                mImageViews[k].setBackgroundResource(R.mipmap.icon_defaultdot);
            }
            mtopVGroup.addView(mImageViews[k]);
        }
        // 初始化generalVpLayout
        generalVpLayout.init(new HolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, data)
            // 设置轮播停顿时间
            .setDuration(5000)
            // 设置指示器是否可见
            .setPointViewVisible(false)
            // 开启轮播
            .start();
        // ViewPager状态改变监听
        generalVpLayout.setOnViewPagerChangeListener(new GeneralVpLayout.OnViewPagerChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 修改指示器
                for (int i = 0; i < mImageViews.length; i++) {
                    mImageViews[position].setBackgroundResource(R.mipmap.icon_focusdot);
                    if (position != i) {
                        mImageViews[i].setBackgroundResource(R.mipmap.icon_defaultdot);
                    }
                }
            }
        });
    } else {
        generalVpLayout.setVisibility(View.GONE);
    }
}
```
```
// 自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
private class ImageViewHolder implements Holder<Integer> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        // 创建数据
        imageView = new ImageView(MainActivity.this);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer imgPath) {
//            // 加载数据-一般为加载网址
//            if (TextUtils.isEmpty(imgPath)) {
//                imageView.setVisibility(View.GONE);
//            } else {
//                imageView.setVisibility(View.VISIBLE);
//            }
        imageView.setVisibility(View.VISIBLE);
        Picasso.with(MainActivity.this)
            .load(imgPath)
            .into(imageView);
    }
}
```
**2、顶部栏透明度变化。**

顶部栏透明度变化，是随着滚动的距离来实现的，所以要对ScrollView的滚动事件进行监听。在这里是通过继承ScrollView，对外添加滚动事件接口的方式来实现。
```
/**
 * 自定义ScrollView-对外提供滚动监听
 *
 * Created by 邹峰立 on 15/8/22.
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScrollchanged(l, t, oldl, oldt);
        }
    }

    /**
     * 由垂直方向滚动条代表的所有垂直范围，缺省的范围是当前视图的画图高度。
     */
    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    // 定义滚动监听接口
    public interface OnScrollListener {
        /**
         * @param t 当前View滑动的距离
         */
        void onScrollchanged(int l, int t, int oldl, int oldt);

        void onTouchUp();

        void onTouchDown();
    }

    public OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
```
顶部栏toolbarLayout，要想实现toolbarLayout的透明度的变化要利用setAlpha方法，该方法参数取值范围0到229。本案例中顶部栏在ScrollView滚动0到190范围内进行变化。

```
myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
    @Override
    public void onScrollchanged(int l, int scrollY, int oldl, int oldt) {
        // 顶部栏透明度控制
        if (scrollY >= 0 && scrollY < 190) {
            toolbarLayout.getBackground().mutate().setAlpha(scrollY / 3);
            brandFlowLayout.setVisibility(View.VISIBLE);
            pdescTv.setVisibility(View.GONE);
        } else if (scrollY >= 190) {
            toolbarLayout.getBackground().mutate().setAlpha(229);
            brandFlowLayout.setVisibility(View.GONE);
            pdescTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTouchUp() {}

    @Override
    public void onTouchDown() {}
});
```
其中brandFlowLayout和pdescTv为顶部栏控件，注意顶部栏的左右两侧图片要为透明线条图，否则当顶部栏变透明时，图片将会保留自身背景显示。

**3、顶部悬浮。**

顶部悬浮实在悬浮【图文详情、产品实拍、评论详情】三部分标题栏，这里其实采用FrameLayout帧布局，悬浮栏会显示在最顶层的最顶部。只要通过动态设置悬浮栏到最顶部的距离就能实现悬浮栏随着整体滚动位置变化效果。最后通过设置悬浮栏到顶部的最小距离来固定在顶部，从而实现顶部悬浮的效果。这里需要借助于ScrollView的滚动事件监听。

这里还有一个地方，需要一些小技巧来屏蔽。需要设置如悬浮栏等高的控件进行占位，因为效果图中悬浮栏一直在底部布局的顶部，所以需要一个控件在底部布局的顶部进行占位处理，而悬浮栏就是覆盖在底部布局的顶部。

这里需要在ScrollView滚动监听onScrollchanged方法中添加以下代码：
```
vpagerTopDistance = bottomVPager.getTop() - classifyHeight - toolbarLayout.getHeight();

// 设置浮动栏
int translation = Math.max(scrollY, vpagerTopDistance);
classifyLayout.setTranslationY(translation);
classifyLayout.setVisibility(View.VISIBLE);
```
其中classifyHeight为浮动栏的高度，classifyLayout为浮动栏布局控件，bottomVPager为顶部布局ViewPager。因为在浮动栏的顶部还有顶部状态栏所以要处理顶部状态栏的高度。

**4、流式布局，如标签。**

流式布局主要用来显示标签或者布局相似的控件，这里通过自定义FlowLayout控件来实现。使用起来也非常简单，如显示商品规格。
首先在布局文件中引入FlowLayout：
```
<cc.ibooker.zproductinfo.view.FlowLayout
    android:id="@+id/flowlayout_specifications_choice"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal"
    android:paddingBottom="8dp"
    android:paddingEnd="8dp"
    android:paddingLeft="8dp"
    android:paddingRight="8dp"
    android:paddingStart="8dp" />
```
代码动态添加规格，这里封装成setSpecificationsChoiceFlowlayoutData方法：
```
/**
 * 设置流式布局控件-选择规格
 *
 * @param datas 数据源
 */
private void setSpecificationsChoiceFlowlayoutData(final ArrayList<Specification> datas) {
    if (datas != null && datas.size() > 0) {
        LayoutInflater mInflater = LayoutInflater.from(this);
        specificationsChoiceFlowlayout.removeAllViews();
        for (Specification value : datas) {
            final TextView tv = (TextView) mInflater.inflate(R.layout.tag_gray_circular_textview, specificationsChoiceFlowlayout, false);
            tv.setTag(value.getId());
            tv.setText(value.getText());
            // 规格点击事件监听
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 防止连续点击
                    if (ClickUtil.isFastClick()) {
                        return;
                    }
                    int tvTag = (int) v.getTag();
                    for (int i = 0; i < datas.size(); i++) {
                    Specification specification = datas.get(i);
                    if (specification.getId() == tvTag) {
                        tv.setBackgroundResource(R.drawable.bg_red_circular);
                        tv.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTitle));
                        // 刷新界面-发送广播/EventBus
                    } else {
                        TextView tagView = specificationsChoiceFlowlayout.findViewWithTag(specification.getId());
                        tagView.setBackgroundResource(R.drawable.bg_gray_circular);
                        tagView.setTextColor(MainActivity.this.getResources().getColor(R.color.colorSomber));
                    }
                }
            }
        });
        specificationsChoiceFlowlayout.addView(tv);
        }
        specificationsChoiceFlowlayout.setVisibility(View.VISIBLE);
    } else {
        specificationsChoiceFlowlayout.setVisibility(View.GONE);
    }
}
```
**5、底部子控件高度自适应。**

因为顶部要显示的是三个模块的内容，每个内容都不一样，所以底部子控件的高度也是不一样的。但是如果使用android自带的ViewPager，底部所显示的高度不会随着子控件的高度变化而变化，要想实现ViewPager的高度自适应，这里通过继承ViewPager实现动态改变子控件高度来达到自适应的效果。
```
/**
 * 可重置/刷新子View高度的viewPager
 * <p>
 * Created by 邹峰立 on 2017/7/1.
 */
public class ChildAutoHeightViewPager extends ViewPager {
    private int current;// 标记当前显示第几个页面
    private int height = 0;// 保存子控件测量值
    /**
     * 保存position与对于的View
     */
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<>();
    // 标记是否能够滚动
    private boolean scrollble = true;

    public ChildAutoHeightViewPager(Context context) {
        super(context);
    }

    public ChildAutoHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 测量当前控件高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > current) {
            View child = mChildrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 重置当前控件高度
    public void resetHeight(int current) {
        this.current = current;
        if (mChildrenViews.size() > current) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    /**
     * 保存position与对于的View
     */
    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !scrollble || super.onTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}
```
在每一次在ViewPager添加子控件的时候，调用setObjectForPosition方法实现ViewPager对子控件标记。当每一次切换子控件的时候调用resetHeight方法，实现子控件高度重置。

**6、底部内容区返回顶部。**

当点击返回顶部按钮，实现返回顶部功能。返回顶部按钮只有当悬浮栏固定在顶部的时候才会实现。所以需要在ScrollView滚动监听onScrollchanged方法中添加如下代码：
```
// 设置返回顶部
if (scrollY >= vpagerTopDistance) {
    backTopIv.setVisibility(View.VISIBLE);
} else {
    backTopIv.setVisibility(View.GONE);
}
```
当点击backTopIv时候，实现返回顶部功能。
```
 myScrollView.smoothScrollTo(0, vpagerTopDistance);
```
**7、底部布局左右滑动切换。**

当底部ViewPager进行左右滑动切换之后，要实现悬浮栏相应控件变化。这一个功能需要对ViewPager的页面改变事件进行监听处理，这里只给成关键性代码。

```
bottomVPager.addOnPageChangeListener(new BottomPageChangeListener());
/**
 * 内部类实现底部ViewPager设置变化监听
 */
private class BottomPageChangeListener implements ViewPager.OnPageChangeListener {

    @Override
    public void onPageSelected(int arg0) {
        // 滑动结束
        changeTextView(arg0);
    }

    @Override
    public void onPageScrolled(int positon, float positonOffset, int positonOffsetPx) {
        // 滑动过程
        if (mCurrentIndex == 0 && positon == 0) {// 0-->1
            params.leftMargin = (int) (mCurrentIndex * bmpw + positonOffset
                    * bmpw)
                    + fixLeftMargin;
        } else if (mCurrentIndex == 1 && positon == 0) {// 1-->1
            params.leftMargin = (int) (mCurrentIndex * bmpw + (positonOffset - 1)
                * bmpw)
                + fixLeftMargin;
        } else if (mCurrentIndex == 1 && positon == 1) {// 1-->2
            params.leftMargin = (int) (mCurrentIndex * bmpw + positonOffset
                * bmpw)
                + fixLeftMargin;
        } else if (mCurrentIndex == 2 && positon == 1) {// 2-->1
                params.leftMargin = (int) (mCurrentIndex * bmpw + (positonOffset - 1)
                    * bmpw)
                    + fixLeftMargin;
        }
        cursor.setLayoutParams(params);
        /**
         * 重置当前高度
        */
        bottomVPager.resetHeight(positon);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
    }
}
```
其中游标cursor为悬浮布局底部线条布局控件，另外注意在初始化的时候要定义好游标的初始位置。

[Github地址](https://github.com/zrunker/ZProductInfo)
[阅读原文](http://www.ibooker.cc/article/68/detail) 

----------
![微信公众号：书客创作](http://upload-images.jianshu.io/upload_images/3480018-24b914bba9700865..jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
