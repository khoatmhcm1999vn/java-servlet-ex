package com.webmvc.chicken.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "discount", schema = "public", catalog = "testabc")
public class DiscountEntity {

    @Basic
    @Column(name = "begin_discount")
    private Date beginDiscount;

    @Basic
    @Column(name = "end_discount")
    private Date endDiscount;

    @Basic
    @Column(name = "discount_value")
    private Double discountValue;

    @Basic
    @Column(name = "discount_name")
    private String discountName;

    @Basic
    @Column(name = "discount_code")
    private String discountCode;

    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = AUTO)
    private int discountId;

    public Date getBeginDiscount() {
        return this.beginDiscount;
    }

    public void setBeginDiscount(Date beginDiscount) {
        this.beginDiscount = beginDiscount;
    }

    public Date getEndDiscount() {
        return this.endDiscount;
    }

    public void setEndDiscount(Date endDiscount) {
        this.endDiscount = endDiscount;
    }

    public Double getDiscountValue() {
        return this.discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountName() {
        return this.discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountEntity that = (DiscountEntity) o;

        if (this.discountId != that.discountId) return false;
        if (!Objects.equals(this.beginDiscount, that.beginDiscount))
            return false;
        if (!Objects.equals(this.endDiscount, that.endDiscount)) return false;
        if (!Objects.equals(this.discountValue, that.discountValue))
            return false;
        if (!Objects.equals(this.discountName, that.discountName)) return false;
        return Objects.equals(this.discountCode, that.discountCode);
    }

    @Override
    public int hashCode() {
        int result = this.beginDiscount != null ? this.beginDiscount.hashCode() : 0;
        result = 31 * result + (this.endDiscount != null ? this.endDiscount.hashCode() : 0);
        result = 31 * result + (this.discountValue != null ? this.discountValue.hashCode() : 0);
        result = 31 * result + (this.discountName != null ? this.discountName.hashCode() : 0);
        result = 31 * result + (this.discountCode != null ? this.discountCode.hashCode() : 0);
        result = 31 * result + this.discountId;
        return result;
    }

}
