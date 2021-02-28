package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.repository.ClientRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
public class ClientExcelDownloadController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {

        try {
            Workbook workbook = new XSSFWorkbook();
            String fileName = "C:/clients.xls ";
            Sheet sheet = workbook.createSheet("Clients");

            //Header
            String[] headers= {"Companyname", "Email", "Debtornumber"};
            Row headerRow = sheet.createRow(0);

            for (int col=0; col < headers.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            List<Client> clients = clientRepository.findAll();

            int rowIdx = 1;
            for (Client client: clients) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(client.getCompanyName());
                row.createCell(1).setCellValue(client.getEmail());
                row.createCell(2).setCellValue(client.getDebtorNumber());
            }
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

            //Code to download
            File fileToDownload = new File(fileName);
            InputStream in = new FileInputStream(fileToDownload);

            // Gets MIME type of the file
            String mimeType = new MimetypesFileTypeMap().getContentType(fileName);

            if (mimeType == null) {
                // Set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // Modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) fileToDownload.length());

            // Forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileToDownload.getName());
            response.setHeader(headerKey, headerValue);

            // obtains response's output stream
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = in.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            in.close();
            outStream.close();

            System.out.println("File downloaded at client successfully");


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
