package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByDrawingNumber(String drawingNumber);
    Optional<Product> findByDrawingNumber(String drawingNumber);
    void deleteByDrawingNumber(String drawingNumber);

}
