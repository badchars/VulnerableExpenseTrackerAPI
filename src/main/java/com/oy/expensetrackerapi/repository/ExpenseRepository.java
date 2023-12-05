package com.oy.expensetrackerapi.repository;

import com.oy.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // SELECT * from tbl_expenses WHERE name LIKE '%keyword%';
    Page<Expense> findByNameContaining(String keyword, Pageable pageable);

    // SELECT * from tbl_expenses WHERE category=?';
    Page<Expense> findByCategory(String category, Pageable pageable);

    // SELECT * from tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate';
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable pageable);

    // SELECT * FROM tbl_expenses WHERE user_id=?
    Page<Expense> findByUserId(Long id, Pageable pageable);

    // SELECT * FROM tbl_expenses WHERE user_id=? AND id=?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);

    // Logged in user finder methods ---------------------------------------
    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable pageable);
    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable pageable);
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable pageable);

    //  SQL injection
    @Query(value = "select * from tbl_expenses where category=:id", nativeQuery = true)
    List<Expense> getExpenseByIdSql(@PathVariable("id") String id);

}
