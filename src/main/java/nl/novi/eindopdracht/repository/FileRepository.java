package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<FileDb, Long> {
}
