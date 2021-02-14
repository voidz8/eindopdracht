package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ProductNotFoundException;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(String drawingNumber) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        return productRepository.findByDrawingNumber(drawingNumber);
    }

    @Override
    public String createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct.getDrawingNumber();
    }

    @Override
    public void deleteProduct(String drawingNumber) {
    productRepository.deleteById(drawingNumber);
    }

    @Override
    public void updateProduct(String drawingNumber, Product product) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product existingProduct = productRepository.findByDrawingNumber(drawingNumber).orElse(null);
        existingProduct.setOperations(product.getOperations());
        existingProduct.setOperationTime(product.getOperationTime());
        existingProduct.setOrders(product.getOrders());
        productRepository.save(existingProduct);
    }

    @Override
    public void partialUpdateProduct(String drawingNumber, Machine operations, Duration operationTime, Order order, Product product) {
    if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product existingProduct = productRepository.findByDrawingNumber(drawingNumber).orElse(null);
        if (existingProduct.getOperations() != null){existingProduct.setOperations(product.getOperations());}
        if (existingProduct.getOperationTime() != null){existingProduct.setOperationTime(product.getOperationTime());}
        if (existingProduct.getOrders() != null){existingProduct.setOrders(product.getOrders());}
        productRepository.save(existingProduct);
    }


}
