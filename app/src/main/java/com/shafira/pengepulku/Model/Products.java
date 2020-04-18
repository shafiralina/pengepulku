package com.shafira.pengepulku.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Products {
    private String productname;
    private String price;
    private String desc;
    private String picture;


    public Products() {
    }

    public Products(String productname, String price, String desc, String picture) {
        this.productname = productname;
        this.price = price;
        this.desc = desc;
        this.picture = picture;


    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
