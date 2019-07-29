package model;

import java.util.List;

public class Order {

    private long id;
    private String address;
    private String payment;
    private Code code;
    private List<Product> listOfProducts;

    public Order(long id, String address, String payment, Code code, List<Product> listOfProducts) {
        this.id = id;
        this.address = address;
        this.payment = payment;
        this.code = code;
        this.listOfProducts = listOfProducts;
    }

    public Order(String address, String payment, Code code, List<Product> listOfProducts) {
        this.address = address;
        this.payment = payment;
        this.code = code;
        this.listOfProducts = listOfProducts;
    }

    public Order(String address, String payment, Code code) {
        this.address = address;
        this.payment = payment;
        this.code = code;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;

    }

    public String getListOfProductsString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : getListOfProducts()) {
            sb.append(product.getId().toString()).append(";");
        }
        return sb.toString();
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;

        if (getId() != order.getId())
            return false;
        if (getAddress() != null ? !getAddress().equals(order.getAddress()) : order.getAddress() != null)
            return false;
        if (getPayment() != null ? !getPayment().equals(order.getPayment()) : order.getPayment() != null)
            return false;
        if (getCode() != null ? !getCode().equals(order.getCode()) : order.getCode() != null)
            return false;
        return getListOfProducts() != null ? getListOfProducts().equals(order.getListOfProducts()) : order.getListOfProducts() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPayment() != null ? getPayment().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getListOfProducts() != null ? getListOfProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                ", code=" + code +
                ", listOfProducts=" + listOfProducts +
                '}';
    }
}
