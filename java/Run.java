import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Run {
    public static List<SalePoint> salePoints = List.of();
    public static List<Warehouse>  warehouse = List.of();
    public static List<Customer> customers = List.of();
    public static List<Employee> employees = List.of();
    public static Scanner scanner = new Scanner(System.in);

    public static void readJson(){
        try {
            salePoints = WorkWithJson.readSalePoints("src/main/resources/shoppingPoints/salepoints.json");
            warehouse = WorkWithJson.readWarehouse("src/main/resources/shoppingPoints/warehouses.json");
            customers = WorkWithJson.readCustomers("src/main/resources/people/customers.json");
            employees = WorkWithJson.readEmployees("src/main/resources/people/employees.json");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeToJson(){
        try {
            WorkWithJson.writeSalePoints("src/main/resources/shoppingPoints/salepoints.json", salePoints);
            WorkWithJson.writeWarehouse("src/main/resources/shoppingPoints/warehouses.json", warehouse);
            WorkWithJson.writeCustomers("src/main/resources/people/customers.json", customers);
            WorkWithJson.writeEmployees("src/main/resources/people/employees.json", employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(){
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
                UI.clearConsole();
                case3();
                break;
            case 4:
                UI.clearConsole();
                workWithEmployee();
                break;
            case 5:
                UI.clearConsole();
                case5();
                break;
            case 6:
                UI.clearConsole();
                case6();
                break;
            case 7:
                break;
        }
    }

    public static void case1(){
        UI.workWithWarehouse();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 4 && choice >= 1){
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
                UI.clearConsole();
                openWarehouse();
                break;
            case 2:
                UI.clearConsole();
                closeWarehouse();
                break;
            case 3:
                UI.clearConsole();
                sendToSalePoint();
                break;
            case 4:
                UI.clearConsole();
                start();
        }
    }

    public static void case2(){
        UI.workWithSalePoint();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 5 && choice >= 1){
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
                UI.clearConsole();
                openSalePoint();
                break;
            case 2:
                UI.clearConsole();
                closeSalePoint();
                break;
            case 3:
                UI.clearConsole();
                returnToWarehouse();
                break;
            case 4:
                UI.clearConsole();
                sendToSalePointFromWarehouse();
                break;
            case 5:
                UI.clearConsole();
                start();
        }
    }

    public static void case3(){
        int count = UI.choiceCustomer();
        while (true){
            if (scanner.hasNextInt()){
                int choice = scanner.nextInt();
                if (choice <= count){
                    Customer customer = customers.get(choice - 1);
                    sellProduct(customer);
                    break;
                } else {
                    System.out.println("Такого варианта нет");
                }
            } else {
                scanner.next();
                System.out.println("Введено не корректное значение, повторите попытку: ");
            }
        }

    }

    public static void sellProduct(Customer customer){
        SalePoint salePoint = getSalePoints();
        Product product = getProductFromSalePoint(salePoint);
        System.out.print("Введите кол-во: ");
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                break;

            } else {
                scanner.next();
                System.out.println("Введено не корректное значение, повторите попытку: ");
            }
        }
        boolean isSell = salePoint.sell(product.getName(), choice, customer);
        if (isSell){
            System.out.println("Товар " + product.getName() + " в кол-ве " + choice + " куплен");
        } else {
            System.out.println("Товар не куплен");
        }
        start();
    }

    public static void case5(){
        Warehouse warehouse1 = getWarehouses();
        UI.clearConsole();
        UI.showWarehouseInfo(warehouse1);
        while (true){
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 1){
                    start();
                }
                else if (choice == 2){
                    break;
                }
                else {
                    System.out.println("Введено некорректное значение");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static void case6(){
        SalePoint salePoint = getSalePoints();
        UI.clearConsole();
        UI.showSalePointInfo(salePoint);
        while (true){
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 1){
                    start();
                }
                else if (choice == 2){
                    break;
                }
                else {
                    System.out.println("Введено некорректное значение");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static Warehouse getWarehouses(){
        int count = UI.warehouseChoice();
        while (true){
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice <= count && choice != 0){
                    return warehouse.get(choice - 1);
                }
                else if (choice == 0){
                    UI.clearConsole();
                    return null;
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
                if (choice <= count && choice != 0) {
                    return salePoints.get(choice - 1);
                }
                else if (choice == 0){
                    UI.clearConsole();
                    return null;
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
        List<Product> productList = UI.warehouseCellChoice(warehouse);
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= productList.size()){
                    List<WarehouseCell> warehouseCellList = warehouse.getWarehouseCells();
                    for (WarehouseCell warehouseCell: warehouseCellList){
                        List<Product> products = warehouseCell.getProduct();
                        System.out.println(products);
                        for (Product product: products){
                            if (product.getName().equals(productList.get(choice - 1).getName())){
                                System.out.println("123");
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

    public static Product getProductFromSalePoint(SalePoint salePoint){
        List<Product> productList = UI.salePointCellChoice(salePoint);
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= productList.size()){
                    List<SalePointCell> salePointCells = salePoint.getSalePointCells();
                    for (SalePointCell salePointCell: salePointCells){
                        List<Product> products = salePointCell.getProduct();
                        for (Product product: products){
                            if (product.getName().equals(productList.get(choice - 1).getName())){
                                return product;
                            }
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
        Warehouse warehouse1 = getWarehouses();
        SalePoint salePoint = getSalePoints();
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

    public static void sendToSalePointFromWarehouse(){
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

    public static void returnToWarehouse(){
        Warehouse warehouse1 = getWarehouses();
        SalePoint salePoint = getSalePoints();
        Product product = getProductFromSalePoint(salePoint);
        WarehouseCell warehouseCell = null;
        List<WarehouseCell> warehouseCells = warehouse1.getWarehouseCells();
        for (WarehouseCell warehouseCell1: warehouseCells){
            List<Product> products = warehouseCell1.getProduct();
            for (Product product1: products){
                if (product1.getName().equals(product1.getName())){
                    warehouseCell = warehouseCell1;
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
        boolean isSend = salePoint.moveProduct(product.getName(), quantity, warehouseCell, warehouse1);
        if (isSend){
            System.out.println("Товар " + product.getName() +
                    " в количестве " + quantity + " шт отправлен на склад №: " + warehouse1.getId());
        } else {
            System.out.println("Товар не был доставлен");
            sendToSalePoint();
        }
    }

    public static void openWarehouse(){
        warehouse.add(new Warehouse());
        System.out.print("Введите id склада: ");
        int id = 0;
        while (true){
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                Warehouse warehouse1 = warehouse.getLast();
                warehouse1.setOpen(true);
                warehouse1.setId(id);
                System.out.print("Введите адресс: ");
                String address = "";
                scanner.nextLine();
                while (true){
                    address = scanner.nextLine();
                    if (!address.isEmpty()){
                        break;
                    }
                    else{
                        System.out.print("Вы ничего не ввели, повторите ввод: ");
                    }
                }
                warehouse1.setAddress(address);
                System.out.println("Склад № " + id + " открыт");
                break;
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        case1();
    }

    public static void openSalePoint(){
        System.out.print("Введите название магазина: ");
        salePoints.add(new SalePoint());
        String name = "";
        String address = "";
        scanner.nextLine();
        while (true){
            name = scanner.nextLine();
            if (!name.isEmpty()){
                break;
            }
            System.out.print("Вы ничего не ввели, повторите ввод: ");
        }
        System.out.print("Введите адрес: ");
        while (true){
            address = scanner.nextLine();
            if (!address.isEmpty()){
                break;
            }
            System.out.print("Вы ничего не ввели, повторите ввод: ");
        }
        SalePoint salePoint = salePoints.getLast();
        salePoint.setOpen(true);
        salePoint.setName(name);
        salePoint.setAddress(address);
        System.out.println("Магазин " + name + " открыт");
        case2();
    }

    public static void closeWarehouse(){
        UI.warehouseChoice();
        System.out.println("Введите id склада: ");
        int id = 0;
        while (true){
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (id <= warehouse.size()){
                    for (Warehouse warehouse1: warehouse){
                        if (warehouse1.getId() == id){
                            warehouse1.setOpen(false);
                            warehouse.remove(warehouse1);
                        }
                    }
                    break;
                } else {
                    System.out.println("Такого склада нет");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
    }

    public static void closeSalePoint(){
        UI.salePointChoice();
        String name = "";
        System.out.println("Введите название магазина: ");
        boolean isClose = false;
        while (true){
            name = scanner.next();
            for (SalePoint salePoint: salePoints){
                if (salePoint.getName().equals(name)){
                    salePoint.setOpen(false);
                    isClose = true;
                    salePoints.remove(salePoint);
                }
            }
            if (isClose){
                System.out.println("Магазин закрылся");
                break;
            } else {
                System.out.println("Магазина " + name + " не существует");
                scanner.next();
            }
        }
    }

    public static void workWithEmployee(){
        UI.showWorkWithEmployee();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 4){
                    break;
                } else {
                    System.out.println("Такого варианта нет");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        switch (choice){
            case 1:
                UI.clearConsole();
                placeChoice(choice);
                break;
            case 2:
                UI.clearConsole();
                placeChoice(choice);
                break;
            case 3:
                UI.clearConsole();
                placeChoice(choice);
                break;
            case 4:
                UI.clearConsole();
                start();
        }
    }

    public static void placeChoice(int ch){
        UI.showPlaceChoice();
        int choice = 0;
        while (true){
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice <= 3){
                    break;
                } else {
                    System.out.println("Такого варианта нет");
                }
            } else {
                System.out.println("Введено некорректное значение");
                scanner.next();
            }
        }
        switch (choice){
            case 1:
                UI.clearConsole();
                workWithW(ch);
                break;
            case 2:
                UI.clearConsole();
                workWithS(ch);
                break;
            case 3:
                UI.clearConsole();
                workWithEmployee();
        }

    }

    public static void workWithW(int ch){
        int choice = 0;
        Warehouse warehouse1 = getWarehouses();
        List<Employee> employees1 = warehouse1.getEmployees();
        if (ch == 1){
            UI.showWarehouseEmployee(warehouse1);
            while (true){
                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    if (choice <= employees1.size()){
                        System.out.println("Сотрудник " + employees1.get(choice - 1).getFio() + " уволен");
                        employees1.remove(employees1.get(choice - 1));
                        warehouse1.setEmployees(employees1);
                        System.out.println(warehouse1);
                        for (int i = 0; i < employees1.size(); i++){
                            if (!employees.contains(employees1.get(i))){
                                employees.add(employees1.get(i));
                            }
                        }
                        break;
                    } else {
                        System.out.println("Такого сотрудника нет");
                    }
                }
                else {
                    System.out.println("Введено некорректное значение");
                    scanner.next();
                }
            }
        }
        else if (ch == 2){
            String fio = "";
            String post = "";
            System.out.print("Введите ФИО сотрудника: ");
            scanner.nextLine();
            while (true){
                fio = scanner.nextLine();
                if (!fio.trim().isEmpty()){
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            System.out.print("Введите должность: ");
            while (true){
                post = scanner.nextLine();
                if (!post.trim().isEmpty()){
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            employees1.add(new Employee(fio, post));
            warehouse1.setEmployees(employees1);
            for (int i = 0; i < employees1.size(); i++){
                if (!employees.contains(employees1.get(i))){
                    employees.add(employees1.get(i));
                }
            }
            System.out.println("Сотрудник " + fio + " добавлен");
        }
        else if (ch == 3){
            String fio = "";
            System.out.print("Введите ФИО: ");
            scanner.nextLine();
            while (true){
                fio = scanner.nextLine();
                if (!fio.trim().isEmpty()){
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            warehouse1.setResponsiblePerson(new Employee(fio, "Директор"));
            employees.add(warehouse1.getResponsiblePerson());

        }
        start();
    }

    public static void workWithS(int ch) {
        int choice = 0;
        SalePoint salePoint = getSalePoints();
        List<Employee> employees1 = salePoint.getEmployees();
        if (ch == 1) {
            UI.showSalePointEmployee(salePoint);
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice <= employees1.size()) {
                        System.out.println("Сотрудник " + employees1.get(choice - 1).getFio() + " уволен");
                        employees1.remove(employees1.get(choice - 1));
                        salePoint.setEmployees(employees1);
                        for (int i = 0; i < employees1.size(); i++){
                            if (!employees.contains(employees1.get(i))){
                                employees.add(employees1.get(i));
                            }
                        }
                        break;
                    } else {
                        System.out.println("Такого сотрудника нет");
                    }
                } else {
                    System.out.println("Введено некорректное значение");
                    scanner.next();
                }
            }
        }
        else if (ch == 2){
            String fio = "";
            String post = "";
            System.out.print("Введите ФИО сотрудника: ");
            scanner.nextLine();
            while (true) {
                fio = scanner.nextLine();
                if (!fio.trim().isEmpty()) {
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            System.out.print("Введите должность: ");
            scanner.nextLine();
            while (true) {
                post = scanner.nextLine();
                if (!post.trim().isEmpty()) {
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            employees1.add(new Employee(fio, post));
            salePoint.setEmployees(employees1);
            for (int i = 0; i < employees1.size(); i++){
                if (!employees.contains(employees1.get(i))){
                    employees.add(employees1.get(i));
                }
            }
            System.out.println("Сотрудник " + fio + " добавлен");
        }

        else if (ch == 3){
            String fio = "";
            System.out.print("Введите ФИО: ");
            scanner.nextLine();
            while (true){
                fio = scanner.nextLine();
                if (!fio.trim().isEmpty()){
                    break;
                }
                System.out.print("Вы ничего не ввели, повторите ввод: ");
            }
            salePoint.setResponsiblePerson(new Employee(fio, "Директор"));
            employees.add(salePoint.getResponsiblePerson());

        }
        start();
    }

}
