package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getProduct(String drawingNumber);
    String createProduct(Product product);
    void deleteProduct(String drawingNumber);
    void updateProduct(String drawingNumber, Product product);
    void partialUpdateProduct(String drawingNumber, Machine operations, Duration operationTime, Order order, Product product);
}
