package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.FileDb;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {
    Optional<FileDb> getFile(String fileId);
    List<FileDb> getAllFiles();
    FileDb storeFile(MultipartFile file);
}
