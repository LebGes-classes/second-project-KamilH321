import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WarehouseCell{
    private int id;
    private String category;
    private int quantity;
    @JsonProperty("products")
    private List<Product> products;
    @JsonIgnore
    private boolean isEnough = true;

    public WarehouseCell(){
        this.id = 0;
        this.category = null;
        this.quantity = 0;
        this.products = new ArrayList<>();
    }

    public WarehouseCell(int id, String category, int quantity, List<Product> products){
        this.id = id;
        this.category = category;
        this.quantity = quantity;
        this.products = products;
    }

    public boolean containsProduct(Product p){
        boolean isContain = false;
        for (Product product: products) {
            if (p.getId() == product.getId()) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    public Product sendProduct(String name, int q, SalePoint salePoint){
        Product currentProduct = null;
        for (Product product: products){
            if (product.getName().equals(name) && product.getQuantity() - q >= 0){
                if ((salePoint.getIncome() - product.getPrice() * q) >= 0) {
                    currentProduct = new Product(product.getId(), product.getName(),
                            product.getPrice(), q, product.getCategory(), this.id);
                    product.setQuantity(product.getQuantity() - q);
                    if (product.getQuantity() == 0) {
                        products.remove(product);
                        quantity--;
                    }
                    break;
                }
                else {
                    isEnough = false;
                    System.out.println("У магазина " + salePoint.getName() + " недостаточно средств");
                }
            } else {
                System.out.println("Данного товара нет, либо нет такого кол-ва выбранного вами товара");
            }

        }
        return currentProduct;
    }

    public void getProductFromSalePoint(Product product){
        boolean found = false;
        for (Product p : products) {
            System.out.println(product);
            if (p.getName().equals(product.getName())) {
                System.out.println(1);
                p.setQuantity(p.getQuantity() + product.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(2);
            products.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getCategory(),
                    this.id
            ));
            quantity++;
        }
    }

    @JsonIgnore
    public boolean isEnough() {
        return isEnough;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<Product> getProduct() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }



    @Override
    public String toString() {
        return "WarehouseCell{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", product=" + products +
                '}';
    }
}
