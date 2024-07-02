package com.webmvc.chicken.model;

import java.io.Serializable;

public class LineItemEntity implements Serializable {

    private ViewProductEntity product;

    private int quantity;

    public LineItemEntity() {

    }

    public ViewProductEntity getProduct() {
        return this.product;
    }

    public void setProduct(ViewProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
