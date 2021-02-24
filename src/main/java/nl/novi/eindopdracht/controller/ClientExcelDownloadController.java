package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.service.ClientExcelDownloadService;
import nl.novi.eindopdracht.service.ClientService;
import nl.novi.eindopdracht.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class ClientExcelDownloadController {

    @Autowired
    private ClientExcelDownloadService clientService;

    @GetMapping(value = "/download}")
    public ResponseEntity<InputStreamResource> getFile() {
        String filename = "clients.xlsx";
        InputStreamResource file = new InputStreamResource(clientService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);

    }
}
