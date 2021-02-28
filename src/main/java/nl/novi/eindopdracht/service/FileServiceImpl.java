package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.FileNotFoundException;
import nl.novi.eindopdracht.exceptions.FileStorageException;
import nl.novi.eindopdracht.model.FileDb;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDb storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")){
                throw new FileStorageException("Filename contains invalid path sequence "+ fileName);
            }
            FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

            return fileRepository.save(fileDb);
        } catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName , ex);
        }
    }

    @Override
    public Optional<FileDb> getFile(String fileId) {
        if (!fileRepository.existsById(fileId)){throw new FileNotFoundException();}
        return fileRepository.findByFileId(fileId);
    }

    @Override
    public List<FileDb> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public void deleteFile(String fileId) {
        if (!fileRepository.existsById(fileId)){throw new FileNotFoundException();}
        fileRepository.deleteById(fileId);
    }
}
