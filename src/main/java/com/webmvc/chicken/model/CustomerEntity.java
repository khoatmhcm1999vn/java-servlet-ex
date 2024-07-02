package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer", schema = "public", catalog = "testabc")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = AUTO)
    private int customerId;

    @Basic
    @Column(name = "mail")
    private String mail;

    @Basic
    @Column(name = "passwd")
    private String passwd;

    @Basic
    @Column(name = "name")
    private String name;

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity customer = (CustomerEntity) o;

        if (this.customerId != customer.customerId) return false;
        if (!Objects.equals(this.mail, customer.mail)) return false;
        if (!Objects.equals(this.passwd, customer.passwd)) return false;
        return Objects.equals(this.name, customer.name);
    }

    @Override
    public int hashCode() {
        int result = this.customerId;
        result = 31 * result + (this.mail != null ? this.mail.hashCode() : 0);
        result = 31 * result + (this.passwd != null ? this.passwd.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

}
