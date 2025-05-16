import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String fio;
    @JsonProperty("products")
    private List<Product> products;

    public Customer(){
        this.fio = null;
        this.products = new ArrayList<>();
    }

    public Customer(String fio, List<Product> products){
        this.fio = fio;
        this.products = products;
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

    @Override
    public String toString() {
        return "Customer{" +
                "fio='" + fio + '\'' +
                ", products=" + products +
                '}';
    }
}
