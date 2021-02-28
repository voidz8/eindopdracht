package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.FileNotFoundException;
import nl.novi.eindopdracht.exceptions.FileStorageException;
import nl.novi.eindopdracht.model.FileDb;
import nl.novi.eindopdracht.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDb storeFile(MultipartFile file, String orderNumber) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")){
                throw new FileStorageException("Filename contains invalid path sequence "+ fileName);
            }
            FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes(), orderNumber);

            return fileRepository.save(fileDb);
        } catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName , ex);
        }
    }

    @Override
    public Optional<FileDb> getFile(String orderNumber) {
        if (!fileRepository.existsByOrderNumber(orderNumber)){throw new FileNotFoundException();}
        return fileRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<FileDb> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteFile(String orderNumber) {
        if (!fileRepository.existsByOrderNumber(orderNumber)){throw new FileNotFoundException();}
        fileRepository.deleteByOrderNumber(orderNumber);
    }
}
