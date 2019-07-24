package model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Basket {

    private long id;
    private User user;
    private List<Product> products;

    public Basket(long id, User user, List<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public Basket(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Basket basket = (Basket) o;

        if (getId() != basket.getId())
            return false;
        if (getUser() != null ? !getUser().equals(basket.getUser()) : basket.getUser() != null)
            return false;
        return getProducts() != null ? getProducts().equals(basket.getProducts()) : basket.getProducts() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
