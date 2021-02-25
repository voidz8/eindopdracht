package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ProductNotFoundException;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
        existingProduct.setAmount(product.getAmount());
        existingProduct.setMaterial(product.getMaterial());
        existingProduct.setOperationTime(product.getOperationTime());
        existingProduct.setOrders(product.getOrders());
        productRepository.save(existingProduct);
    }


    @Override
    public void addOperation(String drawingNumber, Machine machine) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product product = productRepository.findByDrawingNumber(drawingNumber).get();
        product.addOperation(machine);
        productRepository.save(product);
    }

    @Override
    public void removeOperation(String drawingNumber) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product product = productRepository.findByDrawingNumber(drawingNumber).get();
        for (Machine machine : product.getOperations()){
            product.removeOperation(machine);
        }
        productRepository.save(product);
    }

    @Override
    public Collection<Machine> getOperations(String drawingNumber) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product product = productRepository.findByDrawingNumber(drawingNumber).get();
        return product.getOperations();
    }

    @Override
    public void updateProductPartial(String drawingNumber, Map<String, Object> fields) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product product = productRepository.findByDrawingNumber(drawingNumber).get();
        for (String field: fields.keySet()){
            switch (field.toLowerCase()){
                case "amount":
                    product.setAmount((Integer) fields.get(field));
                    break;
                case "material":
                    product.setMaterial((String) fields.get(field));
                    break;
                case "operationtime":
                    Duration newDuration = Duration.parse(fields.get(field).toString());
                    product.setOperationTime(newDuration);
                    break;
                case "operations":
                    product.setOrders((Set<Order>) fields.get(field));
                    break;
            }
        }
        productRepository.save(product);
    }
}
