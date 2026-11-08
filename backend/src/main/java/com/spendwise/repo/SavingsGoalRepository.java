package com.spendwise.repo;
import com.spendwise.model.SavingsGoal; import com.spendwise.model.AppUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface SavingsGoalRepository extends JpaRepository<SavingsGoal,Long> { List<SavingsGoal> findAllByUserOrderByIdDesc(AppUser user); }
