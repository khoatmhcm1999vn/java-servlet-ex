package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "bill_item", schema = "public", catalog = "testabc")
public class BillItemEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = AUTO)
    private int itemId;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private BillEntity billId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductEntity productId;

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BillEntity getBillId() {
        return this.billId;
    }

    public void setBillId(BillEntity billId) {
        this.billId = billId;
    }

    public ProductEntity getProductId() { return this.productId; }

    public void setProductId(ProductEntity productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillItemEntity item = (BillItemEntity) o;

        if (this.itemId != item.itemId) return false;
        return Objects.equals(this.quantity, item.quantity);
    }

    @Override
    public int hashCode() {
        int result = this.itemId;
        result = 31 * result + (this.quantity != null ? this.quantity.hashCode() : 0);
        return result;
    }

}
