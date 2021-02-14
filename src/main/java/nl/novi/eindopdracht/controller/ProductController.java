package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Product;
import nl.novi.eindopdracht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getAllProducts(){
    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{drawingNumber}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "drawingNumber") String drawingNumber){
        return new ResponseEntity<>(productService.getProduct(drawingNumber), HttpStatus.OK);
    }
    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        String newProduct = productService.createProduct(product);
        return new ResponseEntity<>("Product with drawingnumber: "+newProduct+ " is created.", HttpStatus.CREATED);
    }
    @PutMapping(value = "/products/{drawingNumber}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "drawingNumber") String drawingNumber, @RequestBody Product product){
        productService.updateProduct(drawingNumber,product);
        return new ResponseEntity<>("Product with drawingnumber: "+ drawingNumber + " is updated.", HttpStatus.OK);
    }
    @PatchMapping(value = "/products/{drawingNumber}")
    public ResponseEntity<Object> partialUpdateProduct(@PathVariable(value = "drawingNumber") String drawingNumber, @RequestBody Product product, Machine operations, Duration operationTime, Order order){
        productService.partialUpdateProduct(drawingNumber, operations, operationTime, order, product);
        return new ResponseEntity<>("Product with drawingnumber: "+ drawingNumber + " is updated.", HttpStatus.OK);
    }
    @DeleteMapping(value = "/products/{drawingNumber}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "drawingNumber")String drawingNumber){
        productService.deleteProduct(drawingNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
