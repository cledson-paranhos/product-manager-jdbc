package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private Integer id;
    private String name;
    private List<Product> listProduct = new ArrayList<>();

    public Category() {

    }

    public Category(Integer id, String name, List<Product> listProduct) {
        this.id = id;
        this.name = name;
        this.listProduct = listProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listProduct=" + listProduct +
                '}';
    }
}
