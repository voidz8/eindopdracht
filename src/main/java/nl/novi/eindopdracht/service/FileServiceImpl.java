package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.FileDb;
import nl.novi.eindopdracht.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public long save(FileDb fileDb) throws IOException {
        FileDb filedb = fileRepository.save(fileDb);
        return filedb.getId();
    }

    @Override
    public Optional<FileDb> getFile(long id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<FileDb> getAllFiles() {
        return fileRepository.findAll();
    }

}
