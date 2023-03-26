package com.group8.saveit;

public class FoodBundle {
    private String bundleName;
    private int bundleImage;
    private double price;
    private String[] items;

    public FoodBundle(String bundleName, int bundleImage, double price) {
        this.bundleName = bundleName;
        this.bundleImage = bundleImage;
        this.price = price;
    }

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
}
