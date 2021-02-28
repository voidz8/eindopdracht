package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.FileDb;
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
    @GetMapping(value = "/files/{orderNumber}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable(value = "orderNumber") String orderNumber, HttpServletRequest  request){
        Optional<FileDb> fileDb = fileService.getFile(orderNumber);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.get().getFileName() + "\"")
                .body(new ByteArrayResource(fileDb.get().getData()));
    }
    @PostMapping(value = "/files")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file, String orderNumber){
        FileDb fileName = fileService.storeFile(file, orderNumber);

        String fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(fileName.getFileName())
                .toUriString();

        return new ResponseEntity<>("fileId: " + fileName.getFileId(), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/files/{orderNumber}")
    public ResponseEntity<Object> deleteFile(@PathVariable(value = "orderNumber") String orderNumber){
        fileService.deleteFile(orderNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
