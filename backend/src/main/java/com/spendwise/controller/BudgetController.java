package com.spendwise.controller;
import com.spendwise.model.Budget;import com.spendwise.repo.BudgetRepository;import com.spendwise.service.CurrentUser;import org.springframework.web.bind.annotation.*;import java.math.BigDecimal;import java.util.*;
@RestController @RequestMapping("/api/budgets") public class BudgetController{
 private final BudgetRepository repo;private final CurrentUser current;public BudgetController(BudgetRepository r,CurrentUser c){repo=r;current=c;}
 public record Request(String month,String category,BigDecimal limitAmount){}
 private Budget owned(Long id){return repo.findById(id).filter(x->x.getUser().getId().equals(current.get().getId())).orElseThrow(()->new NoSuchElementException("Budget not found"));}
 @GetMapping public List<Budget> all(){return repo.findAllByUserOrderByIdDesc(current.get());}
 @PostMapping public Budget create(@RequestBody Request r){return save(new Budget(),r);}
 @PutMapping("/{id}") public Budget update(@PathVariable Long id,@RequestBody Request r){return save(owned(id),r);}
 private Budget save(Budget b,Request r){if(r.month()==null||!r.month().matches("\d{4}-\d{2}")||r.category()==null||r.limitAmount()==null||r.limitAmount().compareTo(BigDecimal.ZERO)<=0)throw new IllegalArgumentException("Provide month (YYYY-MM), category and positive limit");b.setUser(current.get());b.setMonth(r.month());b.setCategory(r.category());b.setLimitAmount(r.limitAmount());return repo.save(b);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.delete(owned(id));}
}
