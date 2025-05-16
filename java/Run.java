import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Run {
    public static List<SalePoint> salePoints = List.of();
    public static List<Warehouse>  warehouse = List.of();
    public static List<Customer> customers = List.of();
    public static List<Employee> employees = List.of();
    public static Scanner scanner = new Scanner(System.in);

    public static void start(){
        try {
            salePoints = WorkWithJson.readSalePoints("src/main/resources/shoppingPoints/salepoints.json");
            warehouse = WorkWithJson.readWarehouse("src/main/resources/shoppingPoints/warehouses.json");
            customers = WorkWithJson.readCustomers("src/main/resources/people/customers.json");
            employees = WorkWithJson.readEmployees("src/main/resources/people/employees.json");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        }
        int choice = 0;
        UI.startWindow();
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 7 && choice >= 1){
                    break;
                } else {
                    UI.clearConsole();
                    UI.startWindow();
                    System.out.println("Нет такого варианта");
                }
            } else {
                UI.clearConsole();
                UI.startWindow();
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }

        switch (choice){
            case 1:
                UI.clearConsole();
                case1();
                break;
            case 2:
                UI.clearConsole();
                case2();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }

        try {
            WorkWithJson.writeSalePoints("src/main/resources/shoppingPoints/salepoints.json", salePoints);
            WorkWithJson.writeWarehouse("src/main/resources/shoppingPoints/warehouses.json", warehouse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void case1(){
        UI.workWithWarehouse();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 3 && choice >= 1){
                    break;
                } else {
                    UI.clearConsole();
                    UI.workWithWarehouse();
                    System.out.println("Нет такого варианта");
                }
            } else {
                UI.clearConsole();
                UI.workWithWarehouse();
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        switch (choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                UI.clearConsole();
                sendToSalePoint();
                break;
        }
    }

    public static void case2(){
        UI.workWithSalePoint();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 4 && choice >= 1){
                    break;
                } else {
                    UI.clearConsole();
                    UI.workWithSalePoint();
                    System.out.println("Нет такого варианта");
                }
            } else {
                UI.clearConsole();
                UI.workWithSalePoint();
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        switch (choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                UI.clearConsole();
                sendToSalePoint();
                break;
        }
    }

    public static Warehouse getWarehouses(){
        int count = UI.warehouseChoice();
        while (true){
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice <= count){
                    return warehouse.get(choice - 1);
                } else {
                    UI.clearConsole();
                    UI.warehouseChoice();
                    System.out.println("Введено некорректное значение");
                }
            } else {
                UI.clearConsole();
                UI.warehouseChoice();
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static SalePoint getSalePoints(){
        int count = UI.salePointChoice();
        while (true){
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice <= count){
                    return salePoints.get(choice - 1);
                } else {
                    UI.clearConsole();
                    UI.salePointChoice();
                    System.out.println("Введено некорректное значение");
                }
            } else {
                UI.clearConsole();
                UI.salePointChoice();
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static Product getProduct(Warehouse warehouse){
        List<Product> productList = UI.warehouseCellChoice();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= productList.size()){
                    List<WarehouseCell> warehouseCellList = warehouse.getWarehouseCells();
                    for (WarehouseCell warehouseCell: warehouseCellList){
                        List<Product> products = warehouseCell.getProduct();
                        for (Product product: products){
                            if (product.getName().equals(productList.get(choice - 1).getName())){
                                return product;
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("Введено некорректное значение");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static void sendToSalePoint(){
        SalePoint salePoint = getSalePoints();
        Warehouse warehouse1 = getWarehouses();
        Product product = getProduct(warehouse1);
        SalePointCell salePointCell = null;
        List<SalePointCell> salePointCells = salePoint.getSalePointCells();
        for (SalePointCell salePointCell1: salePointCells){
            List<Product> products = salePointCell1.getProduct();
            for (Product product1: products){
                if (product1.getName().equals(product1.getName())){
                    salePointCell = salePointCell1;
                }
            }
        }
        int quantity = 0;
        System.out.println("Введите кол-во");
        while (true){
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                break;
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        boolean isSend = warehouse1.moveProduct(product.getName(), quantity, salePointCell, salePoint);
        if (isSend){
            System.out.println("Товар " + product.getName() +
                    " в количестве " + quantity + " шт отправлен в " + salePoint.getName());
        } else {
            System.out.println("Товар не был доставлен");
            sendToSalePoint();
        }
    }
}
