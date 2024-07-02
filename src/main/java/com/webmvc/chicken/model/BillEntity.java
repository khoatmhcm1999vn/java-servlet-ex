package com.webmvc.chicken.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bill", schema = "public", catalog = "testabc")
public class BillEntity {

    @Basic
    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Basic
    @Column(name = "processed")
    private Byte processed;

    @Id
    @Column(name = "bill_id")
    private String billId;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity addressByAddressId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id")
    private DiscountEntity discount;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Byte getProcessed() {
        return this.processed;
    }

    public void setProcessed(Byte processed) {
        this.processed = processed;
    }

    public String getBillId() {
        return this.billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public AddressEntity getAddressByAddressId() {
        return this.addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    public CustomerEntity getCustomer(){ return this.customer; }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public DiscountEntity getDiscount() { return this.discount; }

    public void setDiscount(DiscountEntity discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (!Objects.equals(this.billId, that.billId)) return false;
        if (!Objects.equals(this.invoiceDate, that.invoiceDate)) return false;

        return Objects.equals(this.processed, that.processed);
    }

    @Override
    public int hashCode() {
        int result = this.invoiceDate != null ? this.invoiceDate.hashCode() : 0;
        result = 31 * result + (this.processed != null ? this.processed.hashCode() : 0);
        return result;
    }

}
