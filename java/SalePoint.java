import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SalePoint{
    private int id;
    private String address;
    private String name;
    private int countOfSalePointCells;
    @JsonProperty("salePointCells")
    private List<SalePointCell> salePointCells;
    private double income;
    @JsonProperty("responsiblePerson")
    private Employee responsiblePerson;
    @JsonProperty("employees")
    private List<Employee> employees;

    public SalePoint(){
        this.id = 0;
        this.address = null;
        this.name = null;
        this.countOfSalePointCells = 0;
        this.salePointCells = new ArrayList<>();
        this.income = 0.0;
        this.responsiblePerson = null;
        this.employees = new ArrayList<>();
    }

    public SalePoint(int id, String address, String name,
                     int countOfSalePointCells, List<SalePointCell> salePointCells,
                     double income, Employee responsiblePerson){
        this.id = id;
        this.address = address;
        this.name = name;
        this.countOfSalePointCells = countOfSalePointCells;
        this.salePointCells = salePointCells;
        this.income = income;
        this.responsiblePerson = responsiblePerson;
    }

    public void moveProduct(String name, int quantity, WarehouseCell warehouseCell, Warehouse warehouse){
        for (int i = 0; i < salePointCells.size(); i ++){
            SalePointCell cell = salePointCells.get(i);
            for (int j = 0; j < cell.getProduct().size(); j++){
                if (cell.getProduct().get(j).getName().equals(name) && cell.getProduct() != null){
                    double price = cell.getProduct().get(j).getPrice();
                    Product currentProduct = cell.sendProduct(name, quantity, warehouse);
                    if (cell.isEnough() && currentProduct != null) {
                        double resultPrice = calculateIncome(price, quantity);
                        setIncome(income + resultPrice);
                        warehouse.setIncome(warehouse.getIncome() - resultPrice);
                        warehouseCell.getProductFromSalePoint(currentProduct);
                        System.out.println("Товар отправлен на склад №: " + warehouse.getId());
                        break;
                    }
                }
            }
        }
    }

    public double calculateIncome(double price, int quantity){
        double resultPrice;
        resultPrice = price * quantity;
        return resultPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountOfSalePointCells(int countOfSalePointCells) {
        this.countOfSalePointCells = countOfSalePointCells;
    }

    public void setSalePointCells(List<SalePointCell> salePointCells) {
        this.salePointCells = salePointCells;
    }

    public String getAddress() {
        return address;
    }

    public double getIncome() {
        return income;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountOfSalePointCells() {
        return countOfSalePointCells;
    }

    public List<SalePointCell> getSalePointCells() {
        return salePointCells;
    }

    public Employee getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Employee responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void changeResponsiblePerson(Employee employee){
        setResponsiblePerson(employee);
    }

    public void dismissEmployee(Employee employee){
        for (Employee emp: employees){
            if (employee.getFio().equals(emp.getFio())){
                employees.remove(employee);
            }
        }
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "SalePoint{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", countOfSalePointCells=" + countOfSalePointCells +
                ", salePointCells=" + salePointCells +
                ", income=" + income +
                ", responsiblePerson=" + responsiblePerson +
                ", employees=" + employees +
                '}';
    }
}
