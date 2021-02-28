package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getProduct(String drawingNumber);
    String createProduct(Product product);
    void deleteProduct(String drawingNumber);
    void updateProduct(String drawingNumber, Product product);
    void updateProductPartial(String drawingNumber, Map<String, Object> fields);
    Collection<Machine> getOperations(String drawingNumber);
    void addOperation(String drawingNumber, Machine machine);
    void removeOperation(String drawingNumber, Machine machine);
}
