import java.util.ArrayList;
import java.util.List;

public class UI {
    public static void startWindow(){
        System.out.println("<----------------------------------->");
        System.out.println("1.Управление складами                ");
        System.out.println("2.Управление пунктами продаж         ");
        System.out.println("3.Приобрести товар                   ");
        System.out.println("4.Управление сотрудниками            ");
        System.out.println("5.Информация о складе                ");
        System.out.println("6.Информация о пункте продаж         ");
        System.out.println("7.Выйти                              ");
        System.out.println("<----------------------------------->");
    }

    public static void workWithWarehouse(){
        System.out.println("<----------------------------------->");
        System.out.println("1.Открыть склад                      ");
        System.out.println("2.Закрыть склад                      ");
        System.out.println("3.Отправить товар в пункт продаж     ");
        System.out.println("4.Вернутся в главное меню");
        System.out.println("<----------------------------------->");
    }

    public static void workWithSalePoint(){
        System.out.println("<----------------------------------->");
        System.out.println("1.Открыть пункт продаж               ");
        System.out.println("2.Закрыть пункт продаж               ");
        System.out.println("3.Отправить товар на склад           ");
        System.out.println("4.Закупить товар со склада           ");
        System.out.println("5.Вернутся в главное меню");
        System.out.println("<----------------------------------->");
    }

    public static int warehouseChoice(){
        List<Warehouse> warehouses = Run.warehouse;
        System.out.println("<----------------------------------->");
        System.out.println("Выберите склад:                  ");
        for (int i = 0; i < warehouses.size(); i++){
            int id = warehouses.get(i).getId();
            System.out.println((i + 1) + ".Склад №:" + id);
        }
        System.out.println("0.Закрыть программу");
        System.out.println("<----------------------------------->");
        return warehouses.size();
    }

    public static int salePointChoice(){
        List<SalePoint> salePoints = Run.salePoints;
        System.out.println("<----------------------------------->");
        System.out.println("Выберите магазин:                  ");
        for (int i = 0; i < salePoints.size(); i++){
            String name = salePoints.get(i).getName();
            System.out.println((i + 1) + "." + name);
        }
        System.out.println("0.Закрыть программу");
        System.out.println("<----------------------------------->");
        return salePoints.size();
    }

    public static List<Product> warehouseCellChoice(Warehouse warehouse){
        List<Product> productList = new ArrayList<>();
        System.out.println("<----------------------------------->");
        System.out.println("Выберите продукт:                  ");
        List<WarehouseCell> warehouseCells = warehouse.getWarehouseCells();
        for (int j = 0; j < warehouseCells.size(); j++){
            WarehouseCell warehouseCell = warehouseCells.get(j);
            List<Product> products = warehouseCell.getProduct();
            productList.addAll(products);
        }
        for (int i = 0; i < productList.size(); i++){
            System.out.println((i+1) + "." + productList.get(i).getName() + " " +
                    productList.get(i).getQuantity() + " шт");
        }
        System.out.println("<----------------------------------->");
        return productList;
    }

    public static List<Product> salePointCellChoice(SalePoint salePoint){
        List<Product> productList = new ArrayList<>();
        System.out.println("<----------------------------------->");
        System.out.println("Выберите продукт:                  ");
        List<SalePointCell> salePointCells = salePoint.getSalePointCells();
        for (int j = 0; j < salePointCells.size(); j++){
            SalePointCell salePointCell = salePointCells.get(j);
            List<Product> products = salePointCell.getProduct();
            productList.addAll(products);
        }
        for (int i = 0; i < productList.size(); i++){
            System.out.println((i+1) + "." + productList.get(i).getName() + " " +
                    productList.get(i).getQuantity() + " шт");
        }
        System.out.println("<----------------------------------->");
        return productList;
    }

    public static void showWarehouseInfo(Warehouse warehouse){
        System.out.println("<----------------------------------->");
        System.out.println("Склад № " + warehouse.getId());
        System.out.println("По адресу: " + warehouse.getAddress());
        System.out.println("Доходность склада: " + warehouse.getIncome() + " р.");
        System.out.println("Ответственное лицо: " + warehouse.getResponsiblePerson().getFio());
        System.out.println("Работники: ");
        for (Employee employee: warehouse.getEmployees()){
            System.out.println(employee.getFio() + ", " + employee.getPost());
        }
        System.out.println("1.Вернуться в главное меню");
        System.out.println("2.Выйти из программы");
        System.out.println("<----------------------------------->");
    }

    public static void showSalePointInfo(SalePoint salePoint){
        System.out.println("<----------------------------------->");
        System.out.println("Магазин: " + salePoint.getName());
        System.out.println("По адресу: " + salePoint.getAddress());
        System.out.println("Доходность склада: " + salePoint.getIncome() + " р.");
        System.out.println("Ответственное лицо: " + salePoint.getResponsiblePerson().getFio());
        System.out.println("Работники: ");
        for (Employee employee: salePoint.getEmployees()){
            System.out.println(employee.getFio() + ", " + employee.getPost());
        }
        System.out.println("1.Вернуться в главное меню");
        System.out.println("2.Выйти из программы");
        System.out.println("<----------------------------------->");
    }

    public static void showWorkWithEmployee(){
        System.out.println("<----------------------------------->");
        System.out.println("1.Уволить сотрудника                 ");
        System.out.println("2.Нанять сотрудника                 ");
        System.out.println("3.Назначить/сменить ответственное лицо");
        System.out.println("4.Выйти в главное меню");
        System.out.println("<----------------------------------->");
    }

    public static void showWarehouseEmployee(Warehouse warehouse){
        System.out.println("<----------------------------------->");
        for (int i = 0; i < warehouse.getEmployees().size(); i++){
            System.out.println((i + 1) + "." + warehouse.getEmployees().get(i).getFio() + " " + warehouse.getEmployees().get(i).getPost());
        }
        System.out.println("<----------------------------------->");
    }

    public static void showSalePointEmployee(SalePoint salePoint){
        System.out.println("<----------------------------------->");
        for (int i = 0; i < salePoint.getEmployees().size(); i++){
            System.out.println((i + 1) + "." + salePoint.getEmployees().get(i).getFio() + " " + salePoint.getEmployees().get(i).getPost());
        }
        System.out.println("<----------------------------------->");
    }

    public static void showPlaceChoice(){
        System.out.println("<----------------------------------->");
        System.out.println("Выберите с чем вы хотите работать");
        System.out.println("1.Склад");
        System.out.println("2.Пункт продаж");
        System.out.println("3.Вернуться назад");
        System.out.println("<----------------------------------->");
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
    }

    public static int choiceCustomer(){
        List<Customer> customers = Run.customers;
        System.out.println("<----------------------------------->");
        System.out.println("Выберите покупателя:");
        for (int i = 0; i < customers.size(); i++){
            System.out.println((i + 1) + "." + customers.get(i).getFio());
        }
        System.out.println("<----------------------------------->");
        return customers.size();
    }
}
