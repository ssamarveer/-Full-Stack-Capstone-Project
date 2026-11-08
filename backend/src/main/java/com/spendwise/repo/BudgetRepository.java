package com.spendwise.repo;
import com.spendwise.model.Budget; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface BudgetRepository extends JpaRepository<Budget,Long> { List<Budget> findAllByUserOrderByIdDesc(AppUser user); }
