package com.webmvc.chicken.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_product", schema = "public", catalog = "testabc")
public class ViewProductEntity {

    @Basic
    @Column(name = "product_discount")
    private Double productDiscount;

    @Basic
    @Column(name = "product_price")
    private Double productPrice;

    @Basic
    @Column(name = "product_note")
    private String productNote;

    @Basic
    @Column(name = "product_name")
    private String productName;

    @Basic
    @Column(name = "product_code")
    private String productCode;

    @Basic
    @Column(name = "category_code")
    private String categoryCode;

    @Basic
    @Id
    @Column(name = "product_id")
    private Integer productId;

    public Double getProductDiscount() {
        return Objects.requireNonNullElse(this.productDiscount, 0.0);
    }

    public void setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNote() {
        return this.productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewProductEntity that = (ViewProductEntity) o;

        if (!Objects.equals(this.productDiscount, that.productDiscount))
            return false;
        if (!Objects.equals(this.productPrice, that.productPrice)) return false;
        if (!Objects.equals(this.productNote, that.productNote)) return false;
        if (!Objects.equals(this.productName, that.productName)) return false;
        if (!Objects.equals(this.productCode, that.productCode)) return false;
        if (!Objects.equals(this.categoryCode, that.categoryCode)) return false;
        return Objects.equals(this.productId, that.productId);
    }

    @Override
    public int hashCode() {
        int result = this.productDiscount != null ? this.productDiscount.hashCode() : 0;
        result = 31 * result + (this.productPrice != null ? this.productPrice.hashCode() : 0);
        result = 31 * result + (this.productNote != null ? this.productNote.hashCode() : 0);
        result = 31 * result + (this.productName != null ? this.productName.hashCode() : 0);
        result = 31 * result + (this.productCode != null ? this.productCode.hashCode() : 0);
        result = 31 * result + (this.categoryCode != null ? this.categoryCode.hashCode() : 0);
        result = 31 * result + (this.productId != null ? this.productId.hashCode() : 0);
        return result;
    }

}
