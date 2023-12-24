package com.oy.expensetrackerapi.controller;

import com.oy.expensetrackerapi.entity.Expense;
import com.oy.expensetrackerapi.entity.User;
import com.oy.expensetrackerapi.repository.ExpenseRepository;
import com.oy.expensetrackerapi.service.ExpenseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@Api(tags = "Expense API", description = "Expense CRUD Operations")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable pageable){
        return expenseService.getAllExpenses(pageable).toList();
    }


    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam("id") Long id){
        expenseService.deleteExpenseById(id);
    }

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id, expense);
    }


    @GetMapping("/expenses/name")
    public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable pageable) {
        return expenseService.readByName(name, pageable);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable pageable) {
        return expenseService.readByCategory(category, pageable);
    }

    @GetMapping("/expenses/image")
    public List<Expense> getExpenseByIdSql(@RequestParam String category) {
        System.out.println("SQL query called");
        return expenseRepository.getExpenseByIdSql(category);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
            Pageable pageable
            )                {
        return expenseService.readByDate(startDate, endDate, pageable);
    }

    @GetMapping("/getXFrameOptions")
    public ResponseEntity getXFrameOptions(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Frame-Options", "DENY");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getStrictTransportSecurity")
    public ResponseEntity getStrictTransportSecurity(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getContentType")
    public ResponseEntity getContentType(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getContentSecurityPolicy")
    public ResponseEntity getContentSecurityPolicy(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Security-Policy", "frame-ancestors 'none';");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getAccessControlAllowOrigin")
    public ResponseEntity getAccessControlAllowOrigin(@RequestHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN) String value){

        if(value == null || value.equalsIgnoreCase("null")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getXContentTypeOptions")
    public ResponseEntity getXContentTypeOptions(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Content-Type-Options", "nosniff");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getPubliclyAccessibleLeakyAPIs")
    public ResponseEntity getPubliclyAccessibleLeakyAPIs(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getPubliclyAccessibleByDesign")
    public ResponseEntity getPubliclyAccessibleByDesign(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAzureAPIWithoutRateLimiting")
    public ResponseEntity getAzureAPIWithoutRateLimiting(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-ms-ratelimit-remaining-resource", "Microsoft.Compute/DeleteVMScaleSet3Min;107");
        return new ResponseEntity(headers,HttpStatus.OK);
    }
    @GetMapping("/getXAspNetVersion")
    public ResponseEntity getXAspNetVersion(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Asp-Net-Version", "2.1");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getXPoweredBy")
    public ResponseEntity getXPoweredBy(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Powered-By", "2.1");
        return new ResponseEntity(headers,HttpStatus.OK);
    }


}
