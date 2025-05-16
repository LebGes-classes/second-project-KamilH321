public class Product {
    private int id;
    private int idOfCell;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(){
        this.id = 0;
        this.name = null;
        this.price = 0.0;
        this.quantity = 0;
        this.category = null;
        this.idOfCell = 0;
    }

    public Product(int id, String name, double price, int quantity, String category, int idOfCell){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.idOfCell = idOfCell;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getIdOfCell() {
        return idOfCell;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idOfCell=" + idOfCell +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
