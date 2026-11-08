package com.spendwise.repo;
import com.spendwise.model.RecurringTransaction; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface RecurringRepository extends JpaRepository<RecurringTransaction,Long> { List<RecurringTransaction> findAllByUserOrderByIdDesc(AppUser user); }
