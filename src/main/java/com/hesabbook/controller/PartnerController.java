package com.hesabbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import com.hesabbook.entity.party.Partner;
import com.hesabbook.service.party.PartyService;
import com.hesabbook.utils.BusinessResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hesabbook/partner")
public class PartnerController {
    @Autowired
    private PartyService partyService;

    @PostMapping("/upload")
    public List<Object> uploadExcel(@RequestBody List<LinkedHashMap> linkedHashMap) throws IOException {
        List<List<String>> excelData = new ArrayList<>();

/*        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming you're working with the first sheet

            Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Iterator<Cell> cells = currentRow.iterator();

                List<String> rowData = new ArrayList<>();
                while (cells.hasNext()) {
                    Cell currentCell = cells.next();
                    rowData.add(currentCell.toString());
                }

                excelData.add(rowData);
            }
        }*/

      //  return excelData;
       // List<Object> excelData = ExcelParser.parse(file.getInputStream());

        return Collections.singletonList(excelData);
    }



    @PostMapping("/uploadss")
    public List<List<String>> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        List<List<String>> excelData = new ArrayList<>();
        Integer rows = 0;
        List<Partner> partnerList = new ArrayList<>();
        Workbook workboosak = WorkbookFactory.create(file.getInputStream());

        XSSFWorkbook workbookss = new XSSFWorkbook(file.getInputStream());
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    Partner partner = new Partner();
                    for (Cell cell : row) {
                        System.out.print(cellToString(cell) + "\t");
                    }
                    if (rows > 0) {
                        partner.setPName(Objects.equals(String.valueOf(row.getCell(0)), "") ? null : String.valueOf(row.getCell(0)));
                        partner.setMobileNumber(Objects.equals(String.valueOf(row.getCell(1)), "") ? null : String.valueOf(row.getCell(1)));
                        partner.setEmail(Objects.equals(String.valueOf(row.getCell(2)), "") ? null : String.valueOf(row.getCell(2)));
                        partner.setBillingAddress(Objects.equals(String.valueOf(row.getCell(3)), "") ? null : String.valueOf(row.getCell(3)));
                        partner.setShippingAddress(Objects.equals(String.valueOf(row.getCell(4)), "") ? null : String.valueOf(row.getCell(4)));
                        partner.setCompany(Objects.equals(String.valueOf(row.getCell(5)), "") ? null : String.valueOf(row.getCell(5)));
                        partner.setGstNumber(Objects.equals(String.valueOf(row.getCell(6)), "") ? null : String.valueOf(row.getCell(6)));
                        partner.setPartyCategory(Objects.equals(String.valueOf(row.getCell(7)), "") ? null : String.valueOf(row.getCell(7)));
                        partner.setCreditLimit(Objects.equals(String.valueOf(row.getCell(8)), "") ? null : String.valueOf(row.getCell(8)));
                        partner.setCreditPeriod(Objects.equals(String.valueOf(row.getCell(9)), "") ? null : String.valueOf(row.getCell(9)));
                        partner.setCreditPeriodType(Objects.equals(String.valueOf(row.getCell(10)), "") ? null : String.valueOf(row.getCell(10)));
                        partner.setOpeningBalance(Objects.equals(String.valueOf(row.getCell(11)), "") ? null : String.valueOf(row.getCell(11)));
                        partner.setOpeningBalanceType(Objects.equals(String.valueOf(row.getCell(12)), "") ? null : String.valueOf(row.getCell(12)));
                   //     boolean flages = areAllFieldsNull(partner);
                   //     if (!flages) {
                     //       partner.setPrimary_user_id(userfromCache.getPrimary_user_id());
                  //          partner.setSecondary_user_id(userfromCache.getSecondary_user_id());
                            partnerList.add(partner);
                     //   }
                    }
                    rows = rows + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming you're working with the first sheet

        /*    Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Iterator<Cell> cells = currentRow.iterator();

                List<String> rowData = new ArrayList<>();
                while (cells.hasNext()) {
                    Cell currentCell = cells.next();
                    rowData.add(currentCell.toString());
                }

                excelData.add(rowData);
            }*/
        }

        return excelData;
    }
    @GetMapping("/all")
    public BusinessResponse getAllMangeUser() {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Partner> accountDetailResponse = partyService.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Partner> accountDetailResponse = partyService.findByPrimaryPartnerId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody Partner partner) {
        BusinessResponse businessResponse = new BusinessResponse();
        Partner accountDetailResponse = partyService.save(partner);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            partyService.delete(id);
            flag = "Success";
            businessResponse.setCode(200);
            businessResponse.setStatus("SUCCESS");
            businessResponse.setResponse(flag);
        } else {
            flag = "Failure";
            businessResponse.setCode(200);
            businessResponse.setStatus("FAILURE");
            businessResponse.setResponse(flag);
        }
        return businessResponse;
    }

    private String cellToString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
