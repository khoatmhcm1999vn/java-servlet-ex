package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product", schema = "public", catalog = "testabc")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = AUTO)
    private int productId;

    @Basic
    @Column(name = "product_name")
    private String productName;

    @Basic
    @Column(name = "product_price")
    private Double productPrice;

    @Basic
    @Column(name = "product_discount")
    private Double productDiscount;

    @Basic
    @Column(name = "product_code")
    private String productCode;

    @Basic
    @Column(name = "product_note")
    private String productNote;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoriesEntity categoriesID;

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductDiscount() {
        return this.productDiscount;
    }

    public void setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNote() {
        return this.productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public CategoriesEntity getCategoriesID() {
        return this.categoriesID;
    }

    public void setCategoriesID(CategoriesEntity categoriesID) {
        this.categoriesID = categoriesID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (this.productId != that.productId) return false;
        if (!Objects.equals(this.productName, that.productName)) return false;
        if (!Objects.equals(this.productPrice, that.productPrice)) return false;
        if (!Objects.equals(this.productDiscount, that.productDiscount))
            return false;
        if (!Objects.equals(this.productCode, that.productCode)) return false;
        return Objects.equals(this.productNote, that.productNote);
    }

    @Override
    public int hashCode() {
        int result = this.productId;
        result = 31 * result + (this.productName != null ? this.productName.hashCode() : 0);
        result = 31 * result + (this.productPrice != null ? this.productPrice.hashCode() : 0);
        result = 31 * result + (this.productDiscount != null ? this.productDiscount.hashCode() : 0);
        result = 31 * result + (this.productCode != null ? this.productCode.hashCode() : 0);
        result = 31 * result + (this.productNote != null ? this.productNote.hashCode() : 0);
        return result;
    }

}
