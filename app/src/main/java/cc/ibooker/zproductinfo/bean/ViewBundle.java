package cc.ibooker.zproductinfo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cc.ibooker.zproductinfo.view.ChildAutoHeightViewPager;

/**
 * View的Bundle
 * Created by 邹峰立 on 2017/10/24.
 */
public class ViewBundle implements Parcelable {
    ChildAutoHeightViewPager viewPager;

    public ViewBundle() {
        super();
    }

    public ViewBundle(ChildAutoHeightViewPager viewPager) {
        this.viewPager = viewPager;
    }

    protected ViewBundle(Parcel in) {
    }

    public static final Creator<ViewBundle> CREATOR = new Creator<ViewBundle>() {
        @Override
        public ViewBundle createFromParcel(Parcel in) {
            return new ViewBundle(in);
        }

        @Override
        public ViewBundle[] newArray(int size) {
            return new ViewBundle[size];
        }
    };

    public ChildAutoHeightViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ChildAutoHeightViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
