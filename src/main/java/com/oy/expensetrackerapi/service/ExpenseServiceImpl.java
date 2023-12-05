package com.oy.expensetrackerapi.service;

import com.oy.expensetrackerapi.entity.Expense;
import com.oy.expensetrackerapi.exception.ResourceNotFoundException;
import com.oy.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements  ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable){
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(), pageable);
    }

    @Override
    public Expense getExpenseById(Long id) throws ResourceNotFoundException {
        // TODO : VULNERABILITY-3 -> Eger asagidaki gibi kalirsa her login olan kullanici herkese ait kayitlari goruntuleyebilir
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id: "+id);

        // TODO : Secure Code 
//        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
//        if(expense.isPresent()) {
//            return expense.get();
//        }
//        throw new ResourceNotFoundException("Expense is not found for the id: "+id);

    }

    @Override
    public void deleteExpenseById(Long id){
        // TODO: VULNERABILITY-1 -> Attacker could delete every expense 
        expenseRepository.deleteById(id);

        // TODO: Secure Code
//        Expense expense = getExpenseById(id);
//        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense){
        expense.setUser(userService.getLoggedInUser());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() !=null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() !=null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setAmount(expense.getAmount() !=null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDate(expense.getDate() !=null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setCategory(expense.getCategory() !=null ? expense.getCategory() : existingExpense.getCategory());

        // TODO: VULNERABILITY-6 SSRF
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(expense.getBillUrl(), String.class);
        existingExpense.setBilldata(response);

        return expenseRepository.save(existingExpense);
    }


    @Override
    public List<Expense> readByName(String name, Pageable pageable) {
        // TODO: VULNERABILITY-4 -> Asagidaki kod ile login olan her kullanici her keywordu kullanarak tum kullanicilara ait expense kayitlarini goruntuleyebilir.
        return expenseRepository.findByNameContaining(name, pageable).toList();

        // TODO: Secure Code:
//        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), name, pageable).toList();
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable pageable) {
        return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, pageable).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable) {
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        // TODO: VULNERABILITY -> Asagidaki kod ile login olan her kullanici her tarih araligindaki expense kayitlarini gorebilir. Baska kullaniciya ait kayitlar da goruntulenebilir.
//        Page<Expense> pages = expenseRepository.findByDateBetween(startDate, endDate, pageable);

        // TODO: Secure Code
        Page<Expense> pages = expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, pageable);
        return pages.toList();
    }



}
