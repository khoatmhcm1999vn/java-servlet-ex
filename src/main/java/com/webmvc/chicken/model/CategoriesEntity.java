package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "categories", schema = "public", catalog = "testabc")
public class CategoriesEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = AUTO)
    private int categoryId;

    @Basic
    @Column(name = "category_name")
    private String categoryName;

    @Basic
    @Column(name = "category_code")
    private String categoryCode;

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

        if (this.categoryId != that.categoryId) return false;
        if (!Objects.equals(this.categoryName, that.categoryName)) return false;
        return Objects.equals(this.categoryCode, that.categoryCode);
    }

    @Override
    public int hashCode() {
        int result = this.categoryId;
        result = 31 * result + (this.categoryName != null ? this.categoryName.hashCode() : 0);
        result = 31 * result + (this.categoryCode != null ? this.categoryCode.hashCode() : 0);
        return result;
    }

}
