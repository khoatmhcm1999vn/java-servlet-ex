package com.webmvc.chicken.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartEntity implements Serializable {

    private List<LineItemEntity> items;

    private String discountCode;

    private Double discountValue;

    public CartEntity() {
        this.items = new ArrayList<>();
    }

    public List<LineItemEntity> getItems() {
        return this.items;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Double getDiscountValue() {
        return Objects.requireNonNullElse(this.discountValue, 0.0);
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public LineItemEntity getItemByProductCode(String productCode) {
        for (LineItemEntity lineItemEntity : this.items) {
            if (lineItemEntity.getProduct().getProductCode().equalsIgnoreCase(productCode)) {
                return lineItemEntity;
            }
        }
        return null;
    }

    public void addItem(LineItemEntity item) {
        String code = item.getProduct().getProductCode();
        int quantity = item.getQuantity();
        for (LineItemEntity lineItemEntity : this.items) {
            if (lineItemEntity.getProduct().getProductCode().equalsIgnoreCase(code)) {
                lineItemEntity.setQuantity(quantity);
                return;
            }
        }
        this.items.add(item);
    }

    public void removeItem(LineItemEntity item) {
        String code = item.getProduct().getProductCode();
        for (int i = 0; i < this.items.size(); i++) {
            LineItemEntity lineItemEntity = this.items.get(i);
            if (lineItemEntity.getProduct().getProductCode().equalsIgnoreCase(code)) {
                this.items.remove(i);
                return;
            }
        }
    }

}
