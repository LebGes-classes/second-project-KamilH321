import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String fio;
    @JsonProperty("products")
    private List<Product> products;
    private double cash;

    public Customer(){
        this.fio = null;
        this.products = new ArrayList<>();
        this.cash = 0.0;
    }

    public Customer(String fio, List<Product> products, double cash){
        this.fio = fio;
        this.products = products;
        this.cash = cash;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fio='" + fio + '\'' +
                ", products=" + products +
                '}';
    }
}
