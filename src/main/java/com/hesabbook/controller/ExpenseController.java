package com.hesabbook.controller;

import com.hesabbook.entity.expense.Expense;
import com.hesabbook.service.expense.ExpenseService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

/*    @PostMapping("/upload")
    public BusinessResponse uploadExcel(@RequestBody List<LinkedHashMap<String, String>> linkedHashMap) throws IOException {
        BusinessResponse businessResponse = expenseService.saveBulk(linkedHashMap);
        return businessResponse;
    }

    @GetMapping("/all")
    public BusinessResponse getAllMangeUser() {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Inventory> accountDetailResponse = expenseService.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        Inventory accountDetailResponse = expenseService.find(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }*/

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody Expense expense) {
        BusinessResponse businessResponse = new BusinessResponse();
        Expense accountDetailResponse = expenseService.save(expense);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

 /*   @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            expenseService.delete(id);
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
    }*/
}
