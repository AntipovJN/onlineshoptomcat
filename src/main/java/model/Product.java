package model;

public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;

    public Product(Long id, String name, String descriptional, double price) {
        this.id = id;
        this.name = name;
        this.description = descriptional;
        this.price = price;
    }
    public Product (String name, String descriptional, double price) {
        this.name = name;
        this.description = descriptional;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product item = (Product) o;

        if (Double.compare(item.getPrice(), getPrice()) != 0) return false;
        if (getId() != null ? !getId().equals(item.getId()) : item.getId() != null) return false;
        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(item.getDescription()) : item.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
