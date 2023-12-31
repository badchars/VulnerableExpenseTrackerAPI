package com.oy.expensetrackerapi.controller;

import com.oy.expensetrackerapi.entity.Expense;
import com.oy.expensetrackerapi.repository.ExpenseRepository;
import com.oy.expensetrackerapi.service.ExpenseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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

}
