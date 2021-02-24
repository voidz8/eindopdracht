package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exporter.ExcelFileExporter;
import nl.novi.eindopdracht.model.Client;

import nl.novi.eindopdracht.repository.ClientRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ClientExcelDownloadServiceImpl implements ClientExcelDownloadService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ByteArrayInputStream load() {
        List<Client> clients = clientRepository.findAll();

        ByteArrayInputStream in  = ExcelFileExporter.clientsToExcel(clients);
        return in;
    }
}
