package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByDrawingNumber(String drawingNumber);
    Optional<Product> findByDrawingNumber(String drawingNumber);

}
