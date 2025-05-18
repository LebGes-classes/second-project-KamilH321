import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalePointCell {
    private int id;
    private String category;
    private int quantity;
    @JsonProperty("products")
    private List<Product> products;
    @JsonIgnore
    private boolean isEnough = true;

    public SalePointCell(){
        Random random = new Random();
        this.id = random.nextInt(100);
        this.category = null;
        this.quantity = 0;
        this.products = new ArrayList<>();
    }

    public SalePointCell(int id, String category, int quantity, List<Product> products){
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

    public Product sendProduct(String name, int q, Warehouse warehouse){
        Product currentProduct = null;
        for (Product product: products){
            if (product.getName().equals(name) && product.getQuantity() - q >= 0){
                if ((warehouse.getIncome() - product.getPrice() * q) >= 0){
                    currentProduct = new Product(product.getId(), product.getName(),
                            product.getPrice(), q, product.getCategory(), this.id);
                    if (product.getQuantity() == 0){
                        products.remove(product);
                        quantity--;
                    }
                    product.setQuantity(product.getQuantity() - q);
                } else {
                    isEnough = false;
                    System.out.println("У склада недостаточно средств");
                }
            } else {
                System.out.println("Вы не можете отправить больше " + product.getQuantity() + " шт");
            }
        }
        return currentProduct;
    }

    public void getProductFromWarehouse(Product product){
        boolean found = false;
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
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

    @JsonIgnore
    public boolean isEnough() {
        return isEnough;
    }

    @Override
    public String toString() {
        return "SalePointCell{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", product=" + products +
                '}';
    }
}
