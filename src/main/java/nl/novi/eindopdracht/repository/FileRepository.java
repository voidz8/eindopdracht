package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FileRepository extends JpaRepository<FileDb, String> {
    boolean existsByOrderNumber(String orderNumber);
    Optional<FileDb> findByOrderNumber(String orderNumber);
    void deleteByOrderNumber(String orderNumber);
}
