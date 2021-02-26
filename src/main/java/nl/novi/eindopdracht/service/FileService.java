package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.FileDb;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    long save(FileDb fileDb) throws IOException;
    Optional<FileDb> getFile(long id);
    List<FileDb> getAllFiles();
}
