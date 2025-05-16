import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WorkWithJson {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // чтение данных из json
    public static List<SalePoint> readSalePoints(String filePath) throws IOException {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<>(){}
        );
    }

    public static List<Warehouse> readWarehouse(String filePath) throws IOException {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<>(){}
        );
    }

    public static List<Customer> readCustomers(String filePath) throws IOException {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<>(){}
        );
    }

    public static List<Employee> readEmployees(String filePath) throws IOException {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<>(){}
        );
    }

    // запись данных в json
    public static void writeSalePoints(String filePath, List<SalePoint> salePoints) throws IOException {
        mapper.writeValue(new File(filePath), salePoints);
    }

    public static void writeWarehouse(String filePath, List<Warehouse> warehouses) throws IOException {
        mapper.writeValue(new File(filePath), warehouses);
    }

    public static void writeCustomers(String filePath, List<Customer> customers) throws IOException {
        mapper.writeValue(new File(filePath), customers);
    }

    public static void writeEmployees(String filePath, List<Employee> employees) throws IOException {
        mapper.writeValue(new File(filePath), employees);
    }
}
