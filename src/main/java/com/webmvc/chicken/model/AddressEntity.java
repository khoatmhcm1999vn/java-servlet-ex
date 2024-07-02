package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "address", schema = "public", catalog = "testabc")
public class AddressEntity {

    @Basic
    @Column(name = "address_name")
    private String addressName;

    @Basic
    @Column(name = "phone")
    private Integer phone;

    @Basic
    @Column(name = "province")
    private String province;

    @Basic
    @Column(name = "district")
    private String district;

    @Basic
    @Column(name = "commune")
    private String commune;

    @Basic
    @Column(name = "details")
    private String details;

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = AUTO)
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerEntity customerId;

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return this.commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAddressId() {
        return this.addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public CustomerEntity getCustomerId(){ return this.customerId; }

    public void setCustomerId(CustomerEntity customerId) {
        this.customerId = customerId;
    }

    public String fullAddress() {
        return this.getDetails() + ", " + this.getCommune() + ", " + this.getDistrict() + ", " + this.getProvince();
    }

    public String phoneFormat() {
        return String.valueOf(this.phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "(+84)$1 $2 $3");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (this.addressId != that.addressId) return false;
        if (!Objects.equals(this.addressName, that.addressName)) return false;
        if (!Objects.equals(this.phone, that.phone)) return false;
        if (!Objects.equals(this.province, that.province)) return false;
        if (!Objects.equals(this.district, that.district)) return false;
        if (!Objects.equals(this.commune, that.commune)) return false;
        return Objects.equals(this.details, that.details);
    }

    @Override
    public int hashCode() {
        int result = this.addressName != null ? this.addressName.hashCode() : 0;
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.province != null ? this.province.hashCode() : 0);
        result = 31 * result + (this.district != null ? this.district.hashCode() : 0);
        result = 31 * result + (this.commune != null ? this.commune.hashCode() : 0);
        result = 31 * result + (this.details != null ? this.details.hashCode() : 0);
        result = 31 * result + this.addressId;

        return result;
    }

}
