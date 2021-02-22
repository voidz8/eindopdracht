package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.exceptions.BadRequestException;
import nl.novi.eindopdracht.exceptions.ClientNotFoundException;
import nl.novi.eindopdracht.exceptions.ForbiddenException;
import nl.novi.eindopdracht.exceptions.MachineNotFoundException;
import nl.novi.eindopdracht.exceptions.OrderNotFoundException;
import nl.novi.eindopdracht.exceptions.ProductNotFoundException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<Object> exception(ClientNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> exception(ForbiddenException exception) {
        return ResponseEntity.status(401).build();
    }
    @ExceptionHandler(value = MachineNotFoundException.class)
    public ResponseEntity<Object> exception(MachineNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<Object> exception(OrderNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}