package com.spendwise.repo;
import com.spendwise.model.Transaction; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TransactionRepository extends JpaRepository<Transaction,Long> { List<Transaction> findAllByUserOrderByIdDesc(AppUser user); }
