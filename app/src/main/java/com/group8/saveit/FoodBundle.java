package com.group8.saveit;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//Implementing Parcelable so Restaurant activity can pass foodbundle array as serializable extra to ordersummary activity
public class FoodBundle implements Parcelable {
    private long id;
    private String bundleName;
    private int bundleImage;
    private double price;
    private String[] items;

    private boolean checked=false;

    public FoodBundle(long id,String bundleName, int bundleImage, double price) {
        this.id=id;
        this.bundleName = bundleName;
        this.bundleImage = bundleImage;
        this.price = price;
    }

    protected FoodBundle(Parcel in) {
        id = in.readLong();
        bundleName = in.readString();
        bundleImage = in.readInt();
        price = in.readDouble();
        items = in.createStringArray();
        checked = in.readByte() != 0;
    }

    public static final Creator<FoodBundle> CREATOR = new Creator<FoodBundle>() {
        @Override
        public FoodBundle createFromParcel(Parcel in) {
            return new FoodBundle(in);
        }

        @Override
        public FoodBundle[] newArray(int size) {
            return new FoodBundle[size];
        }
    };

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public int getBundleImage() {
        return bundleImage;
    }

    public void setBundleImage(int bundleImage) {
        this.bundleImage = bundleImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(bundleName);
        dest.writeInt(bundleImage);
        dest.writeDouble(price);
        dest.writeStringArray(items);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}