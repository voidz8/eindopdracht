package nl.novi.eindopdracht.controller;

import com.sun.istack.ByteArrayDataSource;
import nl.novi.eindopdracht.model.FileDb;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/files")
    public ResponseEntity<Object> getAllFiles(){
        return new ResponseEntity<>(fileService.getAllFiles(), HttpStatus.OK);
    }
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable(value = "id") String id, HttpServletRequest  request){
        Optional<FileDb> fileDb = fileService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.get().getFileName() + "\"")
                .body(new ByteArrayResource(fileDb.get().getData()));
    }
    @PostMapping(value = "/files")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file){
        FileDb fileName = fileService.storeFile(file);

        String fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(fileName.getFileName())
                .toUriString();

        return new ResponseEntity<>("fileId: " + fileName.getFileId(), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/files/{fileId}")
    public ResponseEntity<Object> deleteFile(@PathVariable(value = "fileId") String fileId){
        fileService.deleteFile(fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
