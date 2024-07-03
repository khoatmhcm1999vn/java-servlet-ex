package com.webmvc.chicken.model;

import com.webmvc.chicken.utils.MyUtil;

import java.io.Serializable;

public class LineItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public String getTotalCurrencyFormat() {
        return MyUtil.format(this.getTotal());
    }

    public String getPriceFormat() {
        return MyUtil.format(this.product.getProductPrice());
    }

    public Double getTotal() {
        return (this.product.getProductPrice() - this.product.getProductDiscount()) * this.quantity;
    }

}
