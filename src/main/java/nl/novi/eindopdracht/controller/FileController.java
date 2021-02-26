package nl.novi.eindopdracht.controller;

import lombok.Getter;
import nl.novi.eindopdracht.model.FileDb;
import nl.novi.eindopdracht.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/files")
    public ResponseEntity<Object> getAllFiles(){
        return new ResponseEntity<>(fileService.getAllFiles(), HttpStatus.OK);
    }
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<Object> getFile(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(fileService.getFile(id), HttpStatus.OK);
    }
    @PostMapping(value = "/files")
    public ResponseEntity<Object> addFile(@RequestBody FileDb fileDb) throws IOException {
        long newFile = fileService.save(fileDb);
        return new ResponseEntity<>("A new file is created: " + newFile, HttpStatus.CREATED);
    }
}
