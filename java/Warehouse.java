import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Warehouse{
    private int id;
    private String address;
    private int countOfWarehouseCells;
    @JsonProperty("warehouseCells")
    private List<WarehouseCell> warehouseCells;
    private double income;
    @JsonProperty("responsiblePerson")
    private Employee responsiblePerson;
    @JsonProperty("employees")
    private List<Employee> employees;
    @JsonIgnore
    private boolean isOpen = true;

    public Warehouse(){
        Random random = new Random();
        this.id = 0;
        this.address = null;
        this.countOfWarehouseCells = 0;
        this.warehouseCells = new ArrayList<>();
        this.income = random.nextDouble(1000000.0);
        this.responsiblePerson = null;
        this.employees = new ArrayList<>();
    }

    public Warehouse(int id, String address, int countOfWarehouseCells,
                     ArrayList<WarehouseCell> warehouseCells, double income){
        this.id = id;
        this.address = address;
        this.countOfWarehouseCells = countOfWarehouseCells;
        this.warehouseCells = warehouseCells;
        this.income = income;
    }

    public boolean moveProduct(String name, int quantity, SalePointCell cellPointCell, SalePoint salePoint){
        boolean isSend = false;
        for (int i = 0; i < warehouseCells.size(); i ++){
            WarehouseCell cell = warehouseCells.get(i);
            for (int j = 0; j < cell.getProduct().size(); j++){
                if (cell.getProduct().get(j).getName().equals(name) && cell.getProduct() != null){
                    double price = cell.getProduct().get(j).getPrice();
                    Product currentProduct = cell.sendProduct(name, quantity, salePoint);
                    if (cell.isEnough() && currentProduct != null){
                        double resultPrice = calculateIncome(price, quantity);
                        setIncome(income + resultPrice);
                        salePoint.setIncome(salePoint.getIncome() - resultPrice);
                        cellPointCell.getProductFromWarehouse(currentProduct);
                        isSend = true;
                        break;
                    }
                }
            }
        }
        return isSend;
    }

    public double calculateIncome(double price, int quantity){
        double resultPrice;
        resultPrice = price * quantity;
        return resultPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountOfWarehouseCells(int countOfWarehouseCells) {
        this.countOfWarehouseCells = countOfWarehouseCells;
    }

    public void setWarehouseCells(ArrayList<WarehouseCell> warehouseCells) {
        this.warehouseCells = warehouseCells;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getCountOfWarehouseCells() {
        return countOfWarehouseCells;
    }

    public List<WarehouseCell> getWarehouseCells() {
        return warehouseCells;
    }

    public double getIncome() {
        return income;
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

    @JsonIgnore
    public void setOpen(boolean open) {
        isOpen = open;
    }

    @JsonIgnore
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", countOfWarehouseCells=" + countOfWarehouseCells +
                ", warehouseCells=" + warehouseCells +
                ", income=" + income +
                ", responsiblePerson=" + responsiblePerson +
                ", employees=" + employees +
                '}';
    }
}
