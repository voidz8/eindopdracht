package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.ProductNotFoundException;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
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
    public void removeOperation(String drawingNumber, Machine machine) {
        if (!productRepository.existsByDrawingNumber(drawingNumber)){throw new ProductNotFoundException();}
        Product product = productRepository.findByDrawingNumber(drawingNumber).get();
        Product newProduct = new Product();
        newProduct.setDrawingNumber(product.getDrawingNumber());
        newProduct.setAmount(product.getAmount());
        newProduct.setMaterial(product.getMaterial());
        newProduct.setOperationTime(product.getOperationTime());
        newProduct.setOrders(product.getOrders());
        Set<Machine>  machines = new HashSet<>();
        for (Machine m : product.getOperations()) {
            if (!(m.toString().trim().equals(machine.toString().trim()))) {
                machines.add(m);
            }
        }
        newProduct.setOperations(machines);
        productRepository.save(newProduct);
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
            }
        }
        productRepository.save(product);
    }
}
