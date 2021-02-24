package nl.novi.eindopdracht.exporter;

import nl.novi.eindopdracht.model.Client;
import nl.novi.eindopdracht.model.Order;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelFileExporter {

    static String[] headers= {"Companyname", "Email", "Debtornumber"};

    public static ByteArrayInputStream clientsToExcel(List<Client> clients) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();)
        {
            Sheet sheet = workbook.createSheet("Order");

            //Header
            Row headerRow = sheet.createRow(0);

            for (int col=0; col < headers.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }
            int rowIdx = 1;
            for (Client client: clients){
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(client.getCompanyName());
                row.createCell(1).setCellValue(client.getEmail());
                row.createCell(2).setCellValue(client.getDebtorNumber());
        }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
    }
        catch (IOException e){
            throw new RuntimeException("Failed to import data to Excel file" + e.getMessage());
        }
    }
}