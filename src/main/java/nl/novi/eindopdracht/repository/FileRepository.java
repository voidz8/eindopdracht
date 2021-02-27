package nl.novi.eindopdracht.repository;

import nl.novi.eindopdracht.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FileRepository extends JpaRepository<FileDb, String> {
    Optional<FileDb> findByFileId(String fileId);
}
